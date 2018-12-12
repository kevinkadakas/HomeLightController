package box.keri.homelightcontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

/**
 * Created by kadak on 29.04.2018.
 */

public class Receiver extends BroadcastReceiver {
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
    DatabaseReference time = users.child("time");
    String isChecked1, isChecked2, isChecked3, isChecked4, isChecked5, isChecked6, isChecked7, isChecked8, isChecked9, isChecked10;
    @Override
    public void onReceive(Context context, Intent intent) {
        isChecked1 = intent.getExtras().getString("valia");
        isChecked2 = intent.getExtras().getString("valib");
        isChecked3 = intent.getExtras().getString("valic");
        isChecked4 = intent.getExtras().getString("valid");
        isChecked5 = intent.getExtras().getString("valie");
        isChecked6 = intent.getExtras().getString("valif");
        isChecked7 = intent.getExtras().getString("valig");
        isChecked8 = intent.getExtras().getString("valih");
        isChecked9 = intent.getExtras().getString("valii");
        isChecked10 = intent.getExtras().getString("valij");
        try {
            new update().execute().get();
            time.setValue("c");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    private class update extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
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
            if (isChecked1.equals("false")) {
                button1.setValue("b");
            } else {
                button1.setValue("a");
            }
            if (isChecked2.equals("false")) {
                button2.setValue("b");
            } else {
                button2.setValue("a");
            }
            if (isChecked3.equals("false")) {
                button3.setValue("b");
            } else {
                button3.setValue("a");
            }
            if (isChecked4.equals("false")) {
                button4.setValue("b");
            } else {
                button4.setValue("a");
            }
            if (isChecked5.equals("false")) {
                button5.setValue("b");
            } else {
                button5.setValue("a");
            }
            if (isChecked6.equals("false")) {
                button6.setValue("b");
            } else {
                button6.setValue("a");
            }
            if (isChecked7.equals("false")) {
                button7.setValue("b");
            } else {
                button7.setValue("a");
            }
            if (isChecked8.equals("false")) {
                button8.setValue("b");
            } else {
                button8.setValue("a");
            }
            if (isChecked9.equals("false")) {
                button9.setValue("b");
            } else {
                button9.setValue("a");
            }
            if (isChecked10.equals("false")) {
                button10.setValue("b");
            } else {
                button10.setValue("a");
            }
            return null;
        }
    }
}
