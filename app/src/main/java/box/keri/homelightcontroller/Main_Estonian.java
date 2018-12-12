package box.keri.homelightcontroller;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.content.BroadcastReceiver;
import android.view.Menu;
import android.view.MenuItem;
import android.text.Html;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Typeface;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by kadak on 15.12.2017.
 */

public class Main_Estonian extends Activity {
    ToggleButton hello, bye, btn2, btn1, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10;
    Button Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8, Button9, Button10, timeSet;
    TimePicker timePicker;
    TextView currentTemperatureField, weatherIcon;
    ImageView helloOn, helloOff, byeOn, byeOff, on1, on2, on3, on4, on5, on6, on7, on8, on9, on10, off1, off2, off3, off4, off5, off6, off7, off8, off9, off10, img1, img2, img3, img4, img5, img6, img7, img8, img9, img10;
    private boolean isChecked1 = false;
    private boolean isChecked2 = false;
    private boolean isChecked3 = false;
    private boolean isChecked4 = false;
    private boolean isChecked5 = false;
    private boolean isChecked6 = false;
    private boolean isChecked7 = false;
    private boolean isChecked8 = false;
    private boolean isChecked9 = false;
    private boolean isChecked10 = false;
    private SharedPreferences loginPreferencesy;
    private SharedPreferences loginPreferencesx;
    private SharedPreferences loginPreferencess;
    private SharedPreferences refreshSaved;
    private SharedPreferences.Editor refreshEdit;
    private SharedPreferences.Editor loginEditx;
    private SharedPreferences.Editor loginEdity;
    Typeface weatherFont;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    DatabaseReference users = myRef.child("lightcontroll");
    DatabaseReference button1 = users.child("wcbtn");
    DatabaseReference button2 = users.child("btn1");
    DatabaseReference button3 = users.child("btn2");
    DatabaseReference button4 = users.child("btn3");
    DatabaseReference button5 = users.child("btn4");
    DatabaseReference button6 = users.child("btn5");
    DatabaseReference button7 = users.child("btn6");
    DatabaseReference button8 = users.child("btn7");
    DatabaseReference button9 = users.child("btn8");
    DatabaseReference button10 = users.child("btn9");
    DatabaseReference button11 = users.child("btn11");
    DatabaseReference button22 = users.child("btn22");
    DatabaseReference button33 = users.child("btn33");
    DatabaseReference button44 = users.child("btn44");
    DatabaseReference button55 = users.child("btn55");
    DatabaseReference button66 = users.child("btn66");
    DatabaseReference button77 = users.child("btn77");
    DatabaseReference button88 = users.child("btn88");
    DatabaseReference button99 = users.child("btn99");
    DatabaseReference button1010 = users.child("btn1010");
    DatabaseReference tund = users.child("tund");
    DatabaseReference minut = users.child("minut");
    DatabaseReference time = users.child("time");
    PendingIntent pintent;
    AlarmManager am;
    Calendar cal;
    Context context;
    String state;
    Handler refresh;
    Runnable refreshTask;
    int INTERVAL;
    int valueof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__estonian);
        this.context = this;
        final Intent intent = new Intent(this.context, Receiver.class);
        hello = findViewById(R.id.hello);
        bye = findViewById(R.id.bye);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);
        Button1 = findViewById(R.id.butto1);
        Button2 = findViewById(R.id.butto2);
        Button3 = findViewById(R.id.butto3);
        Button4 = findViewById(R.id.butto4);
        Button5 = findViewById(R.id.butto5);
        Button6 = findViewById(R.id.butto6);
        Button7 = findViewById(R.id.butto7);
        Button8 = findViewById(R.id.butto8);
        Button9 = findViewById(R.id.butto9);
        Button10 = findViewById(R.id.butto10);
        img1 = findViewById(R.id.imageView);
        img3 = findViewById(R.id.imageView1);
        img10 = findViewById(R.id.imageView2);
        img9 = findViewById(R.id.imageView3);
        img8 = findViewById(R.id.imageView4);
        img2 = findViewById(R.id.imageView5);
        img4 = findViewById(R.id.imageView6);
        img7 = findViewById(R.id.imageView7);
        img6 = findViewById(R.id.imageView8);
        img5 = findViewById(R.id.imageView9);
        Button1.setVisibility(View.GONE);
        Button2.setVisibility(View.GONE);
        Button3.setVisibility(View.GONE);
        Button4.setVisibility(View.GONE);
        Button5.setVisibility(View.GONE);
        Button6.setVisibility(View.GONE);
        Button7.setVisibility(View.GONE);
        Button8.setVisibility(View.GONE);
        Button9.setVisibility(View.GONE);
        Button10.setVisibility(View.GONE);
        timeSet = findViewById(R.id.button3);
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        helloOn = findViewById(R.id.helloOn);
        byeOn = findViewById(R.id.byeOn);
        on1 = findViewById(R.id.btn1On);
        on2 = findViewById(R.id.btn2on);
        on3 = findViewById(R.id.btn3On);
        on4 = findViewById(R.id.btn4On);
        on5 = findViewById(R.id.btn5On);
        on6 = findViewById(R.id.btn6On);
        on7 = findViewById(R.id.btn7On);
        on8 = findViewById(R.id.btn8On);
        on9 = findViewById(R.id.btn9On);
        helloOff = findViewById(R.id.helloOff);
        byeOff = findViewById(R.id.byeOff);
        on10 = findViewById(R.id.btn10On);
        off1 = findViewById(R.id.btn1Off);
        off2 = findViewById(R.id.btn2off);
        off3 = findViewById(R.id.btn3off);
        off4 = findViewById(R.id.btn4off);
        off5 = findViewById(R.id.btn5off);
        off6 = findViewById(R.id.btn6off);
        off7 = findViewById(R.id.btn7Off);
        off8 = findViewById(R.id.btn8off);
        off9 = findViewById(R.id.btn9off);
        off10 = findViewById(R.id.btn10off);
        loginPreferencesx = getSharedPreferences("setingsx", MODE_PRIVATE);
        loginPreferencesy = getSharedPreferences("setingsy", MODE_PRIVATE);

        refreshSaved = getSharedPreferences("RefreshRate", MODE_PRIVATE);
        INTERVAL = ((1000 * 60) * (refreshSaved.getInt("RefreshRate", 0)));

        refreshSaved = getSharedPreferences("RefreshRate", MODE_PRIVATE);
        refreshEdit = refreshSaved.edit();

        valueof = refreshSaved.getInt("RefreshRate",0);

        if (valueof == 0) {
            refreshEdit.putInt("RefreshRate", 120);
            refreshEdit.commit();
        }

        loginEditx = loginPreferencesx.edit();
        loginEdity = loginPreferencesy.edit();


        if (loginPreferencesx.getString("xsaved11", "").equals("") && loginPreferencesy.getString("ysaved11", "").equals("")) {
            loginEditx.putString("xsaved11", "59.43696079999");
            loginEdity.putString("ysaved11", "24.75357469999");
            loginEditx.commit();
            loginEdity.commit();
        }

        refresh = new Handler();
        refreshTask = new Runnable() {
            @Override
            public void run() {
                weatherFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");
                currentTemperatureField = findViewById(R.id.current_temperature_field);
                weatherIcon = findViewById(R.id.weather_icon);
                weatherIcon.setTypeface(weatherFont);
                Function.placeIdTask asyncTask = new Function.placeIdTask(new Function.AsyncResponse() {
                    public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {
                        currentTemperatureField.setText(weather_temperature);
                        weatherIcon.setText(Html.fromHtml(weather_iconText));
                    }
                });
                asyncTask.execute((loginPreferencesx.getString("xsaved11", "")), (loginPreferencesy.getString("ysaved11", "")));
                refresh.postDelayed(refreshTask, INTERVAL);
            }
        };
        if (isChecked1 || isChecked2 || isChecked3 || isChecked4 || isChecked5 || isChecked6 || isChecked7 || isChecked8 || isChecked9 || isChecked10) {
            byeOff.setVisibility(View.GONE);
            byeOn.setVisibility(View.VISIBLE);
        } else {
            byeOn.setVisibility(View.GONE);
            byeOff.setVisibility(View.VISIBLE);
        }

        if (isChecked2 || isChecked4 || isChecked7) {
            helloOff.setVisibility(View.GONE);
            helloOn.setVisibility(View.VISIBLE);
        } else {
            helloOn.setVisibility(View.GONE);
            helloOff.setVisibility(View.VISIBLE);
        }

        bye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                new Background_get().execute("Led1=0");
                new Background_get().execute("Led10=0");
                new Background_get().execute("Led3=0");
                new Background_get().execute("Led4=0");
                new Background_get().execute("Led5=0");
                new Background_get().execute("Led6=0");
                new Background_get().execute("Led7=0");
                new Background_get().execute("Led8=0");
                new Background_get().execute("Led9=0");
                final Handler handler = new Handler();
                Timer timer = new Timer();
                TimerTask testing = new TimerTask() {
                    public void run() {
                        handler.post(new Runnable() {
                            public void run() {
                                new Background_get().execute("Led2=0");
                            }
                        });
                    }
                };
                timer.schedule(testing, 30000);
            }
        });
        hello.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                new Background_get().execute("Led2=1");
                new Background_get().execute("Led7=1");
                new Background_get().execute("Led4=1");
            }
        });
        btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    new Background_get().execute("Led1=1");
                } else {
                    new Background_get().execute("Led1=0");
                }
            }
        });

        btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    new Background_get().execute("Led2=1");
                } else {
                    new Background_get().execute("Led2=0");
                }
            }
        });

        btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    new Background_get().execute("Led3=1");
                } else {
                    new Background_get().execute("Led3=0");
                }
            }
        });

        btn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    new Background_get().execute("Led4=1");
                } else {
                    new Background_get().execute("Led4=0");
                }
            }
        });

        btn5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    new Background_get().execute("Led5=1");
                } else {
                    new Background_get().execute("Led5=0");
                }
            }
        });

        btn6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    new Background_get().execute("Led6=1");
                } else {
                    new Background_get().execute("Led6=0");
                }
            }
        });

        btn7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    new Background_get().execute("Led7=1");
                } else {
                    new Background_get().execute("Led7=0");
                }
            }
        });

        btn8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    new Background_get().execute("Led8=1");
                } else {
                    new Background_get().execute("Led8=0");
                }
            }
        });
        btn9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    new Background_get().execute("Led9=1");
                } else {
                    new Background_get().execute("Led9=0");
                }
            }
        });

        btn10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    new Background_get().execute("Led10=1");
                } else {
                    new Background_get().execute("Led10=0");
                }
            }
        });

        timeSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (state.equals("ready")) {
                    timeSet.setText("Sea tuled");
                    btn1.setVisibility(View.INVISIBLE);
                    btn2.setVisibility(View.INVISIBLE);
                    btn3.setVisibility(View.INVISIBLE);
                    btn4.setVisibility(View.INVISIBLE);
                    btn5.setVisibility(View.INVISIBLE);
                    btn6.setVisibility(View.INVISIBLE);
                    btn7.setVisibility(View.INVISIBLE);
                    btn8.setVisibility(View.INVISIBLE);
                    btn9.setVisibility(View.INVISIBLE);
                    btn10.setVisibility(View.INVISIBLE);
                    Button1.setVisibility(View.VISIBLE);
                    Button2.setVisibility(View.VISIBLE);
                    Button3.setVisibility(View.VISIBLE);
                    Button4.setVisibility(View.VISIBLE);
                    Button5.setVisibility(View.VISIBLE);
                    Button6.setVisibility(View.VISIBLE);
                    Button7.setVisibility(View.VISIBLE);
                    Button8.setVisibility(View.VISIBLE);
                    Button9.setVisibility(View.VISIBLE);
                    Button10.setVisibility(View.VISIBLE);
                    Button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            button11.setValue("a");
                            isOn1();
                        }
                    });
                    Button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            button22.setValue("a");
                            isOn2();
                        }
                    });

                    Button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            button33.setValue("a");
                            isOn3();
                        }
                    });

                    Button4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            button44.setValue("a");
                            isOn4();
                        }
                    });

                    Button5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            button55.setValue("a");
                            isOn5();
                        }
                    });

                    Button6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            button66.setValue("a");
                            isOn6();
                        }
                    });

                    Button7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            button77.setValue("a");
                            isOn7();
                        }
                    });

                    Button8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            button88.setValue("a");
                            isOn8();
                        }
                    });

                    Button9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            button99.setValue("a");
                            isOn9();
                        }
                    });

                    Button10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            button1010.setValue("a");
                            isOn10();
                        }
                    });
                    timeSet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cal = Calendar.getInstance();
                            if (isChecked1) {
                                intent.putExtra("valia", "true");
                            } else {
                                intent.putExtra("valia", "false");
                            }
                            if (isChecked2) {
                                intent.putExtra("valib", "true");
                            } else {
                                intent.putExtra("valib", "false");
                            }
                            if (isChecked3) {
                                intent.putExtra("valic", "true");
                            } else {
                                intent.putExtra("valic", "false");
                            }
                            if (isChecked4) {
                                intent.putExtra("valid", "true");
                            } else {
                                intent.putExtra("valid", "false");
                            }
                            if (isChecked5) {
                                intent.putExtra("valie", "true");
                            } else {
                                intent.putExtra("valie", "false");
                            }
                            if (isChecked6) {
                                intent.putExtra("valif", "true");
                            } else {
                                intent.putExtra("valif", "false");
                            }
                            if (isChecked7) {
                                intent.putExtra("valig", "true");
                            } else {
                                intent.putExtra("valig", "false");
                            }
                            if (isChecked8) {
                                intent.putExtra("valih", "true");
                            } else {
                                intent.putExtra("valih", "false");
                            }
                            if (isChecked9) {
                                intent.putExtra("valii", "true");
                            } else {
                                intent.putExtra("valii", "false");
                            }
                            if (isChecked10) {
                                intent.putExtra("valij", "true");
                            } else {
                                intent.putExtra("valij", "false");
                            }
                            cal.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                            cal.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                            cal.set(Calendar.SECOND, 0);
                            pintent = PendingIntent.getBroadcast(Main_Estonian.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            am = (AlarmManager) getSystemService(ALARM_SERVICE);
                            am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pintent);
                            String hrh = Integer.toString(timePicker.getCurrentHour());
                            String minm = Integer.toString(timePicker.getCurrentMinute());
                            tund.setValue(hrh);
                            minut.setValue(minm);
                            time.setValue("a");
                            btn1.setVisibility(View.VISIBLE);
                            btn2.setVisibility(View.VISIBLE);
                            btn3.setVisibility(View.VISIBLE);
                            btn4.setVisibility(View.VISIBLE);
                            btn5.setVisibility(View.VISIBLE);
                            btn6.setVisibility(View.VISIBLE);
                            btn7.setVisibility(View.VISIBLE);
                            btn8.setVisibility(View.VISIBLE);
                            btn9.setVisibility(View.VISIBLE);
                            btn10.setVisibility(View.VISIBLE);
                            Button1.setVisibility(View.GONE);
                            Button2.setVisibility(View.GONE);
                            Button3.setVisibility(View.GONE);
                            Button4.setVisibility(View.GONE);
                            Button5.setVisibility(View.GONE);
                            Button6.setVisibility(View.GONE);
                            Button7.setVisibility(View.GONE);
                            Button8.setVisibility(View.GONE);
                            Button9.setVisibility(View.GONE);
                            Button10.setVisibility(View.GONE);
                            Intent start = new Intent(getApplicationContext(), Main_Estonian.class);
                            startActivity(start);
                        }
                    });
                }
                if (state.equals("started")) {
                    time.setValue("d");
                    button11.setValue("b");
                    button22.setValue("b");
                    button33.setValue("b");
                    button44.setValue("b");
                    button55.setValue("b");
                    button66.setValue("b");
                    button77.setValue("b");
                    button88.setValue("b");
                    button99.setValue("b");
                    button1010.setValue("b");
                    Toast.makeText(getApplicationContext(), "Katkestatud", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                String a = "a";
                if (value.equals(a)) {
                    off1.setVisibility(View.INVISIBLE);
                    on1.setVisibility(View.VISIBLE);
                    isChecked1 = true;
                    btn1.setChecked(true);
                } else {
                    on1.setVisibility(View.INVISIBLE);
                    off1.setVisibility(View.VISIBLE);
                    isChecked1 = false;
                    btn1.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        button2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                String a = "a";
                if (value.equals(a)) {
                    off2.setVisibility(View.INVISIBLE);
                    on2.setVisibility(View.VISIBLE);
                    isChecked2 = true;
                    btn2.setChecked(true);
                } else {
                    on2.setVisibility(View.INVISIBLE);
                    off2.setVisibility(View.VISIBLE);
                    isChecked2 = false;
                    btn2.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        button3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                String a = "a";
                if (value.equals(a)) {
                    off3.setVisibility(View.INVISIBLE);
                    on3.setVisibility(View.VISIBLE);
                    isChecked3 = true;
                    btn3.setChecked(true);
                } else {
                    on3.setVisibility(View.INVISIBLE);
                    off3.setVisibility(View.VISIBLE);
                    isChecked3 = false;
                    btn3.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        button4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                String a = "a";
                if (value.equals(a)) {
                    off4.setVisibility(View.INVISIBLE);
                    on4.setVisibility(View.VISIBLE);
                    isChecked4 = true;
                    btn4.setChecked(true);
                } else {
                    on4.setVisibility(View.INVISIBLE);
                    off4.setVisibility(View.VISIBLE);
                    isChecked4 = false;
                    btn4.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        button5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                String a = "a";
                if (value.equals(a)) {
                    off5.setVisibility(View.INVISIBLE);
                    on5.setVisibility(View.VISIBLE);
                    isChecked5 = true;
                    btn5.setChecked(true);
                } else {
                    on5.setVisibility(View.INVISIBLE);
                    off5.setVisibility(View.VISIBLE);
                    isChecked5 = false;
                    btn5.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        button6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                String a = "a";
                if (value.equals(a)) {
                    off6.setVisibility(View.INVISIBLE);
                    on6.setVisibility(View.VISIBLE);
                    isChecked6 = true;
                    btn6.setChecked(true);
                } else {
                    on6.setVisibility(View.INVISIBLE);
                    off6.setVisibility(View.VISIBLE);
                    isChecked6 = false;
                    btn6.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        button7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                String a = "a";
                if (value.equals(a)) {
                    off7.setVisibility(View.INVISIBLE);
                    on7.setVisibility(View.VISIBLE);
                    isChecked7 = true;
                    btn7.setChecked(true);
                } else {
                    on7.setVisibility(View.INVISIBLE);
                    off7.setVisibility(View.VISIBLE);
                    isChecked7 = false;
                    btn7.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        button8.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                String a = "a";
                if (value.equals(a)) {
                    off8.setVisibility(View.INVISIBLE);
                    on8.setVisibility(View.VISIBLE);
                    isChecked8 = true;
                    btn8.setChecked(true);
                } else {
                    on8.setVisibility(View.INVISIBLE);
                    off8.setVisibility(View.VISIBLE);
                    isChecked8 = false;
                    btn8.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        button9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                String a = "a";
                if (value.equals(a)) {
                    off9.setVisibility(View.INVISIBLE);
                    on9.setVisibility(View.VISIBLE);
                    isChecked9 = true;
                    btn9.setChecked(true);
                } else {
                    on9.setVisibility(View.INVISIBLE);
                    off9.setVisibility(View.VISIBLE);
                    isChecked9 = false;
                    btn9.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        button10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                String a = "a";
                if (value.equals(a)) {
                    off10.setVisibility(View.INVISIBLE);
                    on10.setVisibility(View.VISIBLE);
                    isChecked10 = true;
                    btn10.setChecked(true);
                } else {
                    on10.setVisibility(View.INVISIBLE);
                    off10.setVisibility(View.VISIBLE);
                    isChecked10 = false;
                    btn10.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        button11.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                String a = "a";
                if (value.equals(a)) {
                    img1.setVisibility(View.VISIBLE);
                } else {
                    img1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        button22.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                String a = "a";
                if (value.equals(a)) {
                    img2.setVisibility(View.VISIBLE);
                } else {
                    img2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        button33.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                String a = "a";
                if (value.equals(a)) {
                    img3.setVisibility(View.VISIBLE);
                } else {
                    img3.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        button44.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                String a = "a";
                if (value.equals(a)) {
                    img4.setVisibility(View.VISIBLE);
                } else {
                    img4.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        button55.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                String a = "a";
                if (value.equals(a)) {
                    img5.setVisibility(View.VISIBLE);
                } else {
                    img5.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        button66.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                String a = "a";
                if (value.equals(a)) {
                    img6.setVisibility(View.VISIBLE);
                } else {
                    img6.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        button77.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                String a = "a";
                if (value.equals(a)) {
                    img7.setVisibility(View.VISIBLE);
                } else {
                    img7.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        button88.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                String a = "a";
                if (value.equals(a)) {
                    img8.setVisibility(View.VISIBLE);
                } else {
                    img8.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        button99.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                String a = "a";
                if (value.equals(a)) {
                    img9.setVisibility(View.VISIBLE);
                } else {
                    img9.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        button1010.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                String a = "a";
                if (value.equals(a)) {
                    img10.setVisibility(View.VISIBLE);
                } else {
                    img10.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        tund.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                int hourtund = Integer.parseInt(value);
                timePicker.setCurrentHour(hourtund);
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        minut.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                int minutmin = Integer.parseInt(value);
                timePicker.setCurrentMinute(minutmin);
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        time.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                String a = "a";
                String b = "b";
                String d = "d";
                if (value.equals(a)) {
                    state = "started";
                    timeSet.setText("Katkesta");
                    timePicker.setEnabled(false);
                } else if (value.equals(b)) {
                    state = "ready";
                    timeSet.setText("Sea aeg");
                    timePicker.setEnabled(true);
                } else if (value.equals(d)) {
                    timeSet.setEnabled(true);
                    Intent base = new Intent(getBaseContext(), Receiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, base, 0);
                    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                    alarmManager.cancel(pendingIntent);
                    time.setValue("b");
                } else {
                    state = "done";
                    timePicker.setEnabled(true);
                    time.setValue("b");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    private void isOn1() {
        if (!isChecked1) {
            isChecked1 = true;
        } else {
            isChecked1 = false;
        }
    }

    private void isOn2() {
        if (!isChecked2) {
            isChecked2 = true;
        } else {
            isChecked2 = false;
        }
    }

    private void isOn3() {
        if (!isChecked3) {
            isChecked3 = true;
        } else {
            isChecked3 = false;
        }
    }

    private void isOn4() {
        if (!isChecked4) {
            isChecked4 = true;
        } else {
            isChecked4 = false;
        }
    }

    private void isOn5() {
        if (!isChecked5) {
            isChecked5 = true;
        } else {
            isChecked5 = false;
        }
    }

    private void isOn6() {
        if (!isChecked6) {
            isChecked6 = true;
        } else {
            isChecked6 = false;
        }
    }

    private void isOn7() {
        if (!isChecked7) {
            isChecked7 = true;
        } else {
            isChecked7 = false;
        }
    }

    private void isOn8() {
        if (!isChecked8) {
            isChecked8 = true;
        } else {
            isChecked8 = false;
        }
    }

    private void isOn9() {
        if (!isChecked9) {
            isChecked9 = true;
        } else {
            isChecked9 = false;
        }
    }

    private void isOn10() {
        if (!isChecked10) {
            isChecked10 = true;
        } else {
            isChecked10 = false;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        refreshTask.run();
        if (isChecked1 || isChecked2 || isChecked3 || isChecked4 || isChecked5 || isChecked6 || isChecked7 || isChecked8 || isChecked9 || isChecked10) {
            byeOff.setVisibility(View.INVISIBLE);
            byeOn.setVisibility(View.VISIBLE);
        } else {
            byeOn.setVisibility(View.INVISIBLE);
            byeOff.setVisibility(View.VISIBLE);
        }

        if (isChecked1 || isChecked6 || isChecked9) {
            helloOff.setVisibility(View.INVISIBLE);
            helloOn.setVisibility(View.VISIBLE);
        } else {
            helloOn.setVisibility(View.INVISIBLE);
            helloOff.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        refresh.removeCallbacks(refreshTask);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class Background_get extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                if (params[0].equals("Led1=1") && !isChecked1) {
                    button1.setValue("a");
                    new Background_get2().execute("Led1=1");
                }
                if (params[0].equals("Led1=0") && isChecked1) {
                    button1.setValue("b");
                    new Background_get2().execute("Led1=0");
                }
                if (params[0].equals("Led2=1") && !isChecked2) {
                    button2.setValue("a");
                    new Background_get2().execute("Led2=1");
                }
                if (params[0].equals("Led2=0") && isChecked2) {
                    button2.setValue("b");
                    new Background_get2().execute("Led2=0");
                }
                if (params[0].equals("Led3=1") && !isChecked3) {
                    button3.setValue("a");
                    new Background_get2().execute("Led3=1");
                }
                if (params[0].equals("Led3=0") && isChecked3) {
                    button3.setValue("b");
                    new Background_get2().execute("Led3=0");
                }
                if (params[0].equals("Led4=1") && !isChecked4) {
                    button4.setValue("a");
                    new Background_get2().execute("Led4=1");
                }
                if (params[0].equals("Led4=0") && isChecked4) {
                    button4.setValue("b");
                    new Background_get2().execute("Led4=0");
                }
                if (params[0].equals("Led5=1") && !isChecked5) {
                    button5.setValue("a");
                    new Background_get2().execute("Led5=1");
                }
                if (params[0].equals("Led5=0") && isChecked5) {
                    button5.setValue("b");
                    new Background_get2().execute("Led5=0");
                }
                if (params[0].equals("Led6=1") && !isChecked6) {
                    button6.setValue("a");
                    new Background_get2().execute("Led6=1");
                }
                if (params[0].equals("Led6=0") && isChecked6) {
                    button6.setValue("b");
                    new Background_get2().execute("Led6=0");
                }
                if (params[0].equals("Led7=1") && !isChecked7) {
                    button7.setValue("a");
                    new Background_get2().execute("Led7=1");
                }
                if (params[0].equals("Led7=0") && isChecked7) {
                    button7.setValue("b");
                    new Background_get2().execute("Led7=0");
                }
                if (params[0].equals("Led8=1") && !isChecked8) {
                    button8.setValue("a");
                    new Background_get2().execute("Led8=1");
                }
                if (params[0].equals("Led8=0") && isChecked8) {
                    button8.setValue("b");
                    new Background_get2().execute("Led8=0");
                }
                if (params[0].equals("Led9=1") && !isChecked9) {
                    button9.setValue("a");
                    new Background_get2().execute("Led9=1");
                }
                if (params[0].equals("Led9=0") && isChecked9) {
                    button9.setValue("b");
                    new Background_get2().execute("Led9=0");
                }
                if (params[0].equals("Led10=1") && !isChecked10) {
                    button10.setValue("a");
                    new Background_get2().execute("Led10=1");
                }
                if (params[0].equals("Led10=0") && isChecked10) {
                    button10.setValue("b");
                    new Background_get2().execute("Led10=0");
                }
                Thread.sleep(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    private class Background_get2 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            try {
                loginPreferencess = getSharedPreferences("loginPrefss", MODE_PRIVATE);
                Thread.sleep(10);
                URL url = new URL("http://" + loginPreferencess.getString("ip", "") + "/?" + params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return null;
        }
        protected void onPreExecute() {
            hello.setClickable(false);
            bye.setClickable(false);
            btn1.setClickable(false);
            btn2.setClickable(false);
            btn3.setClickable(false);
            btn4.setClickable(false);
            btn5.setClickable(false);
            btn6.setClickable(false);
            btn7.setClickable(false);
            btn8.setClickable(false);
            btn9.setClickable(false);
            btn10.setClickable(false);
        }
        protected void onPostExecute(String result) {
            try {
                hello.setClickable(true);
                bye.setClickable(true);
                btn1.setClickable(true);
                btn2.setClickable(true);
                btn3.setClickable(true);
                btn4.setClickable(true);
                btn5.setClickable(true);
                btn6.setClickable(true);
                btn7.setClickable(true);
                btn8.setClickable(true);
                btn9.setClickable(true);
                btn10.setClickable(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
