package gunjika.varshney.gla.macsolution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class forget_pass extends AppCompatActivity {
    TextView email;
    Button forgetButton;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        email=findViewById(R.id.emailText);
        forgetButton=findViewById(R.id.newPass);
        firebaseAuth=FirebaseAuth.getInstance();

        forgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail=email.getText().toString().trim();
                if (useremail.equals(""))
                {
                    Toast.makeText(forget_pass.this, "please eneter your registered email id", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(forget_pass.this, "password send to your email", Toast.LENGTH_SHORT).show();
                               // finish();
                                startActivity(new Intent(forget_pass.this,LoginActivity.class));
                            }
                            else
                            {
                                Toast.makeText(forget_pass.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}
