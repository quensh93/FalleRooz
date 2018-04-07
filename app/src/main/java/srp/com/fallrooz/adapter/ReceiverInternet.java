  // Copyright (c) 2016, iGap - www.iGap.im
// iGap is a Hybrid instant messaging service .
// RooyeKhat Media Co . - www.RooyeKhat.co
// All rights reserved.

package srp.com.fallrooz.adapter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import srp.com.fallrooz.Db.AppDatabase;
import srp.com.fallrooz.Db.tables.UinfoDb;
import srp.com.fallrooz.util.Global;

  public class ReceiverInternet extends BroadcastReceiver {

    private Context mContext;
    private NetworkInfo networkInfo;
    private UinfoDb uinfoDb;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                ConnectivityManager cm = ((ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE));
                networkInfo = cm.getActiveNetworkInfo(); 
                if (cm == null) {
                    return;
                }
                if (networkInfo != null) {
                    long size = AppDatabase.getAppDatabase(mContext).type1Dao().countUinfo();
                    if (size > 0) {
                        if (networkInfo.isConnectedOrConnecting()) {
                            sendCode();
                        }
                        if (hasActiveInternetConnection()) {
                            sendCode();
                        }
                    }
                }
            }
        });
        thread.start();
    }

      public void sendCode() {
          Log.e("sajad","estelam");
          uinfoDb = AppDatabase.getAppDatabase(mContext).type1Dao().getUinfo();
          Ion.with(mContext).
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
                          Log.e("sajad","result : "+result.toString());
                          int status = result.get("status").getAsInt();
                          if (status==1){
                              uinfoDb.setIsValid(true);
                              Log.e("sajad","true");
                          }else {
                              uinfoDb.setIsValid(false);
                              Log.e("sajad","false");
                          }
                          AppDatabase.getAppDatabase(mContext).type1Dao().update(uinfoDb);
                      }
                  });

      }


    public boolean hasActiveInternetConnection() {
        if (networkInfo != null) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(3000);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            }
            catch (IOException e) {

            }
        } else {

        }
        return false;
    }
}
