package srp.com.fallrooz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import srp.com.fallrooz.Db.AppDatabase;
import srp.com.fallrooz.Db.tables.Type1Db;
import srp.com.fallrooz.MainActivity;
import srp.com.fallrooz.R;
import srp.com.fallrooz.adapter.BloodTypeAdapter;
import srp.com.fallrooz.adapter.CheeseAdapter;
import srp.com.fallrooz.adapter.DailyAdapter;
import srp.com.fallrooz.adapter.EyesAdapter;
import srp.com.fallrooz.adapter.FroutsAdapter;
import srp.com.fallrooz.adapter.Loading;
import srp.com.fallrooz.adapter.MonthAdapter;
import srp.com.fallrooz.adapter.ShapeAdapter;
import srp.com.fallrooz.adapter.discretescrollview.DiscreteScrollView;
import srp.com.fallrooz.adapter.SeseansAdapter;
import srp.com.fallrooz.adapter.discretescrollview.transform.ScaleTransformer;
import srp.com.fallrooz.util.Global;

import static srp.com.fallrooz.util.Global.BackTo;
import static srp.com.fallrooz.util.Global.CatId;
import static srp.com.fallrooz.util.Global.iranSans;

/**
 * Created by s.rahmanipour on 9/10/2016.
 */
public class Type1Detail extends Fragment{


    private ImageButton ibMenu;
    private DiscreteScrollView dsv;
    private TextView txtMatn;
    private Button btnMen;
    private Button btnWomen;
    private Button btnKoli;
    private LinearLayout llGender;
    private int model = 1;
    private int gender = 0;
    private ArrayList<String> data;
    private TextView txtDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_type1detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);

    }

    private void initUI(View view){

        ibMenu = view.findViewById(R.id.ib_menu);
        dsv = view.findViewById(R.id.dsv);
        btnMen = view.findViewById(R.id.btn_men);
        btnWomen = view.findViewById(R.id.btn_women);
        txtMatn = view.findViewById(R.id.txt_matn);
        llGender = view.findViewById(R.id.ll_gender);
        btnKoli = view.findViewById(R.id.btn_koli);
        txtDate = view.findViewById(R.id.txt_date);

        btnKoli.setTypeface(iranSans);
        txtMatn.setTypeface(iranSans);
        btnMen.setTypeface(iranSans);
        btnWomen.setTypeface(iranSans);
        txtDate.setTypeface(iranSans);

        dsv.scrollToPosition(0);
        dsv.setItemTransitionTimeMillis(100);
        dsv.setHasFixedSize(true);
        dsv.setOffscreenItems(12);

        dsv.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.7f)
                .build());
        dsv.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {

                model = adapterPosition+1;
                setNewData();
            }
        });

        btnWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = 2;

                if (CatId==13){
                    btnMen.setBackgroundResource(R.drawable.topleftwhite);
                    btnMen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                    btnWomen.setTextColor(getResources().getColor(R.color.white));
                    btnWomen.setBackgroundResource(R.color.colorAccent1);

                    btnKoli.setBackgroundResource(R.drawable.toprightwhite);
                    btnKoli.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }else {
                    btnWomen.setBackgroundResource(R.drawable.toprightprimary);
                    btnMen.setBackgroundResource(R.drawable.topleftwhite);
                    btnWomen.setTextColor(getResources().getColor(R.color.white));
                    btnMen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }


                setNewData();
            }
        });
        btnMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = 1;
                if (CatId==13){
                    btnWomen.setBackgroundResource(R.color.white);
                    btnWomen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                    btnMen.setTextColor(getResources().getColor(R.color.white));
                    btnMen.setBackgroundResource(R.drawable.topleftprimary);

                    btnKoli.setBackgroundResource(R.drawable.toprightwhite);
                    btnKoli.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                }else {
                    btnWomen.setBackgroundResource(R.drawable.toprightwhite);
                    btnMen.setBackgroundResource(R.drawable.topleftprimary);
                    btnWomen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    btnMen.setTextColor(getResources().getColor(R.color.white));
                }


                setNewData();
            }
        });
        btnKoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = 0;
                btnWomen.setBackgroundResource(R.color.white);
                btnMen.setBackgroundResource(R.drawable.topleftwhite);

                btnWomen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                btnMen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                btnKoli.setBackgroundResource(R.drawable.toprightprimary);
                btnKoli.setTextColor(getResources().getColor(R.color.white));

                setNewData();
            }
        });
        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.MenuControl();
            }
        });
        initData();
    }

    private void initData(){
        BackTo = 1;
        MainActivity.addIgnored(dsv);

        txtDate.setText("");
        btnKoli.setVisibility(View.GONE);
        btnWomen.setBackgroundResource(R.drawable.toprightprimary);
        btnMen.setBackgroundResource(R.drawable.topleftwhite);
        btnWomen.setTextColor(getResources().getColor(R.color.white));
        btnMen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        switch (CatId){
            case 0:
                data = new ArrayList<String>();
                data.clear();
                gender = 0;
                llGender.setVisibility(View.GONE);
                getDailyOmen();
                dsv.setAdapter(new DailyAdapter(getActivity()));
                break;
            case 1:
                Log.e("initData","A");
                gender = 0;
                llGender.setVisibility(View.GONE);
                dsv.setAdapter(new MonthAdapter(getActivity()));
                break;
            case 2:
                gender = 0;
                llGender.setVisibility(View.GONE);
                dsv.setAdapter(new MonthAdapter(getActivity()));
                break;
            case 3:
                gender = 0;
                llGender.setVisibility(View.GONE);
                dsv.setAdapter(new MonthAdapter(getActivity()));
                break;
            case 4:
                gender = 0;
                llGender.setVisibility(View.GONE);
                dsv.setAdapter(new MonthAdapter(getActivity()));
                break;
            case 5:
                gender = 1;
                llGender.setVisibility(View.VISIBLE);
                dsv.setAdapter(new MonthAdapter(getActivity()));
                break;
            case 6:
                gender = 1;
                llGender.setVisibility(View.VISIBLE);
                dsv.setAdapter(new MonthAdapter(getActivity()));
                break;
            case 7:
                gender = 0;
                llGender.setVisibility(View.GONE);
                dsv.setAdapter(new ShapeAdapter(getActivity()));
                break;
            case 9:
                gender = 0;
                llGender.setVisibility(View.GONE);
                dsv.setAdapter(new BloodTypeAdapter(getActivity()));
                break;
            case 10:
                gender = 0;
                llGender.setVisibility(View.GONE);
                dsv.setAdapter(new CheeseAdapter(getActivity()));
                break;
            case 11:
                gender = 0;
                llGender.setVisibility(View.GONE);
                dsv.setAdapter(new FroutsAdapter(getActivity()));
                break;
            case 12:
                gender = 0;
                llGender.setVisibility(View.GONE);
                dsv.setAdapter(new SeseansAdapter(getActivity()));
                break;
            case 13:
                btnKoli.setVisibility(View.VISIBLE);
                btnWomen.setBackgroundResource(R.color.white);
                btnMen.setBackgroundResource(R.drawable.topleftwhite);
                btnWomen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                btnMen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                btnKoli.setBackgroundResource(R.drawable.toprightprimary);
                btnKoli.setTextColor(getResources().getColor(R.color.white));
                gender = 0;
                llGender.setVisibility(View.VISIBLE);
                dsv.setAdapter(new MonthAdapter(getActivity()));
                break;
            case 14:
                gender = 0;
                llGender.setVisibility(View.GONE);
                dsv.setAdapter(new EyesAdapter(getActivity()));
                break;
        }


         setNewData();


    }

    private void setNewData(){
        String desc = "";
        if (CatId!=0) {
            desc = AppDatabase.getAppDatabase(getContext()).type1Dao().findByCatIdAndModelAndGender(CatId,model,gender).getDescription();
        }else {
            if (data.size()>0){
                desc = data.get(model-1);
            }
        }
        txtMatn.setText(desc);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            initData();
        }
        super.onHiddenChanged(hidden);
    }

    public void getDailyOmen() {
        final Loading loading = new Loading(getContext());
        loading.showdialog();
        Ion.with(getContext()).load(Global.DailyOmen).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {

                if (loading !=null){
                    try {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loading.dismiss();
                            }
                        });
                    }catch (NullPointerException e1){
                        e1.printStackTrace();
                    }
                }

                if (e != null) {
                    Log.e("getDailyOmen","e : "+e.toString());
                    return;
                }
                Log.e("getDailyOmen","result : "+result.toString());
                int Success = result.get("success").getAsInt();
                if (Success==1){
                    JsonArray ja = result.get("fall").getAsJsonArray();
                    JsonObject jo = ja.get(0).getAsJsonObject();
                    data.add(jo.get("m0").getAsString());
                    data.add(jo.get("m1").getAsString());
                    data.add(jo.get("m2").getAsString());
                    data.add(jo.get("m3").getAsString());
                    data.add(jo.get("m4").getAsString());
                    data.add(jo.get("m5").getAsString());
                    data.add(jo.get("m6").getAsString());
                    data.add(jo.get("m7").getAsString());
                    data.add(jo.get("m8").getAsString());
                    data.add(jo.get("m9").getAsString());
                    data.add(jo.get("m10").getAsString());
                    data.add(jo.get("m11").getAsString());
                    data.add(jo.get("m12").getAsString());

                    txtDate.setText(jo.get("tarikh").getAsString());
                    txtMatn.setText(data.get(model-1));
                }else {
                    Snackbar snackbar = Snackbar
                            .make(getView(), "متاسفانه خطایی رخ داد.", Snackbar.LENGTH_LONG)
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
}
