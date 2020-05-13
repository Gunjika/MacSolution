package gunjika.varshney.gla.macsolution;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;

public class documentActivity extends AppCompatActivity {
    Button upload,choose;
    EditText username;
    private final int PICK_IMAGE_REQUEST = 1;
    ArrayList<Uri> FileList=new ArrayList<Uri>();
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Processing Please Wait.....");

        choose=findViewById(R.id.chooseFile);
        upload=findViewById(R.id.upload);
        username=findViewById(R.id.usrname);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE_REQUEST){
            if (resultCode==RESULT_OK){
                if (data.getClipData()!=null){
                    int count=data.getClipData().getItemCount();
                    int i=0;
                    while(i<count){
                        Uri File=data.getClipData().getItemAt(i).getUri();
                        FileList.add(File);
                        i++;
                    }
                    Toast.makeText(this, "You have Selected "+FileList.size()+" Files", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void ChooseFiles(View view) {
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    public void UploadFiles(View view) {
        progressDialog.show();
        Toast.makeText(this, "if takes time. you can press again", Toast.LENGTH_SHORT).show();
        for (int j=0;j<FileList.size();j++){
            Uri PerFile=FileList.get(j);
            username=findViewById(R.id.usrname);
            final String name=username.getText().toString().trim();
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            StorageReference folder=FirebaseStorage.getInstance().getReference().child("Files").child(uid);
            final StorageReference filename=folder.child("file"+PerFile.getLastPathSegment());
            filename.putFile(PerFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("user");
                            HashMap<String,String> hashMap=new HashMap<>();
                            hashMap.put("link",String.valueOf(uri));
                            hashMap.put("Name",String.valueOf(name));
                            databaseReference.push().setValue(hashMap);
                            progressDialog.dismiss();
                            Toast.makeText(documentActivity.this, "Upload successfully", Toast.LENGTH_SHORT).show();
                            FileList.clear();
                            startActivity(new Intent(documentActivity.this,postActivity.class));
                        }
                    });
                }
            });
        }
    }
}