package gunjika.varshney.gla.macsolution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class adminLogin extends AppCompatActivity {
    TextView adminName,adminPswd;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        adminName=findViewById(R.id.adminName);
        adminPswd=findViewById(R.id.adminPswrd);
        save=findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=adminName.getText().toString().trim();
                String pswd=adminPswd.getText().toString().trim();
                if(name.equals("admin") && pswd.equals("admin"))
                {
                    Toast.makeText(adminLogin.this, "Proceed to next", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(adminLogin.this,SaveMapData.class));
                }
                else if(name.equals("")||pswd.equals(""))
                {
                    Toast.makeText(adminLogin.this, "fill the empty details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(adminLogin.this, "incorrect name or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
