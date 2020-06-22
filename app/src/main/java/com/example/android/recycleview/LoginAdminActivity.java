package com.example.android.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.example.android.recycleview.databinding.ActivityLoginAdminBinding;
import com.example.android.recycleview.databinding.ActivityLoginBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class LoginAdminActivity extends AppCompatActivity {
    private ActivityLoginAdminBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginAdminBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.loginBtntxt.setOnClickListener(this::onClick);
        binding.inscriBtn.setOnClickListener(v -> startActivity(new Intent(LoginAdminActivity.this, InscriptionActivity.class)));
    }

    private void Login() {
        if (!isEmpty()) {
            String mail = binding.emailInput.getText().toString();
            String password = binding.passwordInput.getText().toString();
            if (validEmail() && validPassword()) {

                mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(LoginAdminActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Snackbar.make(binding.getRoot(), task.getException().getMessage(), Snackbar.LENGTH_LONG);
                    }

                });
            }
        }

    }

    private boolean isEmpty() {
        boolean empty = false;

        if (TextUtils.isEmpty(binding.emailInput.getText())) {
            empty = true;
            binding.emailInput.setError("Champ Vide");
        }
        if ((TextUtils.isEmpty(binding.passwordInput.getText()))) {
            empty = true;
            binding.passwordInput.setError("Champ Vide");
        }
        return empty;
    }

    private boolean validEmail() {
        boolean valid = true;
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailInput.getText()).matches()) {
            valid = false;
            binding.emailInput.setError(" entrer un email Valide");
        }

        return valid;
    }

    private boolean validPassword() {
        boolean valide = true;
        if (binding.passwordInput.getText().length() < 6) {
            valide = false;
            binding.passwordInput.setError("minimum 6 caractéres ");
        }
        return valide;
    }

    private void onClick(View v) {
        Login();
    }
}
