package gunjika.varshney.gla.macsolution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class postActivity extends AppCompatActivity {
    TextView selectedPost;
    Button data;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();

        selectedPost=findViewById(R.id.selectedPost);
        data=findViewById(R.id.showData);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference=FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String post=dataSnapshot.child("post").getValue().toString();
                        selectedPost.setText(post);
                        if (post.equals(""))
                        {
                            Toast.makeText(postActivity.this, "press show button to continue", Toast.LENGTH_SHORT).show();
                        }
                        else if(post.equals("Helper 1"))
                        {
                            startActivity(new Intent(postActivity.this,Helper1Activity.class));
                        }
                        else
                        {
                            startActivity(new Intent(postActivity.this,Helper2Activity.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
