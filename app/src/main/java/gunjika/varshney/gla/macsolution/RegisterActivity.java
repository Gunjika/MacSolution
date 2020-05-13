package gunjika.varshney.gla.macsolution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    Spinner spinner,tehsil,education,State,District,code;
    TextView Email,pswrd,username,address,contact,pin;
    Button registerButton;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    User user;
    ArrayList<String> stateList;
    ArrayAdapter<String> arrayAdapter_state,arrayAdapter_district;

    ArrayList<String> arrayList_AP,districtList,arrayList_ANP,arrayList_as,Ajmer,alwar,banswara,baran,barmer,bharatpur,bhilwara,bikaner,
            bundi,chitorgarh,churu,dausa,dholpur,dungarpur,hanumangarh,jaipur,jaisalmer,jalor,nagaur,jodhpur;
    ArrayAdapter<String> arrayAdapter_raj,arrayAdapter_th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");

        Email=findViewById(R.id.emaill);
        pswrd=findViewById(R.id.pswrd);
        username=findViewById(R.id.username);
        address=findViewById(R.id.address);
        pin=findViewById(R.id.pinCode);
        //tehsil=findViewById(R.id.tehsil);
        registerButton=findViewById(R.id.registerbutton);
        contact=findViewById(R.id.contact);
//        pinCode=findViewById(R.id.pin);
        user=new User();

        spinner=findViewById(R.id.spinner);
        List<String> list=new ArrayList<String>();
        list.add("Post Applied for");
        list.add("Electro Mechanical Engineer");
        list.add("Electrician");
        list.add("Electro Mechanic");
        list.add("Pump Driver");
        list.add("Filters");
        list.add("Helper 1");
        list.add("Helper 2");
        list.add("Gardner");
        list.add("Sweeper");
        list.add("helpers & Chowkidar");

        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posn, long id) {
                spinner.setSelection(posn);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        code=findViewById(R.id.code);
        List<String> listCode=new ArrayList<String>();
        listCode.add("Select Country Code");
        listCode.add("+93    Afghanistan");
        listCode.add("+672    Antarctica");
        listCode.add("+43    Austria");
        listCode.add("+61    Australia");
        listCode.add("+55    Brazil");
        listCode.add("+1    Canada");
        listCode.add("+86    China");
        listCode.add("+75    Colombia");
        listCode.add("+33    France");
        listCode.add("+995    Georgia");
        listCode.add("+30    Greece");
        listCode.add("+852    Hong Kong");
        listCode.add("+91    India");
        listCode.add("+39    Italy");
        listCode.add("+81    Japan");
        listCode.add("+231    Liberia");
        listCode.add("+356    Malta");
        listCode.add("+977    Nepal");
        listCode.add("+64    New Zealand");
        listCode.add("+850    North Korea");
        listCode.add("+94    Sri Lanka");
        listCode.add("+66    Thailand");
        listCode.add("+44    United Kingdom");

        final ArrayAdapter<String> codeAdap=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,listCode);
        codeAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        code.setAdapter(codeAdap);

        code.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                code.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        State=findViewById(R.id.State);
        District=findViewById(R.id.District);
        tehsil=findViewById(R.id.Tehsil);

        stateList=new ArrayList<>();
//        List<String> stateList=new ArrayList<>();
        stateList.add("Select State");
        stateList.add("Andra Pradesh");
        stateList.add("Arunachal Pradesh");
        stateList.add("Assam");
        stateList.add("Bihar");
        stateList.add("Chhattisgarh");
        stateList.add("Goa");
        stateList.add("Gujarat");
        stateList.add("Haryana");
        stateList.add("Himachal Pradesh");
        stateList.add("Jammu and Kashmir");
        stateList.add("Jharkhand");
        stateList.add("Karnataka");
        stateList.add("Kerala");
        stateList.add("Madya Pradesh");
        stateList.add("Maharashtra");
        stateList.add("Manipur");
        stateList.add("Meghalaya");
        stateList.add("Mizoram");
        stateList.add("Nagaland");
        stateList.add("Orissa");
        stateList.add("Punjab");
        stateList.add("Rajasthan");
        stateList.add("Sikkim");
        stateList.add("Tamil Nadu");
        stateList.add("Telagana");
        stateList.add("Tripura");
        stateList.add("Uttaranchal");
        stateList.add("Uttar Pradesh");
        stateList.add("West Bengal");

        arrayAdapter_state=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,stateList);
        State.setAdapter(arrayAdapter_state);

        districtList=new ArrayList<>();
        districtList.add("Select District");
        districtList.add("Ajmer");
        districtList.add("Alwar");
        districtList.add("Banswara");
        districtList.add("Baran");
        districtList.add("Barmer");
        districtList.add("Bharatpur");
        districtList.add("Bhilwara");
        districtList.add("Bikaner");
        districtList.add("Bundi");
        districtList.add("Chittorgarh");
        districtList.add("Churu");
        districtList.add("Dausa");
        districtList.add("Dholpur");
        districtList.add("Dungarpur");
        districtList.add("Hanumangarh");
        districtList.add("Jaipur");
        districtList.add("Jaisalmer");
        districtList.add("Jalor");
        districtList.add("Jhalawar");
        districtList.add("Jhunjhunu");
        districtList.add("Jodhpur");
        districtList.add("Karauli");
        districtList.add("Kota");
        districtList.add("Nagaur");
        districtList.add("Pali");
        districtList.add("Pratapgarh");
        districtList.add("Rajsamand");
        districtList.add("Sawai Madhopur");
        districtList.add("Sikar");
        districtList.add("Sirohi");
        districtList.add("Sri Ganganagar");
        districtList.add("Tonk");
        districtList.add("Udaipur");


        arrayList_AP=new ArrayList<>();
        arrayList_AP.add("Select District");
        arrayList_AP.add("Anantapur");
        arrayList_AP.add("Chittoor");
        arrayList_AP.add("East Godavari");
        arrayList_AP.add("Guntur");
        arrayList_AP.add("YSR Kadapa");
        arrayList_AP.add("Krishna");
        arrayList_AP.add("Kurnool");
        arrayList_AP.add("Nellore");

        arrayList_ANP=new ArrayList<>();
        arrayList_ANP.add("Select District");
        arrayList_ANP.add("Anjaw");
        arrayList_ANP.add("Changlang");
        arrayList_ANP.add("East Kameng");
        arrayList_ANP.add("East Siang");
        arrayList_ANP.add("Kra Daadi");

        arrayList_as=new ArrayList<>();
        arrayList_as.add("Select District");
        arrayList_as.add("Baksa");
        arrayList_as.add("Barpeta");
        arrayList_as.add("Chirang");
        arrayList_as.add("Darrang");

        State.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==22){
                    arrayAdapter_raj=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,districtList);
                }
                else if (position==1)
                {
                    arrayAdapter_raj=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_AP);
                }
                else if (position==2)
                {
                    arrayAdapter_raj=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_ANP);
                }
                else if (position==3)
                {
                    arrayAdapter_raj=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_as);
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "please Select state", Toast.LENGTH_SHORT).show();
                }
                District.setAdapter(arrayAdapter_raj);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        arrayAdapter_district=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,districtList);
        District.setAdapter(arrayAdapter_district);

        Ajmer=new ArrayList<>();
        Ajmer.add("Select Tehsil");
        Ajmer.add("Ajmer");
        Ajmer.add("Kishangarh");
        Ajmer.add("Beawar");
        Ajmer.add("Masuda");
        Ajmer.add("Kekri");
        Ajmer.add("Nasirabad");
        Ajmer.add("Sarwar");
        Ajmer.add("Peesangan");
        Ajmer.add("Bhinay");

        alwar=new ArrayList<>();
        alwar.add("Select Tehsil");
        alwar.add("Alwar");
        alwar.add("Tijara");
        alwar.add("Behror");
        alwar.add("Rajgarh");
        alwar.add("Lachhmangarh");
        alwar.add("Bansur");
        alwar.add("Ramgarh");
        alwar.add("Kathumar");
        alwar.add("Thanagazi");
        alwar.add("Mandawar");
        alwar.add("Kishangarh Bas");
        alwar.add("Kotkasim");

        banswara=new ArrayList<>();
        banswara.add("Select Tehsil");
        banswara.add("Banswara");
        banswara.add("Kushalgarh");
        banswara.add("Bagidora");
        banswara.add("Garhi");
        banswara.add("Ghatol");

        baran=new ArrayList<>();
        baran.add("Select Tehsil");
        baran.add("Baran");
        baran.add("Chhipabarod");
        baran.add("Kishanganj");
        baran.add("Chhabra");
        baran.add("Atru");
        baran.add("Shahbad");
        baran.add("Antah");
        baran.add("Mangrol");

        barmer=new ArrayList<>();
        barmer.add("Select Tehsil");
        barmer.add("Chohtan");
        barmer.add("Gudha Malani");
        barmer.add("Pachpadra");
        barmer.add("Barmer");
        barmer.add("Siwana");
        barmer.add("Baytoo");
        barmer.add("Sheo");
        barmer.add("Ramsar");

        bharatpur=new ArrayList<>();
        bharatpur.add("Select Tehsil");
        bharatpur.add("Bharatpur");
        bharatpur.add("Weir");
        bharatpur.add("Bayana");
        bharatpur.add("Rupbas");
        bharatpur.add("Nagar");
        bharatpur.add("Deeg");
        bharatpur.add("Nadbai");
        bharatpur.add("Kaman");
        bharatpur.add("Kumher");
        bharatpur.add("Pahari");

        bhilwara=new ArrayList<>();
        bhilwara.add("Select Tehsil");
        bhilwara.add("Bhilwara");
        bhilwara.add("Asind");
        bhilwara.add("Mandal");
        bhilwara.add("Jahazpur");
        bhilwara.add("Shahpura");
        bhilwara.add("Mandalgarh");
        bhilwara.add("Kotri");
        bhilwara.add("Hurda");
        bhilwara.add("Sahara");
        bhilwara.add("Banera");
        bhilwara.add("Raipur");
        bhilwara.add("Beejoliya");

        bikaner=new ArrayList<>();
        bikaner.add("Select Tehsil");
        bikaner.add("Bikaner");
        bikaner.add("Nokha");
        bikaner.add("Sridungargarh");
        bikaner.add("Kolayat");
        bikaner.add("Lunkaransar");
        bikaner.add("Khajuwala");
        bikaner.add("Chhatargarh");
        bikaner.add("Poogal");

        bundi=new ArrayList<>();
        bundi.add("Select Tehsil");
        bundi.add("Bundi");
        bundi.add("Hindoli");
        bundi.add("Nainwa");
        bundi.add("Keshoraipatan");
        bundi.add("Indragarh");

        chitorgarh=new ArrayList<>();
        chitorgarh.add("Select Tehsil");
        chitorgarh.add("Bari Sadri");
        chitorgarh.add("Begun");
        chitorgarh.add("Bhadesar");
        chitorgarh.add("Chittaurgarh");
        chitorgarh.add("Dungla");
        chitorgarh.add("Gangrar");
        chitorgarh.add("Kapasan");
        chitorgarh.add("Nimbahera");
        chitorgarh.add("Rashmi");
        chitorgarh.add("Rawatbhata");

        churu=new ArrayList<>();
        churu.add("Select Tehsil");
        churu.add("Churu");
        churu.add("Dungargarh");
        churu.add("Rajgarh");
        churu.add("Ratangarh");
        churu.add("Sardarshahar");
        churu.add("Sujangarh");
        churu.add("Taranagar");

        dausa=new ArrayList<>();
        dausa.add("Select Tehsil");
        dausa.add("Baswa");
        dausa.add("Dausa");
        dausa.add("Lalsot");
        dausa.add("Mahwa");
        dausa.add("Sikrai");

        dholpur=new ArrayList<>();
        dholpur.add("Select Tehsil");
        dholpur.add("Bari");
        dholpur.add("Baseri");
        dholpur.add("Dhaulpur");
        dholpur.add("Rajakhera");
        dholpur.add("Saipau");

        dungarpur=new ArrayList<>();
        dungarpur.add("Select Tehsil");
        dungarpur.add("Aspur");
        dungarpur.add("Dungarpur");
        dungarpur.add("Sagwara");
        dungarpur.add("Simalwara");

        hanumangarh=new ArrayList<>();
        hanumangarh.add("Select Tehsil");
        hanumangarh.add("Bhadra");
        hanumangarh.add("Hanumangarh");
        hanumangarh.add("Nohar");
        hanumangarh.add("Pilibanga");
        hanumangarh.add("Rawatsar");
        hanumangarh.add("Sangaria");
        hanumangarh.add("Tibi");

        jaipur=new ArrayList<>();
        jaipur.add("Select Tehsil");
        jaipur.add("Amber");
        jaipur.add("Bassi");
        jaipur.add("Chaksu");
        jaipur.add("Chomu");
        jaipur.add("Jaipur");
        jaipur.add("Jamwa Ramgarh");
        jaipur.add("Kotputli");
        jaipur.add("Mauzamabad");
        jaipur.add("Phagi");
        jaipur.add("Phulera (Hq. Sambhar)");
        jaipur.add("Sanganer");
        jaipur.add("Shahpura");
        jaipur.add("Viratnagar");

        jaisalmer=new ArrayList<>();
        jaisalmer.add("Select Tehsil");
        jaisalmer.add("Fatehgarh");
        jaisalmer.add("Jaisalmer");
        jaisalmer.add("Pokaran");

        jalor=new ArrayList<>();
        jalor.add("Select Tehsil");
        jalor.add("Ahore");
        jalor.add("Bagora");
        jalor.add("Bhinmal");
        jalor.add("Jalor");
        jalor.add("Raniwara");
        jalor.add("Sanchore");
        jalor.add("Sayla");

        nagaur=new ArrayList<>();
        nagaur.add("Select Tehsil");
        nagaur.add("Degana");
        nagaur.add("Didwana");
        nagaur.add("Jayal");
        nagaur.add("Kheenvsar");
        nagaur.add("Ladnu");
        nagaur.add("Makrana");
        nagaur.add("Merta");
        nagaur.add("Nagaur");
        nagaur.add("Nawa");
        nagaur.add("Parbatsar");

        jodhpur=new ArrayList<>();
        jodhpur.add("Select Tehsil");
        jodhpur.add("Bhopalgarh");
        jodhpur.add("Bilara");
        jodhpur.add("Jodhpur");
        jodhpur.add("Luni");
        jodhpur.add("Osian");
        jodhpur.add("Phalodi");
        jodhpur.add("Shergarh");

        District.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==1)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,Ajmer);
                else if (position==2)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,alwar);
                else if (position==3)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,banswara);
                else if (position==4)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,baran);
                else if (position==5)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,barmer);
                else if (position==6)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,bharatpur);
                else if (position==7)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,bhilwara);
                else if (position==8)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,bikaner);
                else if (position==9)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,bundi);
                else if (position==10)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,chitorgarh);
                else if (position==11)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,churu);
                else if (position==12)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,dausa);
                else if (position==13)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,dholpur);
                else if (position==14)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,dungarpur);
                else if (position==15)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,hanumangarh);
                else if (position==16)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,jaipur);
                else if (position==17)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,jaisalmer);
                else if (position==18)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,jalor);
                else if (position==24)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,nagaur);
                else if (position==21)
                    arrayAdapter_th=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,jodhpur);
                tehsil.setAdapter(arrayAdapter_th);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        education=findViewById(R.id.education);
        List<String> edu=new ArrayList<String>();
        edu.add("select highest education");
        edu.add("M.Sc.");
        edu.add("M.A.");
        edu.add("M.C.A.");
        edu.add("B.Sc.");
        edu.add("B.A.");
        edu.add("B.C.A.");

        ArrayAdapter<String> arrayAda=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,edu);
        arrayAda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        education.setAdapter(arrayAda);

        education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                education.setSelection(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    private void store(String s1, String s2, String s3, String s4, String s5, String s6) {
        user.setEmail(s1);
        user.setPass(s2);
        user.setUsername(s3);
        user.setAddress(s4);
        user.setEducation(s5);
        user.setTehsil(s6);
        databaseReference.push().setValue(user);
        Toast.makeText(this, "data inserted", Toast.LENGTH_SHORT).show();
//        Map<String,Object> data=new HashMap<>();
//        data.put("Email",s1);
//        data.put("password",s2);
//        data.put("name",s3);
//        data.put("address",s4);
//        data.put("education",s5);
//        data.put("tehsil",s6);
//        databaseReference.push().updateChildren(data);
    }

    void register() {
        final String s1=Email.getText().toString().trim();
        final String s2=pswrd.getText().toString().trim();
        final String s3=username.getText().toString().trim();
        final String s4=address.getText().toString().trim();
        final String s5=education.getSelectedItem().toString().trim();
        final String s6=contact.getText().toString().trim();
        final String s7=spinner.getSelectedItem().toString().trim();
        final String s9=State.getSelectedItem().toString().trim();
        final String s11=District.getSelectedItem().toString().trim();
        final String s12=tehsil.getSelectedItem().toString().trim();
        final String s13=pin.getText().toString().trim();
//        final String s10=pinCode.getText().toString().trim();

        if (!TextUtils.isEmpty(s1)&&!TextUtils.isEmpty(s2)&&!TextUtils.isEmpty(s3)&&!TextUtils.isEmpty(s4)&&!TextUtils.isEmpty(s6))
        {
            mAuth.createUserWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    String uid=mAuth.getCurrentUser().getUid();
                                    DatabaseReference current_user=databaseReference.child(uid);

                                    current_user.child("Email").setValue(s1);
                                    current_user.child("Password").setValue(s2);
                                    current_user.child("name").setValue(s3);
                                    current_user.child("address").setValue(s4);
                                    current_user.child("education").setValue(s5);
                                    current_user.child("Contact").setValue(s6);
                                    current_user.child("Post").setValue(s7);
                                    current_user.child("State").setValue(s9);
                                    current_user.child("District").setValue(s11);
                                    current_user.child("Tehsil").setValue(s12);
                                    current_user.child("pin").setValue(s13);

                                    Toast.makeText(RegisterActivity.this, "Registered Successfully. Please check your email for verification", Toast.LENGTH_SHORT).show();
                                    Intent signIntent=new Intent(RegisterActivity.this,LoginActivity.class);
                                    startActivity(signIntent);
                                }
                                else
                                {
                                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                    else
                    if (task.getException() instanceof FirebaseAuthWeakPasswordException)
                        Toast.makeText(RegisterActivity.this, "Password too weak.\nTry Again!!", Toast.LENGTH_SHORT).show();
                    else if (task.getException() instanceof FirebaseAuthUserCollisionException)
                        Toast.makeText(RegisterActivity.this, "User Already Exists.\nTry Again!!", Toast.LENGTH_SHORT).show();
                    else if (task.getException() instanceof FirebaseNetworkException)
                        Toast.makeText(RegisterActivity.this, "Internet not available.\nTry Again!!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
