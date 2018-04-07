package srp.com.fallrooz.login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import srp.com.fallrooz.Db.AppDatabase;
import srp.com.fallrooz.Db.tables.UinfoDb;
import srp.com.fallrooz.MainActivity;
import srp.com.fallrooz.R;
import srp.com.fallrooz.adapter.IncomingSms;
import srp.com.fallrooz.adapter.Loading;
import srp.com.fallrooz.adapter.OnComplet;
import srp.com.fallrooz.util.Global;

import static srp.com.fallrooz.util.Global.iranSans;

/**
 * Created by s.rahmanipour on 9/10/2016.
 */
public class Login extends AppCompatActivity {

    private LinearLayout llMobile;
    private TextInputLayout tilMobile;
    private TextInputEditText tieMobile;
    private Button btnSave;
    private LinearLayout llVerify;
    private TextInputLayout tilCode;
    private TextInputEditText tieCode;
    private Button btnVerify;
    private String transcode,otpreference,mobile;
    private boolean ispause = false;
    private int sec = 120;
    private TextView txtTimer;
    private View mainView;
    private IncomingSms smsReciver;
    private LinearLayout llConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        mainView = getWindow().getDecorView().getRootView();
        initUI();

    }

    private void initUI(){

        txtTimer = findViewById(R.id.txt_timer);
        llMobile = findViewById(R.id.ll_mobile);
        tilMobile = findViewById(R.id.til_mobile);
        tieMobile = findViewById(R.id.tie_mobile);
        btnSave = findViewById(R.id.btn_save);
        llVerify = findViewById(R.id.ll_verify);
        tilCode = findViewById(R.id.til_code);
        tieCode = findViewById(R.id.tie_code);
        btnVerify = findViewById(R.id.btn_verify);
        TextView txtConfirmation = findViewById(R.id.txt_confirmation);
        Button btnConfirmation = findViewById(R.id.btn_confirmation);
        llConfirmation = findViewById(R.id.ll_confirmation);

        tilMobile.setTypeface(iranSans);
        tieMobile.setTypeface(iranSans);
        btnSave.setTypeface(iranSans);
        tilCode.setTypeface(iranSans);
        tieCode.setTypeface(iranSans);
        btnVerify.setTypeface(iranSans);
        txtTimer.setTypeface(iranSans);
        btnConfirmation.setTypeface(iranSans);
        txtConfirmation.setTypeface(iranSans);

        btnConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llMobile.setVisibility(View.VISIBLE);
                llConfirmation.setVisibility(View.GONE);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = tieMobile.getText().toString();
                if (mobile!=null && !mobile.isEmpty() && !mobile.equals("") && mobile.length()==11){
                    getVerification(mobile);
                }else {
                    Toast.makeText(Login.this,"لطفا موبایل را وارد نمایید.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = tieCode.getText().toString();
                if (code!=null && !code.isEmpty() && !code.equals("") && code.length()==4){
                    sendCode(code);
                }else {
                    Toast.makeText(Login.this,"لطفا کد فعالسازی را وارد نمایید.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tieMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String sss = String.valueOf(editable);
                if (sss.length()==11){
                    View view = getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
            }
        });
        tieCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String sss = String.valueOf(s);
                if (sss.length()==4){
                    View view = getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
            }
        });





        if (ContextCompat.checkSelfPermission(Login.this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.RECEIVE_SMS)) {

                String [] perms = {Manifest.permission.RECEIVE_SMS};
//                dialogPermission = new DialogPermission(getActivity(), "- دسترسی به پیام های ورودی",perms , new OnPermisionTry() {
//                    @Override
//                    public void OnTryAgain() {
//                        dialogPermission.dismiss();
//
//                        final IntentFilter filter = new IntentFilter();
//                        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
//
//                        smsReciver = new IncomingSms( new OnComplet() {
//                            @Override
//                            public void complet(Boolean result, String message) {
//                                Log.e("veriiif","verifff :"+message);
//                                try {
//                                    if (message != null && !message.isEmpty() && !message.equals("null") && !message.equals("")) {
//                                        verifyCode = message;
//                                        edtVerifycode.setText(message);
//                                        login();
//                                    }
//                                }
//                                catch (Exception e1) {}
//                                try {
//                                    getActivity().unregisterReceiver(smsReciver);
//                                }
//                                catch (Exception e) {}
//                            }
//                        });
//                        getActivity().registerReceiver(smsReciver, filter);
//                    }
//                });
//                dialogPermission.showdialog();
            } else {
                requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},1);
            }
        } else {
            final IntentFilter filter = new IntentFilter();
            filter.addAction("android.provider.Telephony.SMS_RECEIVED");

            smsReciver = new IncomingSms(new OnComplet() {
                @Override
                public void complet(Boolean result, String message) {
                    Log.e("veriiif","verifff :"+message);
                    try {
                        if (message != null && !message.isEmpty() && !message.equals("null") && !message.equals("")) {
                            tieCode.setText(message);
                            sendCode(message);
                        }
                    }
                    catch (Exception e1) {}
                    try {
                        unregisterReceiver(smsReciver);
                    }
                    catch (Exception e) {}
                }
            });
            try {
                registerReceiver(smsReciver, filter);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }

    private void timer(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!ispause){

                    txtTimer.setVisibility(View.VISIBLE);
                    if (sec < 10){
                        txtTimer.setText("00:0"+sec);
                    }else if (sec ==10){
                        txtTimer.setText("00:10");
                    }else if (sec>10 && sec <60){
                        txtTimer.setText("00:"+sec);
                    }else if (sec == 60){
                        txtTimer.setText("01:00");
                    }else if (sec > 60 && sec <70){
                        txtTimer.setText("01:0"+(sec-60));
                    }else if (sec ==70){
                        txtTimer.setText("01:"+(sec-60));
                    }else if (sec >70 && sec <120){
                        txtTimer.setText("01:"+(sec-60));
                    }else if (sec==120){
                        txtTimer.setText("02:00");
                    }

                    if (sec==0){
                        sec  = 120;
                        Toast.makeText(Login.this,"فرصت وارد کردن شماره تمام شد",Toast.LENGTH_SHORT).show();
                        txtTimer.setVisibility(View.GONE);
                        llMobile.setVisibility(View.VISIBLE);
                        llVerify.setVisibility(View.GONE);
                    }else {
                        sec  = sec -1;
                        timer();
                    }
                }
            }
        },1000);
    }
    public void sendCode(String code) {

        final Loading loading = new Loading(Login.this);
        loading.showdialog();

        Ion.with(Login.this).
                load(Global.SendCode)
                .setMultipartParameter("transcode",transcode)
                .setMultipartParameter("otpreference",otpreference)
                .setMultipartParameter("code",code)
                .setMultipartParameter("mobile",mobile)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        if (loading !=null){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    loading.dismiss();
                                }
                            });
                        }
                        if (e != null) {
                            return;
                        }
                        int status = result.get("status").getAsInt();
                        if (status==1){
                            ispause = true;
                            UinfoDb uinfoDb = new UinfoDb();
                            uinfoDb.setMobile(mobile);
                            uinfoDb.setIsValid(true);
                            AppDatabase.getAppDatabase(Login.this).type1Dao().update(uinfoDb);
                            startActivity(new Intent(Login.this, MainActivity.class));
                            finish();
                        }else if (status==0){
                            //az ghabl faale
                            ispause = true;
                            UinfoDb uinfoDb = new UinfoDb();
                            uinfoDb.setMobile(mobile);
                            uinfoDb.setIsValid(true);
                            AppDatabase.getAppDatabase(Login.this).type1Dao().update(uinfoDb);
                            startActivity(new Intent(Login.this, MainActivity.class));
                            finish();
                        }else {
                            Snackbar snackbar = Snackbar
                                    .make(mainView, "کد وارد شده نامعتبر است.", Snackbar.LENGTH_LONG)
                                    .setAction("", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    });
                            snackbar.setActionTextColor(getResources().getColor(R.color.white));

                            View sbView = snackbar.getView();
                            sbView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
                            textView.setTextColor(getResources().getColor(R.color.white));
                            textView.setTypeface(iranSans);
                            snackbar.show();

                        }
                    }
                });

    }
    public void getVerification(final String mobile1) {
        final Loading loading = new Loading(Login.this);
        loading.showdialog();
        Ion.with(Login.this).
                load(Global.Verification)
                .setMultipartParameter("mobile",mobile1)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {

                if (loading !=null){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loading.dismiss();
                        }
                    });
                }

                if (e != null) {
                    return;
                }
                int status = result.get("status").getAsInt();
                if (status==1){
                    JsonObject jo = result.get("data").getAsJsonObject();
                    transcode = jo.get("transcode").getAsString();
                    otpreference = jo.get("otpreference").getAsString();
                    mobile = mobile1;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            llVerify.setVisibility(View.VISIBLE);
                            llMobile.setVisibility(View.GONE);
                            timer();
                        }
                    });

                }else {
                    Snackbar snackbar = Snackbar
                            .make(mainView, "متاسفانه خطایی رخ داد.", Snackbar.LENGTH_LONG)
                            .setAction("", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });
                    snackbar.setActionTextColor(getResources().getColor(R.color.white));

                    View sbView = snackbar.getView();
                    sbView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(getResources().getColor(R.color.white));
                    textView.setTypeface(iranSans);
                    snackbar.show();

                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //
            final IntentFilter filter = new IntentFilter();
            filter.addAction("android.provider.Telephony.SMS_RECEIVED");

            smsReciver = new IncomingSms(new OnComplet() {
                @Override
                public void complet(Boolean result, String message) {
                    Log.e("veriiif","verifff :"+message);
                    try {
                        if (message != null && !message.isEmpty() && !message.equals("null") && !message.equals("")) {
                            tieCode.setText(message);
                            sendCode(message);
                        }
                    }
                    catch (Exception e1) {}
                    try {
                        unregisterReceiver(smsReciver);
                    }
                    catch (Exception e) {}
                }
            });
            registerReceiver(smsReciver, filter);
        } else {
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String [] perms = {Manifest.permission.RECEIVE_SMS,Manifest.permission.READ_SMS};
//                        dialogPermission = new DialogPermission(getActivity(), "- دسترسی به پیام ها"+"\n"+"- دسترسی به پیام های ورودی",perms , new OnPermisionTry() {
//                            @Override
//                            public void OnTryAgain() {
//                                dialogPermission.dismiss();
//
//                                final IntentFilter filter = new IntentFilter();
//                                filter.addAction("android.provider.Telephony.SMS_RECEIVED");
//
//                                smsReciver = new IncomingSms(new OnComplet() {
//                                    @Override
//                                    public void complet(Boolean result, String message) {
//                                        Log.e("veriiif","verifff :"+message);
//                                        try {
//                                            if (message != null && !message.isEmpty() && !message.equals("null") && !message.equals("")) {
//                                                verifyCode = message;
//                                                edtVerifycode.setText(message);
//                                                login();
//                                            }
//                                        }
//                                        catch (Exception e1) {}
//                                        try {
//                                            getActivity().unregisterReceiver(smsReciver);
//                                        }
//                                        catch (Exception e) {}
//                                    }
//                                });
//                                getActivity().registerReceiver(smsReciver, filter);
//                            }
//                        });
//                        dialogPermission.showdialog();
                    }
                });
            }catch (NullPointerException e1){
                e1.printStackTrace();
            }
        }
    }
}
