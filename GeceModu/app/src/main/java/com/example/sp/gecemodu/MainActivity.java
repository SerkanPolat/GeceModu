package com.example.sp.gecemodu;
import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {
    Intent overlayIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d("IZIN","IZIN YERINE GIRDIM");
            requestPermissions(new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW},
                    1);
            requestPermissions(new String[]{Manifest.permission.WRITE_SETTINGS},
                    2);
        }
        Intent arttir = new Intent("ParlaklikArttir");
        Intent azalt = new Intent("ParlaklikAzalt");
        Intent Kapat  = new Intent("Kapat");
        PendingIntent pendingIntentArttir = PendingIntent.getBroadcast(this,0,arttir,PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentAzalt = PendingIntent.getBroadcast(this, 1, azalt, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentKapat = PendingIntent.getBroadcast(this,3,Kapat,PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews notificationView = new RemoteViews(getPackageName(), R.layout.notifi_layout);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.kapak).setTicker("SERKAN POLAT").setAutoCancel(false).setContent(notificationView);
        notificationView.setOnClickPendingIntent(R.id.buttonArttir, pendingIntentArttir);
        notificationView.setOnClickPendingIntent(R.id.buttonAzalt,pendingIntentAzalt);
        notificationView.setOnClickPendingIntent(R.id.buttonKapat,pendingIntentKapat);
        notificationManager.notify(1, builder.build());

        overlayIntent = new Intent();
        overlayIntent.setClass(this, Overlay.class);
        NotificationIntentSevice.OverlayIntentServis = overlayIntent;
        startService(overlayIntent);
        finish();
    }
}
