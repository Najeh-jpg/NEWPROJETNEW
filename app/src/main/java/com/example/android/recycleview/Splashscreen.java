package com.example.android.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.android.recycleview.databinding.ActivitySplashscreenBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Splashscreen extends AppCompatActivity {
    private ActivitySplashscreenBinding binding;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashscreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        delay();
    }
    public void delay(){
        final Handler handler = new Handler();
        handler.postDelayed(this::isConnected, 3000);
    }
    private void isConnected() {

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(Splashscreen.this, HomeActivity.class));
            finish();
        } else {
            startActivity(new Intent(Splashscreen.this , Login.class));
            finish();
        }

    }
}
