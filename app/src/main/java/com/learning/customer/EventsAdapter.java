package com.learning.customer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EventsAdapter extends ArrayAdapter<Events> {

    private Activity context;
    private List<Events> list;
    private DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Events");;

    public EventsAdapter(Activity context,List<Events> list)
    {   super(context,R.layout.single_info_event_layout,list);
        this.context=context;
        this.list=list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View listviewitem=layoutInflater.inflate(R.layout.single_info_event_layout,null,true);
        TextView NameD=(TextView)listviewitem.findViewById(R.id.NameD);
        TextView OrganiserD=(TextView)listviewitem.findViewById(R.id.OrganiserD);
        TextView VenueD=(TextView)listviewitem.findViewById(R.id.VenueD);
        TextView StartdateD=(TextView)listviewitem.findViewById(R.id.StartdateD);
        TextView StarttimeD=(TextView)listviewitem.findViewById(R.id.StarttimeD);
        TextView EnddateD=(TextView)listviewitem.findViewById(R.id.EnddateD);
        TextView PhoneD=(TextView)listviewitem.findViewById(R.id.phoneD);
        TextView StatusD=(TextView)listviewitem.findViewById(R.id.statusD);
        Button buttonRegister = listviewitem.findViewById(R.id.buttonRegister);
        Button buttonNavigate = listviewitem.findViewById(R.id.buttonNavigate);



        final Events event=list.get(position);

        NameD.setText(event.getName());
        OrganiserD.setText(event.getOrganiser());
        VenueD.setText(event.getVenue());
        StartdateD.setText(event.getStartdate());
        StarttimeD.setText(event.getStarttime());
        EnddateD.setText(event.getEnddate());
        PhoneD.setText(event.getContactinfo());
        StatusD.setText(event.getStatus());

        buttonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s="Tech+Park+SRM+Potheri+Chennai";
                String uri=String.format("google.navigation:q=%s",s);
                Uri gmmIntentUri = Uri.parse(uri);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                /*if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    context.startActivity(mapIntent);
                }*/
                context.startActivity(mapIntent);

            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(getContext(),EventRegistration.class);
                    context.startActivity(intent);
            }
        });





        return listviewitem;
    }


}