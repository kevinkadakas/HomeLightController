package box.keri.homelightcontroller;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by kadak on 15.12.2017.
 */

public class LogInActivity extends Activity {
    EditText pwTxt , emTxt;
    Button login;
    ProgressBar PB;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private SharedPreferences loginPreferencess;
    private SharedPreferences.Editor loginPrefsEditorr;
    private SharedPreferences lang;
    private SharedPreferences.Editor langEdit;
    private Boolean saveLoginn;
    private Spinner spinner;
        @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
            pwTxt = findViewById(R.id.passwordText);
            emTxt = findViewById(R.id.emailText);
            login = findViewById(R.id.loginBtn);
            saveLoginCheckBox = findViewById(R.id.checkBox);
            spinner = findViewById(R.id.spinner);
            PB = findViewById(R.id.progressBar);
            emTxt.setEnabled(true);
            pwTxt.setEnabled(true);
            login.setEnabled(true);
            login.setText("LogIn");
            PB.setVisibility(View.INVISIBLE);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.country_arrays, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
            loginPrefsEditor = loginPreferences.edit();
            saveLogin = loginPreferences.getBoolean("saveLogin", false);
            loginPreferencess = getSharedPreferences("loginPrefss", MODE_PRIVATE);
            loginPrefsEditorr = loginPreferencess.edit();
            saveLoginn = loginPreferencess.getBoolean("saveLoginn", false);
            if (saveLogin == true) {
                emTxt.setText(loginPreferences.getString("username", ""));
                pwTxt.setText(loginPreferences.getString("password", ""));
                saveLoginCheckBox.setChecked(true);
            }

            lang = getSharedPreferences("Lang", MODE_PRIVATE);
            langEdit = lang.edit();


            mAuth = FirebaseAuth.getInstance();

            if (lang.getString("Lang","").equals("Estonian")) {
                spinner.setSelection(adapter.getPosition("Estonian"));
            } else if (lang.getString("Lang","").equals("Estonian")) {
                spinner.setSelection(adapter.getPosition("English"));
            } else {
                spinner.setSelection(adapter.getPosition("Estonian"));
            }

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if ((String.valueOf(spinner.getSelectedItem())).equals("Settings")) {
                        Intent Settings = new Intent(getApplicationContext(), SettingsActivity.class);
                        startActivity(Settings);
                        finish();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String em = emTxt.getText().toString();
                    String pw = pwTxt.getText().toString();
                    if (em.equals("manager") & pw.equals("KeRiManagerA")) {
                        Intent admin = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(admin);
                    } else {
                        if (saveLoginCheckBox.isChecked()) {
                            loginPrefsEditor.putBoolean("saveLogin", true);
                            loginPrefsEditor.putString("username", em);
                            loginPrefsEditor.putString("password", pw);
                            loginPrefsEditor.commit();
                        } else {
                            loginPrefsEditor.clear();
                            loginPrefsEditor.commit();
                        }
                        clicked();
                    }
                }
            });
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user != null) {

                        Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    } else {
                        Log.d(TAG, "onAuthStateChanged:signed_out");
                    }
                }
            };
    }
    public void clicked() {
        if(String.valueOf(spinner.getSelectedItem()).equals("Estonian")) {
            langEdit.putString("Lang","Estonian");
            langEdit.commit();
            String em = emTxt.getText().toString();
            String pw = pwTxt.getText().toString();
            emTxt.setEnabled(false);
            pwTxt.setEnabled(false);
            login.setEnabled(false);
            login.setText("Loading");
            PB.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(em, pw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                            emTxt.setEnabled(true);
                            pwTxt.setEnabled(true);
                            login.setEnabled(true);
                            login.setText("LogIn");
                            PB.setVisibility(View.INVISIBLE);
                            Intent main = new Intent(getApplicationContext(), Main_Estonian.class);
                            startActivity(main);
                            finish();
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail:failed", task.getException());
                                Intent failed = new Intent(getApplicationContext(), LogInActivity.class);
                                Toast.makeText(LogInActivity.this, "Failed, try again!", Toast.LENGTH_SHORT).show();
                                startActivity(failed);
                                finish();
                            }
                        }
                    });
        }
        if(String.valueOf(spinner.getSelectedItem()).equals("English")) {
            langEdit.putString("Lang","English");
            langEdit.commit();
            String em = emTxt.getText().toString();
            String pw = pwTxt.getText().toString();
            emTxt.setEnabled(false);
            pwTxt.setEnabled(false);
            login.setEnabled(false);
            login.setText("Loading");
            PB.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(em, pw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                            emTxt.setEnabled(true);
                            pwTxt.setEnabled(true);
                            login.setEnabled(true);
                            login.setText("LogIn");
                            PB.setVisibility(View.INVISIBLE);
                            Intent main = new Intent(getApplicationContext(), Main_English.class);
                            startActivity(main);
                            finish();
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail:failed", task.getException());
                                Intent failed = new Intent(getApplicationContext(), LogInActivity.class);
                                Toast.makeText(LogInActivity.this, "Failed, try again!", Toast.LENGTH_SHORT).show();
                                startActivity(failed);
                                finish();
                            }
                        }
                    });
        }

    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
