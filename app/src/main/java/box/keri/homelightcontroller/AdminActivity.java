package box.keri.homelightcontroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by kadak on 15.12.2017.
 */

public class AdminActivity extends Activity {
    Button addbtn, delbtn, back;
    TextView email, password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        addbtn = findViewById(R.id.addbutton);
        delbtn = findViewById(R.id.deletebutton);
        email = findViewById(R.id.adduseremail);
        password = findViewById(R.id.adduserpassword);
        back = findViewById(R.id.backbtn);
        addbtn.setEnabled(true);
        delbtn.setEnabled(true);
        email.setEnabled(true);
        password.setEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                added();
            }
        });
        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleted();
            }
        });
    }
    public void added() {
        String em = email.getText().toString();
        String pw = password.getText().toString();
        email.setEnabled(false);
        password.setEnabled(false);
        addbtn.setEnabled(false);
        addbtn.setText("Loading");
        delbtn.setEnabled(false);
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(em, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:onComplete:");
                            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            final DatabaseReference myRef = database.getReference("users");
                            String uid = user.getUid();
                            final DatabaseReference uidadd = myRef.child(uid);
                            uidadd.setValue("y");
                            Toast.makeText(AdminActivity.this, "Succsess", Toast.LENGTH_SHORT).show();
                            Intent back = new Intent(getApplicationContext(), LogInActivity.class);
                            startActivity(back);
                            finish();
                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText(AdminActivity.this, "failed", Toast.LENGTH_SHORT).show();
                            Intent again = new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(again);
                            finish();
                        }

                        // ...
                    }
                });
    }
    public void deleted() {
        String em = email.getText().toString();
        String pw = password.getText().toString();
        addbtn.setEnabled(false);
        delbtn.setEnabled(false);
        delbtn.setText("Loading");
        email.setEnabled(false);
        password.setEnabled(false);
        mAuth.signInWithEmailAndPassword(em, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        final DatabaseReference myRef = database.getReference("users");
                        String uid = currentUser.getUid();
                        final DatabaseReference uidadd = myRef.child(uid);
                        uidadd.removeValue();
                        currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Succsessfully deleted!", Toast.LENGTH_SHORT).show();
                                    Intent newact = new Intent(getApplicationContext(), LogInActivity.class);
                                    startActivity(newact);
                                    finish();
                                } else {
                                    Toast.makeText(AdminActivity.this, "Deleting failed!", Toast.LENGTH_SHORT).show();
                                    Intent fail=new Intent(getApplicationContext(), AdminActivity.class);
                                    startActivity(fail);
                                    finish();
                                }
                            }
                        });
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(AdminActivity.this, "identification failed", Toast.LENGTH_SHORT).show();
                            Intent fail=new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(fail);
                            finish();
                        }
                    }
                });
    }
}
