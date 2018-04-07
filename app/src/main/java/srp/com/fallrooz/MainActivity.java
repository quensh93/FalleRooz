package srp.com.fallrooz;

import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import srp.com.fallrooz.Db.AppDatabase;
import srp.com.fallrooz.Db.tables.UinfoDb;
import srp.com.fallrooz.adapter.residemenu.ResideMenu;
import srp.com.fallrooz.fragments.AboutUs;
import srp.com.fallrooz.fragments.Home;
import srp.com.fallrooz.login.Login;
import srp.com.fallrooz.util.Global;

import static srp.com.fallrooz.adapter.residemenu.ResideMenu.DIRECTION_LEFT;
import static srp.com.fallrooz.util.FragmentHelper.changeFragment;
import static srp.com.fallrooz.util.FragmentHelper.fragmentManager;
import static srp.com.fallrooz.util.Global.BackTo;
import static srp.com.fallrooz.util.Global.iranSans;

public class MainActivity extends AppCompatActivity {

    private UinfoDb uinfoDb;
    public static ResideMenu resideMenu;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpMenu();
        fragmentManager = getSupportFragmentManager();
        changeFragment(new Home(),"Home");
        //sendCode();
    }
    private void setUpMenu() {

        resideMenu = new ResideMenu(this);
        resideMenu.setUse3D(true);
        //resideMenu.setBackground(R.drawable.bg);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.8f);
        resideMenu.setSwipeDirectionDisable(DIRECTION_LEFT);
        View menu = resideMenu.getMenuView();


        TextView txtAppname = menu.findViewById(R.id.txt_appname);
        TextView txtHome = menu.findViewById(R.id.txt_home);
        TextView txtAboutus = menu.findViewById(R.id.txt_aboutus);
        TextView txtExit = menu.findViewById(R.id.txt_exit);
        TextView txtVersion = menu.findViewById(R.id.txt_version);
        txtAppname.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/NaughtyNights.ttf"));
        txtHome.setTypeface(iranSans);
        txtAboutus.setTypeface(iranSans);
        txtExit.setTypeface(iranSans);
        txtVersion.setTypeface(iranSans);

        txtExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resideMenu.isOpened())
                    resideMenu.closeMenu();
                finish();
                System.exit(0);
            }
        });
        txtHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resideMenu.isOpened())
                    resideMenu.closeMenu();
                fragmentManager = getSupportFragmentManager();
                changeFragment(new Home(),"Home");
            }
        });
        txtAboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resideMenu.isOpened())
                    resideMenu.closeMenu();
                fragmentManager = getSupportFragmentManager();
                changeFragment(new AboutUs(),"AboutUs");
            }
        });

    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }
    public static void addIgnored(View v){
        resideMenu.addIgnoredView(v);
    }
    public static void clearIgnored(){
        resideMenu.clearIgnoredViewList();
    }

    public static void MenuControl(){

        if (resideMenu.isOpened())
            resideMenu.closeMenu();
        else
            resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
    }
    public void sendCode() {
        uinfoDb = AppDatabase.getAppDatabase(MainActivity.this).type1Dao().getUinfo();
        Ion.with(MainActivity.this).
                load(Global.Checker)
                .setMultipartParameter("token","93175d7ba77b58fb43990cbdddcc56d8")
                .setMultipartParameter("sId","6737")
                .setMultipartParameter("mobile",uinfoDb.getMobile())
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e != null) {
                            return;
                        }
                        int status = result.get("status").getAsInt();
                        if (status==1){
                            uinfoDb.setIsValid(true);
                        }else {
                            uinfoDb.setIsValid(false);
                        }
                        AppDatabase.getAppDatabase(MainActivity.this).type1Dao().update(uinfoDb);
                    }
                });
    }

    @Override
    public void onBackPressed() {

        switch (BackTo){
            case 0:
                if (doubleBackToExitPressedOnce) {

                    finish();
                    System.exit(0);

                    return;
                }
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this,"لطفا برای خروج یکبار دیگر کلیک کنید",Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
                break;
            case 1:
                fragmentManager = getSupportFragmentManager();
                changeFragment(new Home(),"Home");
                break;
        }

    }
}
