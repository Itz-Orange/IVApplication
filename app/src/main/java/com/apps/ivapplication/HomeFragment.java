package com.apps.ivapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button btncall, btnemail, btntext, btnfacebook, btnwebsite;
    private TextView logout, welcomeTV;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btncall = (Button) view.findViewById(R.id.callbtn);
        btncall.setOnClickListener(this);
        btnemail = (Button) view.findViewById(R.id.emailbtn);
        btnemail.setOnClickListener(this);
        btntext = (Button) view.findViewById(R.id.textbtn);
        btntext.setOnClickListener(this);
        btnfacebook = (Button) view.findViewById(R.id.facebookbtn);
        btnfacebook.setOnClickListener(this);
        btnwebsite = (Button) view.findViewById(R.id.websitebtn);
        btnwebsite.setOnClickListener(this);
        logout = (TextView) view.findViewById(R.id.logout);

        return view;


    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.callbtn:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:6023180426"));
                startActivity(intent);
                break;
            case R.id.textbtn:
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("sms:6023180426"));
                startActivity(i);
                break;
            case R.id.emailbtn:
                Intent i2 = new Intent(Intent.ACTION_SENDTO);
                i2.setData(Uri.parse("mailto:info@advancemobileivtherapy.com"));
                startActivity(i2);
                break;
            case R.id.facebookbtn:
                Intent i1 = new Intent(Intent.ACTION_VIEW);
                i1.setData(Uri.parse("https://www.facebook.com/advancedmobileIV/"));
                startActivity(i1);
                break;
            case R.id.websitebtn:
                Intent i3 = new Intent(Intent.ACTION_VIEW);
                i3.setData(Uri.parse("https://advancedmobileivtherapy.com/"));
                startActivity(i3);
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent i4 = new Intent(getActivity(), MainActivity.class);
                startActivity(i4);

        }

    }
}
