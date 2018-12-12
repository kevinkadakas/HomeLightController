package box.keri.homelightcontroller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by kadak on 15.12.2017.
 */

public class SettingsActivity extends Activity {
    EditText Ipbox,x ,y, refresh;
    Button back, save;
    private SharedPreferences ysaved;
    private SharedPreferences xsaved;
    private SharedPreferences loginPreferencesfip;
    private SharedPreferences refreshSaved;
    private SharedPreferences.Editor refreshEdit;
    private SharedPreferences spinnerSave;
    private SharedPreferences.Editor spinnerEdit;
    private SharedPreferences.Editor yedit;
    private SharedPreferences.Editor xedit;
    private SharedPreferences.Editor loginPrefsEditorfip;
    private SharedPreferences loc;
    private SharedPreferences.Editor locEdit;
    private Spinner spinner;
    int valueof;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        Ipbox = findViewById(R.id.ipBox);
        back = findViewById(R.id.button2);
        save = findViewById(R.id.button);
        x = findViewById(R.id.locX);
        y = findViewById(R.id.LocY);
        spinner = findViewById(R.id.spinner2);
        refresh = findViewById(R.id.editText);

        loginPreferencesfip = getSharedPreferences("loginPrefss", MODE_PRIVATE);
        loginPrefsEditorfip = loginPreferencesfip.edit();

        ysaved = getSharedPreferences("setingsy", MODE_PRIVATE);
        yedit = ysaved.edit();

        xsaved = getSharedPreferences("setingsx", MODE_PRIVATE);
        xedit = xsaved.edit();

        refreshSaved = getSharedPreferences("RefreshRate", MODE_PRIVATE);
        refreshEdit = refreshSaved.edit();

        spinnerSave = getSharedPreferences("spinnerCh", MODE_PRIVATE);
        spinnerEdit = spinnerSave.edit();
        valueof = refreshSaved.getInt("RefreshRate",0);

        loc = getSharedPreferences("location", MODE_PRIVATE);
        locEdit = loc.edit();

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.locationA, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);

        if (loc.getString("location", "").equals("Tallinn")) {
            spinner.setSelection(adapter1.getPosition("Tallinn"));
        } else if (loc.getString("location", "").equals("Tartu")) {
            spinner.setSelection(adapter1.getPosition("Tartu"));
        } else if (loc.getString("location", "").equals("Pärnu")) {
            spinner.setSelection(adapter1.getPosition("Pärnu"));
        } else if (loc.getString("location", "").equals("Custom")) {
            spinner.setSelection(adapter1.getPosition("Custom"));
        }

        if (valueof == 0) {
            refresh.setText("120");
        } else {
            refresh.setText(String.valueOf(refreshSaved.getInt("RefreshRate",0)));
        }

        Ipbox.setText(loginPreferencesfip.getString("ip", ""));

        spinner.setSelection(spinnerSave.getInt("spinnerCh",0));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if ((String.valueOf(spinner.getSelectedItem())).equals("Tallinn")) {
                    x.setText("59.43696079999");
                    y.setText("24.75357469999");
                    x.setVisibility(View.INVISIBLE);
                    y.setVisibility(View.INVISIBLE);
                }
                if ((String.valueOf(spinner.getSelectedItem())).equals("Tartu")) {
                    x.setText("58.36069739999");
                    y.setText("26.72775679999");
                    x.setVisibility(View.INVISIBLE);
                    y.setVisibility(View.INVISIBLE);
                }
                if ((String.valueOf(spinner.getSelectedItem())).equals("Pärnu")) {
                    x.setText("58.3944623");
                    y.setText("24.51137010000");
                    x.setVisibility(View.INVISIBLE);
                    y.setVisibility(View.INVISIBLE);
                }
                if ((String.valueOf(spinner.getSelectedItem())).equals("Custom")) {
                    x.setText(xsaved.getString("xsaved11", ""));
                    y.setText(ysaved.getString("ysaved11", ""));
                    x.setVisibility(View.VISIBLE);
                    y.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(back);
                finish();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipaddress = Ipbox.getText().toString();
                loginPrefsEditorfip.putString("ip", ipaddress);
                loginPrefsEditorfip.commit();
                String xsaved1 = x.getText().toString();
                xedit.putString("xsaved11", xsaved1);
                xedit.commit();
                String ysaved1 = y.getText().toString();
                yedit.putString("ysaved11", ysaved1);
                yedit.commit();
                String ref = refresh.getText().toString();
                int fRef = Integer.parseInt(ref);
                refreshEdit.putInt("RefreshRate", fRef);
                refreshEdit.commit();
                int spinnerpos = spinner.getSelectedItemPosition();
                spinnerEdit.putInt("spinnerCh", spinnerpos);
                spinnerEdit.commit();
                Toast.makeText(SettingsActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                Intent saved = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(saved);
                finish();
            }
        });
    }
}
