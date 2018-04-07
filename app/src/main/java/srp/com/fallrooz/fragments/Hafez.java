package srp.com.fallrooz.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import srp.com.fallrooz.Db.database;
import srp.com.fallrooz.R;

import static srp.com.fallrooz.util.Global.BackTo;
import static srp.com.fallrooz.util.Global.iranSans;

/**
 * Created by s.rahmanipour on 9/10/2016.
 */
public class Hafez extends Fragment{

    private ImageButton ibMenu;
    private CardView cvBefor;
    private TextView txtPishgoftar;
    private Button btnFall;
    private CardView cvAfter;
    private Button btnTafsir;
    private Button btnPoem;
    private Button btnTryagain;
    private TextView txtMatn;
    private String strpoem,strtafsid;
    private database db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hafez, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);

    }
    private void initUI(View view){
        db = new database(getContext());
        db.useable();
        db.open();


        ibMenu = view.findViewById(R.id.ib_menu);
        cvBefor = view.findViewById(R.id.cv_befor);
        txtPishgoftar = view.findViewById(R.id.txt_pishgoftar);
        btnFall = view.findViewById(R.id.btn_fall);
        cvAfter = view.findViewById(R.id.cv_after);
        btnTafsir = view.findViewById(R.id.btn_tafsir);
        btnPoem = view.findViewById(R.id.btn_poem);
        txtMatn = view.findViewById(R.id.txt_matn);
        btnTryagain = view.findViewById(R.id.btn_tryagain);

        btnTryagain.setTypeface(iranSans);
        btnTafsir.setTypeface(iranSans);
        btnPoem.setTypeface(iranSans);
        txtMatn.setTypeface(iranSans);
        btnFall.setTypeface(iranSans);
        txtPishgoftar.setTypeface(iranSans);

        btnPoem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPoem.setBackgroundResource(R.drawable.toprightprimary);
                btnTafsir.setBackgroundResource(R.drawable.topleftwhite);
                btnPoem.setTextColor(getResources().getColor(R.color.white));
                btnTafsir.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                txtMatn.setText(strpoem);
            }
        });
        btnTafsir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPoem.setBackgroundResource(R.drawable.toprightwhite);
                btnTafsir.setBackgroundResource(R.drawable.topleftprimary);
                btnPoem.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                btnTafsir.setTextColor(getResources().getColor(R.color.white));

                txtMatn.setText(strtafsid);
            }
        });
        btnFall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int min = 1;
                int max = 495;
                int random = new Random().nextInt((max - min) + 1) + min;

                byte[] blobpoem = db.getPoem(1,random);
                strpoem = new String(blobpoem);

                byte[] blobtafsir = db.getPoem(2,random);
                strtafsid = new String(blobtafsir);
                txtMatn.setText(strpoem);

                cvAfter.setVisibility(View.VISIBLE);
                cvBefor.setVisibility(View.GONE);
            }
        });
        btnTryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cvBefor.setVisibility(View.VISIBLE);
                cvAfter.setVisibility(View.GONE);
            }
        });
        initData();
    }

    private void initData(){
        BackTo = 1;
        cvBefor.setVisibility(View.VISIBLE);
        cvAfter.setVisibility(View.GONE);
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            initData();
        }
        super.onHiddenChanged(hidden);
    }
}
