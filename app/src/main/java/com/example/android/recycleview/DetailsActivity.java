package com.example.android.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView title,texte,desc;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_details);
        setContentView(R.layout.activity_details);
        //extras=getIntent().getExtras();
        //title=findViewById(R.id.title_proteine);
        //texte=findViewById(R.id.texte_proteine);
        //desc=findViewById(R.id.desc_proteine);
       // imageView= findViewById(R.id.img_proteine);


        //if (extras!=null){
          //  title.setText(extras.getString("title"));
           // texte.setText(extras.getString("texte"));
            //desc.setText(extras.getString("desc"));
            //imageView.setImageResource(extras.getInt("imageView"));
        }

    }

