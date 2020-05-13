package gunjika.varshney.gla.macsolution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SaveMapData extends AppCompatActivity implements View.OnClickListener {
    private Button btnproceed,fetch;
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Profile> list;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_map_data2);

        recyclerView=findViewById(R.id.Recycler);
        fetch=findViewById(R.id.fetch);
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SaveMapData.this,ViewPdfFilw.class));
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<Profile>();
        reference= FirebaseDatabase.getInstance().getReference().child("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Profile>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Profile p = dataSnapshot1.getValue(Profile.class);
                    list.add(p);
                }
                adapter = new MyAdapter(SaveMapData.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SaveMapData.this, "Oops... Something is Wrong.", Toast.LENGTH_SHORT).show();
            }
        });

        btnproceed=findViewById(R.id.btnproceed);
        btnproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i=new Intent(SaveMapData.this,MapsActivity2.class);
                Intent i=new Intent(SaveMapData.this,MapEx.class);

                startActivity(i);
            }
        });
    }


    //    private void saveUserInformation(){
//        String name =editTextName.getText().toString().trim();
//        double latitude= Double.parseDouble(editTextLatitude.getText().toString().trim());
//        double longitude= Double.parseDouble(editTextLongitude.getText().toString().trim());
//        UserInformation userInformation=new UserInformation(name,latitude,longitude);
//        reference.child("Map Data").setValue(userInformation);
//        Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show();
//    }
    @Override
    public void onClick(View view) {
        if(view==btnproceed){
            finish();
        }
    }
}
