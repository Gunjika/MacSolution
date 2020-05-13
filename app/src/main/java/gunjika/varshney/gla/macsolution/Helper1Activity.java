package gunjika.varshney.gla.macsolution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Helper1Activity extends AppCompatActivity {
    Spinner spinner2;
    Button on,off;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper1);
        on=findViewById(R.id.onButton);
        off=findViewById(R.id.offButton);
        username=findViewById(R.id.username);
        spinner2=findViewById(R.id.spinner2);
        List<String> list=new ArrayList<String>();
        list.add("Select ESR");
        list.add("FIRAWASI");
        list.add("RAIDHANA");
        list.add("MEETHRI");
        list.add("REENGAN");
        list.add("DHYAWA");
        list.add("TANWARA");
        list.add("RODU");
        list.add("SARADI");
        list.add("GHIRARODA KHARA");
        list.add("KASUMBI UPADRA");
        list.add("SANWARD");
        list.add("KASUMBI UPADRA");
        list.add("SHIMLA");
        list.add("JASWANTGARH");
        list.add("DABDI");
        list.add("BAKALIYA");
        list.add("GOREDI");
        list.add("BED");
        list.add("BALDOO");
        list.add("SUNARI");
        list.add("ODIT");
        list.add("NIMBI JODHA");
        list.add("GENANA");
        list.add("RATAU");
        list.add("SEENWA");
        list.add("CHANDRAI");
        list.add("SANDAS");
        list.add("HUDAS");
        list.add("SILANWAD");
        list.add("DHINGSARI");
        list.add("NANDWAN");
        list.add("MANGALPURA");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner2.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(Helper1Activity.this,MapsActivity.class));
                Intent intent = new Intent(getBaseContext(),MapsActivity.class);
                intent.putExtra("username", username.getText().toString().trim());
                startActivity(intent);
            }
        });
    }
}
