package com.example.budgetmanagementapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FirbaseAuthenticationClass extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    final DatabaseReference reference = database.getReference("Users");

    public void LoginUser(String EMAIL, String PASSWORD, final Activity activity, final ProgressDialog progressDialog) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid = mAuth.getCurrentUser().getUid();

                            reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    //save session
                                    //saving value true for session
                                    Save.save(activity,"session","true");
                                    activity.startActivity(new Intent(activity, MainActivity.class));

                                    activity.finish();
                                        progressDialog.dismiss();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });


                        }



                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

//                    RegisterUser(userGmail, userPassword, Contact, Name,  imagePath, lati, loni, addressString, CompleteProfileActivity.this, progressDialog);


    public void RegisterUser(final String userGmail, String userPassword, final String contact, final String name, final String imagePath, final String address, final CompleteProActivity completeProActivity, final ProgressDialog progressDialog) {
      FirebaseAuth.getInstance().createUserWithEmailAndPassword(userGmail,userPassword)
              .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()) {
                          final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                          StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/" + FirebaseDatabase.getInstance().getReference().child("Users").push().getKey());
                          storageReference.putFile(Uri.parse(imagePath)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                              @Override
                              public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                  Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                                  while (!uriTask.isSuccessful()) ;
                                  Uri downloadUri = uriTask.getResult();

//                                  UserAttr userAttr = new UserAttr();
//                                  userAttr.setEmail(userGmail);
//                                  userAttr.setContact(contact);
//                                  userAttr.setName(name);
//                                  userAttr.setAddress(address);
//                                  userAttr.setId(uid);
//                                  userAttr.setImageUrl(downloadUri.toString());
//                                  reference.child(uid).setValue(userAttr);
//                                  Toast.makeText(completeProfileActivity, "Account Created", Toast.LENGTH_SHORT).show();
//                                  completeProfileActivity.startActivity(new Intent(completeProfileActivity, MainActivity.class));
//                                  progressDialog.dismiss();

                              }
                          });


                      }
                  }
              }).addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
              Toast.makeText(completeProActivity, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
              progressDialog.dismiss();
          }
      });
    }
}
