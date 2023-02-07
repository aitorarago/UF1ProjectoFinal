package com.example.uf1projectofinal;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;


public class MainActivity extends AppCompatActivity {
    private Button buttontots;
    private Button buttonmati;
    private Button buttontarda;
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        buttontots=findViewById(R.id.ciclesformtiustots);
        AlarmReceiver alarmReceiver = new AlarmReceiver();
        buttontots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmReceiver.start(0);
            }
        });
        buttonmati=findViewById(R.id.ciclesformtiusmati);
        buttonmati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmReceiver.start(1);
            }
        });
        buttontarda=findViewById(R.id.ciclesformtiustarda);
        buttontarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmReceiver.start(2);
            }
        });
    }


    /**
     * Creates a Notification channel, for OREO and higher.
     */
    public void createNotificationChannel() {

        // Create a notification manager object.
        mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "CAMBIO DE CLASSE",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notifica cada cambio de clase del INS PUIG CASTELLAR");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }
    public void sendError(){
        Snackbar.make(findViewById(R.id.mainview),"ERROR AL INTENTAR DESCARGAR LAS ALARMAS EN EL DISPOSITIVO", Snackbar.LENGTH_SHORT);
    }

}