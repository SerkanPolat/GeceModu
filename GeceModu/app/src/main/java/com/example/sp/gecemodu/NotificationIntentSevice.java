package com.example.sp.gecemodu;

import android.app.Application;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;

public class NotificationIntentSevice extends BroadcastReceiver {
    static Overlay OverlayService;
    static Intent OverlayIntentServis;
    View LayoutView;

    @Override
    public void onReceive(Context context, Intent intent) {

        switch (intent.getAction()){
            case "ParlaklikArttir":
                OverlayService.Guncelle(-0.1f);
                break;
            case "ParlaklikAzalt":
                OverlayService.Guncelle(+0.1f);
                break;

            case "Kapat":
                NotificationManager NotifiManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                NotifiManager.cancel(1);
                context.stopService(OverlayIntentServis);

                break;
                default:
                    break;


        }

    }
}
