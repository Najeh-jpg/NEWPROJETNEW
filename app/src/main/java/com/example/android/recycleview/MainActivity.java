package com.example.android.recycleview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    TextView fulName,email,phone,verifyMsg;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser firebaseUser;
    String userID;
    Button resendcode;
    Button resetPassLocal,changeProfileImage;
    Button goOffers;
    FirebaseUser user;
    ImageView profileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone = findViewById(R.id.profilphone);
        fulName = findViewById(R.id.profilname);
        email = findViewById(R.id.profilemail);
        resetPassLocal= findViewById(R.id.resetPasswordLocal);
        goOffers= findViewById(R.id.btn_go);

        profileImage = findViewById(R.id.profilImage);
        changeProfileImage = findViewById(R.id.changeProfile);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        resendcode= findViewById(R.id.resendcode);
        verifyMsg = findViewById(R.id.verifyMsg);


        userID = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

        if (user.isEmailVerified()){
            resendcode.setVisibility(View.VISIBLE);
            resendcode.setVisibility(View.VISIBLE);

            resendcode.setOnClickListener(v -> user.sendEmailVerification().addOnSuccessListener(aVoid -> Toast.makeText(v.getContext(),"Verification Email Has bee Sent", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Log.d( "TAG","onFailure: Email not sent" + e.getMessage())));
        }



        final DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, (documentSnapshot, e) -> {
            if (documentSnapshot.exists()) {
                phone.setText(documentSnapshot.getString("phone"));
                fulName.setText(documentSnapshot.getString("fName"));
                email.setText(documentSnapshot.getString("email"));
            } else {
                Log.d("tag", "onEvent: Document do not exists");
            }
        });

        resetPassLocal.setOnClickListener(v -> {



            final EditText resetPassword = new EditText(v.getContext());

            AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
            passwordResetDialog.setTitle("Reset Password?");
            passwordResetDialog.setMessage("Entrer new Password> 6 characters long.");
            passwordResetDialog.setView(resetPassword);


            passwordResetDialog.setPositiveButton("Yes", (dialog, which) -> {
                // extract the email and send reset link

                String newPassword  = resetPassword.getText().toString();
                user.updatePassword(newPassword).addOnSuccessListener(aVoid -> Toast.makeText(MainActivity.this, "Password Reset Successfully.",Toast.LENGTH_SHORT).show()).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Password Reset Failed",Toast.LENGTH_SHORT).show();
                    }
                });

            });
            passwordResetDialog.setNegativeButton("No", (dialog, which) -> {
                dialog.dismiss();
                //close

            });
            passwordResetDialog.create().show();
        });


        changeProfileImage.setOnClickListener(v -> {
            //open gallery
            Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(openGalleryIntent, 1000);
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1000){
            if (resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                profileImage.setImageURI(imageUri);
            }
        }
    }

  //  public void setGoOffers(Button goOffers) {
     //   this.goOffers = goOffers;
      //  goOffers.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  Intent offers = new Intent(MainActivity.this,HomeActivity.class);
               // startActivity(offers);

           // }
       // });
   // }

    public void goOffers(View view) {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
    //public void Logout(View view) {
       // FirebaseAuth.getInstance().signOut();
       // startActivity(new Intent(getApplicationContext(),Login.class));
       // finish();
   // }
}
