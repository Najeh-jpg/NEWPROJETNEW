package com.example.android.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.example.android.recycleview.Model.User;
import com.example.android.recycleview.databinding.ActivityInscriptionBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class InscriptionActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1000;
    private ActivityInscriptionBinding binding;
    private Uri filePath;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefrence;
    private FirebaseStorage mStorage;
    private User mUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInscriptionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initFireBAse();
        binding.addimage.setOnClickListener(this::choosePicture);
        binding.inscriBtn.setOnClickListener(v -> save());
    }

    private void save() {
        if (!isEmpty()) {
            String mail = binding.mailInscri.getText().toString();
            String pwd = binding.pwdInscri.getText().toString();

            if (validEmail() && validPassword() && valideImage()) {
                binding.progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(mail, pwd).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        mUser.setUid(mAuth.getCurrentUser().getUid());
                        mUser.setMail(mail);
                        uploadImage();

                    } else {
                        binding.progressBar.setVisibility(View.GONE);
                        Snackbar.make(binding.getRoot(), task.getException().getMessage(), Snackbar.LENGTH_LONG);
                    }
                });
            }
        }

    }

    private void uploadImage() {
        StorageReference child = mStorage.getReference().child(mUser.getUid());
        child.putFile(filePath).continueWithTask(task -> child.getDownloadUrl()).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                mUser.setUrl(task.getResult().toString());
                saveUser();
            }
        });

    }

    private void saveUser() {
        String nom = binding.nomInscri.getText().toString();
        mUser.setNom(nom);
        mUser.setPrenom(binding.prenomInscri.getText().toString());
        mUser.setAdmin(1);

        mRefrence.child(mUser.getUid()).setValue(mUser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    sendVerificationEmail();
                    binding.progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(InscriptionActivity.this, MainActivity.class));
                    finish();
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    Snackbar.make(binding.getRoot(), task.getException().getMessage(), Snackbar.LENGTH_LONG);
                }

            }
        });

    }

    private void sendVerificationEmail() {
        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(task -> Log.e("mailsend", task.toString()));
    }

    public void choosePicture(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            getContentResolver().takePersistableUriPermission(filePath, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                binding.addimage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private boolean isEmpty() {
        boolean empty = false;

        if (TextUtils.isEmpty(binding.mailInscri.getText())) {
            empty = true;
            binding.mailInscri.setError("Champ Vide");
        }
        if ((TextUtils.isEmpty(binding.pwdInscri.getText()))) {
            empty = true;
            binding.pwdInscri.setError("Champ Vide");
        }
        if (TextUtils.isEmpty(binding.nomInscri.getText())) {
            empty = true;
            binding.nomInscri.setError("Champ Vide");
        }
        if (TextUtils.isEmpty(binding.prenomInscri.getText())) {
            empty = true;
            binding.prenomInscri.setError("Champ Vide");
        }
        return empty;
    }

    private boolean validEmail() {
        boolean valid = true;
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.mailInscri.getText()).matches()) {
            valid = false;
            binding.mailInscri.setError(" entrer un email Valide");
        }

        return valid;
    }

    private boolean validPassword() {
        boolean valide = true;
        if (binding.pwdInscri.getText().length() < 6) {
            valide = false;
            binding.pwdInscri.setError("minimum 6 caractéres ");
        }
        return valide;
    }

    private boolean valideImage() {
        boolean valid = true;
        if (filePath == null) {
            valid = false;
            Snackbar.make(binding.addimage, "Ajouter une Image de l'offre ", Snackbar.LENGTH_LONG);
        }
        return valid;
    }

    private void initFireBAse() {
        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance();

        mRefrence = mDatabase.getReference("admin");

        mStorage = FirebaseStorage.getInstance();

    }
}
