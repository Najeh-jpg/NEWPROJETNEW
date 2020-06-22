package com.example.android.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChoisirActivity extends AppCompatActivity {
     Button LinkAdmin,LinkYouser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choisir);
        LinkAdmin = findViewById(R.id.btn_admin);
        LinkYouser = findViewById(R.id.btn_user);

        LinkAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ChoisirActivity.this,InscriptionActivity.class);
                startActivity(intent);
            }
        });
        LinkYouser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ChoisirActivity.this,Register.class);
                startActivity(intent);
            }
        });

        }
    }