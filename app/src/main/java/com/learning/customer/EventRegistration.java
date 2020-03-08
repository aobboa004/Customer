package com.learning.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventRegistration extends AppCompatActivity {
    Button registerevent;
    EditText NameE,CollegenameE,CollegeStudentIdE,EmailE,PhoneE;
    DatabaseReference ref;
    String idj;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventregistration);
        registerevent=findViewById(R.id.buttonRegisterEvent);
        NameE=findViewById(R.id.eventregName);
        CollegenameE=findViewById(R.id.eventregCollegeName);
        CollegeStudentIdE=findViewById(R.id.eventregStudentID);
        EmailE=findViewById(R.id.eventregEmail);
        PhoneE=findViewById(R.id.eventregPhoneNumber);


        Intent intent =getIntent();
         idj= intent.getStringExtra(EventsAdapter.Event_ID);

       ref=FirebaseDatabase.getInstance().getReference("client");

        registerevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveClient();
            }
        });
    }

    private void saveClient() {
        String NameEj=NameE.getText().toString();
        String CollegenameEj=CollegenameE.getText().toString();
        String CollegeStudentIdEj=CollegeStudentIdE.getText().toString();
        String EmailEj=EmailE.getText().toString();
        String PhoneEj=PhoneE.getText().toString();

        if(NameEj.isEmpty())
        {
            NameE.setError("Name is required");
            NameE.requestFocus();
            return;
        }
        if(CollegenameEj.isEmpty())
        {
            CollegenameE.setError("College Name is required");
            CollegenameE.requestFocus();
            return;
        }
        if(CollegeStudentIdEj.isEmpty())
        {
            CollegeStudentIdE.setError("Student ID is required");
            CollegeStudentIdE.requestFocus();
            return;
        }
        if(EmailEj.isEmpty())
        {
            EmailE.setError("Email is required");
            EmailE.requestFocus();
            return;
        }
        if(PhoneEj.isEmpty())
        {
            PhoneE.setError("Phone Number is required");
            PhoneE.requestFocus();
            return;
        }

        String id=ref.push().getKey();
        Client client=new Client(id,NameEj,CollegenameEj,CollegeStudentIdEj,EmailEj,PhoneEj,idj);
        ref.child(id).setValue(client);
        Toast toast=Toast.makeText(EventRegistration.this,"You are successfully registered",Toast.LENGTH_SHORT);
        toast.show();

            }
}
