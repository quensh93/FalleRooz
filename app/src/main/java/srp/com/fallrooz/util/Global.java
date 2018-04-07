package srp.com.fallrooz.util;

import android.graphics.Typeface;

import com.crashlytics.android.Crashlytics;
import com.orm.SugarApp;

import io.fabric.sdk.android.Fabric;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import co.ronash.pushe.Pushe;
import srp.com.fallrooz.Db.AppDatabase;
import srp.com.fallrooz.Db.tables.Type1Db;
import srp.com.fallrooz.Db.tables.Type2Db;
import srp.com.fallrooz.Db.tables.Type3Db;

/**
 * Created by s.rahmanipour on 1/8/2018.
 */

public class Global extends SugarApp {
    public static Typeface iranSans,iranSansBold;
    public static int CatId = 0;
    public static int BackTo = 0;
    public static String DailyOmen = "http://fittapp.ir/fall/dailyomen.php";
    public static String Verification = "http://fittapp.ir/fall/otp_request.php";
    public static String SendCode = "http://fittapp.ir/fall/otp_response.php";
    public static String Checker = "http://79.175.138.237/otp/otp/otp_userSubscribe.php";
    public static String CheckVersionUrl = "http://fittapp.ir/fall/checkupdate.php";

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Pushe.initialize(this,true);

        String id = Pushe.getPusheId(this);
        iranSansBold = Typeface.createFromAsset(getAssets(), "fonts/IRANSansBold.ttf");
        iranSans = Typeface.createFromAsset(getAssets(), "fonts/IRANSans.ttf");

        long size1 = AppDatabase.getAppDatabase(this).type1Dao().countType1();
        if (size1==0){
        String json = null;
        try {
            InputStream is = getAssets().open("fall.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            try {
                JSONObject jo = new JSONObject(json);
                JSONArray type1 = jo.getJSONArray("type1");
                JSONArray type2 = jo.getJSONArray("type2");
                JSONArray type3 = jo.getJSONArray("type3");
                for (int i = 0; i < type1.length(); i++) {
                    JSONObject jotype1 = type1.getJSONObject(i);
                    Type1Db type1Db = new Type1Db();
                    type1Db.setCatId(jotype1.getInt("catid"));
                    type1Db.setModel(jotype1.getInt("model"));
                    type1Db.setGender(jotype1.getInt("gender"));
                    type1Db.setDescription(jotype1.getString("matn"));
                    AppDatabase.getAppDatabase(this).type1Dao().insertAllType1(type1Db);
                }
                for (int i = 0; i < type2.length(); i++) {
                    JSONObject jotype2 = type2.getJSONObject(i);
                    Type2Db type2Db = new Type2Db();
                    type2Db.setCatId(jotype2.getInt("catid"));
                    type2Db.setDescription(jotype2.getString("matn"));
                    AppDatabase.getAppDatabase(this).type1Dao().insertAllType2(type2Db);
                }
                for (int i = 0; i < type3.length(); i++) {
                    JSONObject jotype3 = type3.getJSONObject(i);
                    Type3Db type3Db = new Type3Db();
                    type3Db.setCatId(jotype3.getInt("catid"));
                    type3Db.setMin(jotype3.getInt("min"));
                    type3Db.setMax(jotype3.getInt("max"));
                    type3Db.setGender(jotype3.getString("gender"));
                    type3Db.setDescription(jotype3.getString("matn"));
                    AppDatabase.getAppDatabase(this).type1Dao().insertAllType3(type3Db);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        }
    }
}
