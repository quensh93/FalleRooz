package srp.com.fallrooz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import ir.huri.jcal.JalaliCalendar;
import srp.com.fallrooz.Db.AppDatabase;
import srp.com.fallrooz.Db.tables.Type1Db;
import srp.com.fallrooz.Db.tables.Type3Db;
import srp.com.fallrooz.MainActivity;
import srp.com.fallrooz.R;
import srp.com.fallrooz.util.Helper;

import static srp.com.fallrooz.util.Global.BackTo;
import static srp.com.fallrooz.util.Global.CatId;
import static srp.com.fallrooz.util.Global.iranSans;

/**
 * Created by s.rahmanipour on 9/10/2016.
 */
public class Type3Detail extends Fragment{


    private ImageButton ibMenu;
    private WheelPicker wpYear;
    private WheelPicker wpMonth;
    private WheelPicker wpDay;
    private TextView txtMatn;
    private ArrayList<Integer> Year,Day30,Day31;
    private ArrayList<String> Month;
    private Button btnNamayesh;
    private int AdadeSal,AdadeMah,AdadeRooz,AdadeSarnevesht,AdadeTaghdir;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_type3detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);

    }

    private void initUI(View view){

        ibMenu = view.findViewById(R.id.ib_menu);
        wpYear = view.findViewById(R.id.wp_year);
        wpMonth = view.findViewById(R.id.wp_month);
        wpDay = view.findViewById(R.id.wp_day);
        txtMatn = view.findViewById(R.id.txt_matn);
        btnNamayesh = view.findViewById(R.id.btn_namayesh);

        btnNamayesh.setTypeface(iranSans);
        wpYear.setTypeface(iranSans);
        wpMonth.setTypeface(iranSans);
        wpDay.setTypeface(iranSans);
        txtMatn.setTypeface(iranSans);

        Year = new ArrayList<>();
        Month = new ArrayList<>();
        Day30 = new ArrayList<>();
        Day31 = new ArrayList<>();

        for (int i = 1320; i < 1398; i++) {
            Year.add(i);
        }
        Month.add("فروردین");
        Month.add("اردیبهشت");
        Month.add("خرداد");
        Month.add("تیر");
        Month.add("مرداد");
        Month.add("شهریور");
        Month.add("مهر");
        Month.add("آبان");
        Month.add("آذر");
        Month.add("دی");
        Month.add("بهمن");
        Month.add("اسفند");

        for (int i = 1; i < 31; i++) {
            Day30.add(i);
            Day31.add(i);
        }
        Day31.add(31);


        wpYear.setData(Year);
        wpMonth.setData(Month);
        wpDay.setData(Day30);

        wpMonth.setOnWheelChangeListener(new WheelPicker.OnWheelChangeListener() {
            @Override
            public void onWheelScrolled(int offset) {

            }

            @Override
            public void onWheelSelected(int position) {
                if (position<6){
                    wpDay.setData(Day30);
                }else {
                    wpDay.setData(Day31);
                }
            }

            @Override
            public void onWheelScrollStateChanged(int state) {

            }
        });

        btnNamayesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = Year.get(wpYear.getCurrentItemPosition());
                int month = wpMonth.getCurrentItemPosition()+1;
                int day = wpDay.getCurrentItemPosition()+1;



                switch (CatId){
                    case 15:
                        int reng = 0;
                        if (month==1){
                            reng = day;
                        }else if (month<8){
                            reng = ((month-1)*31)+day;
                        }else{
                            reng = (6*31)+ ((month-7)*30)+day;
                        }
                        if (month==1){

                            if(day<26){
                                Type3Db type3Db = AppDatabase.getAppDatabase(getContext()).type1Dao().findByCatIdAndRenge(CatId,25,363);
                                txtMatn.setText(type3Db.getGender()+"\n"+type3Db.getDescription());
                            }else {
                                Type3Db type3Db = AppDatabase.getAppDatabase(getContext()).type1Dao().findByCatIdAndRenge(CatId,53,26);
                                txtMatn.setText(type3Db.getGender()+"\n"+type3Db.getDescription());
                            }


                        }else if (month==12){
                            if(day>26){
                                Type3Db type3Db = AppDatabase.getAppDatabase(getContext()).type1Dao().findByCatIdAndRenge(CatId,25,363);
                                txtMatn.setText(type3Db.getGender()+"\n"+type3Db.getDescription());
                            }else {
                                Type3Db type3Db = AppDatabase.getAppDatabase(getContext()).type1Dao().findByCatIdAndRenge(CatId,362,335);
                                txtMatn.setText(type3Db.getGender()+"\n"+type3Db.getDescription());
                            }
                        }else {
                            Type3Db type3Db = AppDatabase.getAppDatabase(getContext()).type1Dao().findByCatIdAndRenge(CatId,reng);
                            txtMatn.setText(type3Db.getGender()+"\n"+type3Db.getDescription());
                        }
                        break;
                    case 16:
                        JalaliCalendar jalaliCalendar = new JalaliCalendar(year, month, day);
                        Type1Db type1Db = AppDatabase.getAppDatabase(getContext()).type1Dao().findByCatIdAndModel(CatId,jalaliCalendar.getDayOfWeek());
                        txtMatn.setText(jalaliCalendar.getDayOfWeekString()+"\n"+type1Db.getDescription());
                        break;
                    case 18:
                        int reng1 = 0;
                        if (month==1){
                            reng1 = day;
                        }else if (month<8){
                            reng1 = ((month-1)*31)+day;
                        }else{
                            reng1 = (6*31)+ ((month-7)*30)+day;
                        }
                        Type3Db type3Db = AppDatabase.getAppDatabase(getContext()).type1Dao().findByCatIdAndRenge(CatId,reng1);

                        txtMatn.setText(type3Db.getGender()+"\n"+type3Db.getDescription());
                        break;
                    case 20:
                        int reng2 = 0;
                        if (month==1){
                            reng2 = day;
                        }else if (month<8){
                            reng2 = ((month-1)*31)+day;
                        }else{
                            reng2 = (6*31)+ ((month-7)*30)+day;
                        }
                        Type3Db type3Db1 = AppDatabase.getAppDatabase(getContext()).type1Dao().findByCatIdAndRenge(CatId,reng2);

                        txtMatn.setText(type3Db1.getGender()+"\n"+type3Db1.getDescription());
                        break;
                    case 21:
                        JalaliCalendar jalaliCalendar1 = new JalaliCalendar(year, month, day);
                        GregorianCalendar gc = jalaliCalendar1.toGregorian();
                        Date en = gc.getTime();
                        int dd = en.getDay();
                        int model = setdata1(dd);
                        Type1Db type1Db1 = AppDatabase.getAppDatabase(getContext()).type1Dao().findByCatIdAndModel(CatId,model);
                        txtMatn.setText(type1Db1.getDescription());
                        break;
                    case 22:
                        JalaliCalendar jalaliCalendar2 = new JalaliCalendar(year, month, day);
                        GregorianCalendar gc1 = jalaliCalendar2.toGregorian();
                        Date en1 = gc1.getTime();
                        String fDate = new SimpleDateFormat("yyyy-MM-dd").format(gc1.getTime());

                        String [] splite = fDate.split("-");

                        checknums(1,splite[0]);
                        checknums(2,splite[1]);
                        checknums(3,splite[2]);
                        checknums(4,""+AdadeSal+AdadeMah+AdadeRooz);
                        checknums(5,""+AdadeSarnevesht+month);
                        setNumData();
                        break;
                }
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

    private void setNumData(){
        Log.e("setNumData","AdadeTaghdir : "+AdadeTaghdir);
        Log.e("setNumData","CatId : "+CatId);
        Type1Db type1Db1 = AppDatabase.getAppDatabase(getContext()).type1Dao().findByCatIdAndModel(CatId,AdadeTaghdir);
        txtMatn.setText("عدد تقدیر شما : "+type1Db1.getModel()+"\n"+type1Db1.getDescription());
    }

    private void checknums(int model ,String input){
        Log.e("setNumData","model : "+model+" | input : "+input);
        int y = Helper.CheckSum(input);
        if (y < 10 || y==11 || y==22){
            if (model==1){
                AdadeSal = y;
                Log.e("setNumData","AdadeSal : "+AdadeSal);
            }else if (model==2){
                AdadeMah = y;
                Log.e("setNumData","AdadeMah : "+AdadeMah);
            }else if (model==3){
                AdadeRooz = y;
                Log.e("setNumData","AdadeRooz : "+AdadeRooz);
            }else if (model==4){
                AdadeSarnevesht = y;
                Log.e("setNumData","AdadeSarnevesht : "+AdadeSarnevesht);
            }else if (model==5){
                AdadeTaghdir = y;
                Log.e("setNumData","AdadeTaghdir : "+AdadeTaghdir);
            }
        }else {
            checknums(model,""+y);
        }
    }



    private int setdata1(int day){
        int model = 0;
        switch (day){
            case 1:
            case 6:
            case 11:
            case 16:
            case 21:
            case 26:
            case 31:
                model = 1;
                break;
            case 2:
            case 7:
            case 12:
            case 17:
            case 22:
            case 27:
                model = 2;
                break;
            case 3:
            case 8:
            case 13:
            case 18:
            case 23:
            case 28:
                model = 3;
                break;
            case 4:
            case 9:
            case 14:
            case 19:
            case 24:
            case 29:
                model = 4;
                break;
            case 5:
            case 10:
            case 15:
            case 20:
            case 25:
            case 30:
                model = 5;
                break;
        }
        return model;
    }


    private void initData(){
        BackTo = 1;
        txtMatn.setText("");
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            initData();
        }
        super.onHiddenChanged(hidden);
    }
}
