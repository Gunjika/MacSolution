package gunjika.varshney.gla.macsolution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    TextView email,pass;
    Button login;
    CheckBox forgetPass,NewReg;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

        String uid=mAuth.getCurrentUser().getUid();
//        DatabaseReference current_user=databaseReference.childid);
        reference= FirebaseDatabase.getInstance().getReference().child(uid);

        email=findViewById(R.id.emaill);
        pass=findViewById(R.id.password);
        login=findViewById(R.id.loginbutton);
        forgetPass=findViewById(R.id.forgetPass);
        NewReg=findViewById(R.id.newReg);
      /*  mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null)
                {
                    startActivity(new Intent(LoginActivity.this,PostActivity.class));
                }
            }
        };*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });
        NewReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NewReg.isChecked())
                {
                    Intent passIntent=new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(passIntent);
                }
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (forgetPass.isChecked())
                {
                    Intent failIntent=new Intent(LoginActivity.this,forget_pass.class);
                    startActivity(failIntent);
                }
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        //mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn()
    {
        String emailId=email.getText().toString();
        String password=pass.getText().toString();
        if (TextUtils.isEmpty(emailId)||TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "please fill all the entries", Toast.LENGTH_SHORT).show();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(emailId, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        if (mAuth.getCurrentUser().isEmailVerified())
                        {
                            Toast.makeText(LoginActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
                            Intent loginIntent=new Intent(LoginActivity.this,documentActivity.class);
                            startActivity(loginIntent);
                        }else
                        {
                            Toast.makeText(getApplicationContext(),"please verify your email address",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
