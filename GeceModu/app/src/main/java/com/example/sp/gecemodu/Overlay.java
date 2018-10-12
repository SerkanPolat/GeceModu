package com.example.sp.gecemodu;

import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

public class Overlay extends Service{
    private LayoutInflater inf;
    private Display mDisplay;
    private View layoutView;
    private WindowManager windowManager;
    private WindowManager.LayoutParams params;
    private Resources res;
    private float Saydamlik;
    @Nullable
    @Override
    public IBinder onBind(Intent ıntent) {
            return null;
    }

    public Overlay() {
        super();
        NotificationIntentSevice.OverlayService = this;
        Saydamlik = 0f;
    }

    @Override
    public void onCreate(){

        super.onCreate();
        res = getResources();
        windowManager = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        params = new WindowManager.LayoutParams(
                -1, yKonumBelirle(), 0, 0, 2006, 17368856, -3);
        params.gravity = Gravity.TOP | Gravity.LEFT;

        inf = LayoutInflater.from(getApplicationContext());
        layoutView = inf.inflate(R.layout.activity_main, null);
        layoutView.findViewById(R.id.overlayPanel).setAlpha(0f);
        windowManager.addView(layoutView, params);

    }

    public void Guncelle(float Gelen){

        ConstraintLayout cons = layoutView.findViewById(R.id.overlayPanel);
            if(Saydamlik+Gelen<=0){
                cons.setAlpha(0f);
            }else{
                if(Saydamlik+Gelen>=0.7) {
                    cons.setAlpha(0.7f);
                }else{
                    cons.setAlpha(Saydamlik+Gelen);
                }
            }
            Saydamlik = cons.getAlpha();
            windowManager.updateViewLayout(layoutView,params);

    }
    private int yKonumBelirle() {

        DisplayMetrics dispMet =  new DisplayMetrics();
        mDisplay.getMetrics(dispMet);
        int c = dispMet.heightPixels+BarDurumlari();
        int nav = navDurum();
        return res.getConfiguration().orientation==1 ? c + nav : c;
    }

    private int navDurum() {
        int identifier = res.getIdentifier("navigation_bar_height","dimen","android");
        if(identifier>0){
            return res.getDimensionPixelSize(identifier);
        }else{
            return (int)typed(48.0f);
        }
    }

    private int BarDurumlari() {
        int identifier = res.getIdentifier("status_bar_height","dimen","android");
        if(identifier>0){
            return res.getDimensionPixelSize(identifier);
        }else{
            return (int)typed(25.0f);
        }
    }

    private float typed(float v) {
        return TypedValue.applyDimension(1,v,res.getDisplayMetrics());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        windowManager.removeView(layoutView);
    }
}
