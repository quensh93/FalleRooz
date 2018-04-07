package srp.com.fallrooz;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import co.ronash.pushe.Pushe;
import srp.com.fallrooz.Db.AppDatabase;
import srp.com.fallrooz.Db.tables.UinfoDb;
import srp.com.fallrooz.login.Login;
import srp.com.fallrooz.util.Global;

import static srp.com.fallrooz.util.Global.CatId;

public class Splash extends AppCompatActivity {

    private UinfoDb uinfoDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Pushe.initialize(this,true);
        TextView textView = findViewById(R.id.textView);
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/NaughtyNights.ttf"));
        uinfoDb = AppDatabase.getAppDatabase(this).type1Dao().getUinfo();
        startActivity(new Intent(Splash.this, MainActivity.class));
        finish();
        //checkUpdate();
    }
    private void changeActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AppDatabase.getAppDatabase(Splash.this).type1Dao().countUinfo()>0) {
                    if (uinfoDb.getIsValid()){
                        startActivity(new Intent(Splash.this, MainActivity.class));
                        finish();
                    }else {
                        startActivity(new Intent(Splash.this, Login.class));
                        finish();
                    }
                }else {
                    startActivity(new Intent(Splash.this, Login.class));
                    finish();
                }
            }
        }, 3000);
    }


    private void checkUpdate(){
        Ion.with(Splash.this)
                .load(Global.CheckVersionUrl)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject jsonObject) {
                        if (e != null) {
                            return;
                        }
                        if (jsonObject.has("success") && jsonObject.get("success").getAsInt() == 1) {
                            int vcode = jsonObject.get("vcode").getAsInt();
                            int isforce = jsonObject.get("isforce").getAsInt();
                            String dlurl = jsonObject.get("dlurl").getAsString();

                            PackageManager manager = getPackageManager();
                            PackageInfo info = null;
                            int versionCode=0;
                            try {
                                info = manager.getPackageInfo(getPackageName(), 0);
                                versionCode = info.versionCode;
                            } catch (PackageManager.NameNotFoundException e1) {
                                e1.printStackTrace();
                            }
                            if (versionCode < vcode){
                                Intent intent = new Intent(Splash.this,Update.class);
                                intent.putExtra("dlurl",dlurl);
                                intent.putExtra("isforce",isforce);
                                startActivity(intent);
                                finish();
                            }else {
                                changeActivity();
                            }
                        }else {
                            changeActivity();
                        }

                    }
                })
        ;
    }


}
