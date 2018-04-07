package srp.com.fallrooz.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import srp.com.fallrooz.R;

import static srp.com.fallrooz.util.FragmentHelper.changeFragment;
import static srp.com.fallrooz.util.FragmentHelper.fragmentManager;
import static srp.com.fallrooz.util.Global.BackTo;
import static srp.com.fallrooz.util.Global.CatId;
import static srp.com.fallrooz.util.Global.iranSans;

/**
 * Created by s.rahmanipour on 9/10/2016.
 */
public class Home extends Fragment implements View.OnClickListener{

    private LinearLayout ll1;
    private TextView txt1;
    private LinearLayout ll2;
    private TextView txt2;
    private LinearLayout ll3;
    private TextView txt3;
    private LinearLayout ll4;
    private TextView txt4;
    private LinearLayout ll5;
    private TextView txt5;
    private LinearLayout ll6;
    private TextView txt6;
    private LinearLayout ll7;
    private TextView txt7;
    private LinearLayout ll8;
    private TextView txt8;
    private LinearLayout ll9;
    private TextView txt9;
    private LinearLayout ll10;
    private TextView txt10;
    private LinearLayout ll11;
    private TextView txt11;
    private LinearLayout ll12;
    private TextView txt12;
    private LinearLayout ll13;
    private TextView txt13;
    private LinearLayout ll14;
    private TextView txt14;
    private LinearLayout ll15;
    private TextView txt15;
    private LinearLayout ll16;
    private TextView txt16;
    private LinearLayout ll17;
    private TextView txt17;
    private LinearLayout ll18;
    private TextView txt18;
    private LinearLayout ll19;
    private TextView txt19;
    private LinearLayout ll20;
    private TextView txt20;
    private LinearLayout ll21;
    private TextView txt21;
    private LinearLayout ll22;
    private TextView txt22;
    private LinearLayout ll23;
    private TextView txt23;
    private LinearLayout ll24;
    private TextView txt24;
    private TextView txtTitle1;
    private TextView txtTitle2;
    private ImageView imgStar;
    private boolean isPause = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);

    }

    private void initUI(View view){
        imgStar = view.findViewById(R.id.img_star);
        txtTitle1 = view.findViewById(R.id.txt_title1);
        txtTitle2 = view.findViewById(R.id.txt_title2);
        ll1 = view.findViewById(R.id.ll1);
        txt1 = view.findViewById(R.id.txt1);
        ll2 = view.findViewById(R.id.ll2);
        txt2 = view.findViewById(R.id.txt2);
        ll3 = view.findViewById(R.id.ll3);
        txt3 = view.findViewById(R.id.txt3);
        ll4 = view.findViewById(R.id.ll4);
        txt4 = view.findViewById(R.id.txt4);
        ll5 = view.findViewById(R.id.ll5);
        txt5 = view.findViewById(R.id.txt5);
        ll6 = view.findViewById(R.id.ll6);
        txt6 = view.findViewById(R.id.txt6);
        ll7 = view.findViewById(R.id.ll7);
        txt7 = view.findViewById(R.id.txt7);
        ll8 = view.findViewById(R.id.ll8);
        txt8 = view.findViewById(R.id.txt8);
        ll9 = view.findViewById(R.id.ll9);
        txt9 = view.findViewById(R.id.txt9);
        ll10 = view.findViewById(R.id.ll10);
        txt10 = view.findViewById(R.id.txt10);
        ll11 = view.findViewById(R.id.ll11);
        txt11 = view.findViewById(R.id.txt11);
        ll12 = view.findViewById(R.id.ll12);
        txt12 = view.findViewById(R.id.txt12);
        ll13 = view.findViewById(R.id.ll13);
        txt13 = view.findViewById(R.id.txt13);
        ll14 = view.findViewById(R.id.ll14);
        txt14 = view.findViewById(R.id.txt14);
        ll15 = view.findViewById(R.id.ll15);
        txt15 = view.findViewById(R.id.txt15);
        ll16 = view.findViewById(R.id.ll16);
        txt16 = view.findViewById(R.id.txt16);
        ll17 = view.findViewById(R.id.ll17);
        txt17 = view.findViewById(R.id.txt17);
        ll18 = view.findViewById(R.id.ll18);
        txt18 = view.findViewById(R.id.txt18);
        ll19 = view.findViewById(R.id.ll19);
        txt19 = view.findViewById(R.id.txt19);
        ll20 = view.findViewById(R.id.ll20);
        txt20 = view.findViewById(R.id.txt20);
        ll21 = view.findViewById(R.id.ll21);
        txt21 = view.findViewById(R.id.txt21);
        ll22 = view.findViewById(R.id.ll22);
        txt22 = view.findViewById(R.id.txt22);
        ll23 = view.findViewById(R.id.ll23);
        txt23 = view.findViewById(R.id.txt23);
        ll24 = view.findViewById(R.id.ll24);
        txt24 = view.findViewById(R.id.txt24);

        txt1.setTypeface(iranSans);
        txt2.setTypeface(iranSans);
        txt3.setTypeface(iranSans);
        txt4.setTypeface(iranSans);
        txt5.setTypeface(iranSans);
        txt6.setTypeface(iranSans);
        txt7.setTypeface(iranSans);
        txt8.setTypeface(iranSans);
        txt9.setTypeface(iranSans);
        txt10.setTypeface(iranSans);
        txt11.setTypeface(iranSans);
        txt12.setTypeface(iranSans);
        txt13.setTypeface(iranSans);
        txt14.setTypeface(iranSans);
        txt15.setTypeface(iranSans);
        txt16.setTypeface(iranSans);
        txt17.setTypeface(iranSans);
        txt18.setTypeface(iranSans);
        txt19.setTypeface(iranSans);
        txt20.setTypeface(iranSans);
        txt21.setTypeface(iranSans);
        txt22.setTypeface(iranSans);
        txt23.setTypeface(iranSans);
        txt24.setTypeface(iranSans);
        txtTitle1.setTypeface(iranSans);
        txtTitle2.setTypeface(iranSans);

        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        ll4.setOnClickListener(this);
        ll5.setOnClickListener(this);
        ll6.setOnClickListener(this);
        ll7.setOnClickListener(this);
        ll8.setOnClickListener(this);
        ll9.setOnClickListener(this);
        ll10.setOnClickListener(this);
        ll11.setOnClickListener(this);
        ll12.setOnClickListener(this);
        ll13.setOnClickListener(this);
        ll14.setOnClickListener(this);
        ll15.setOnClickListener(this);
        ll16.setOnClickListener(this);
        ll17.setOnClickListener(this);
        ll18.setOnClickListener(this);
        ll19.setOnClickListener(this);
        ll20.setOnClickListener(this);
        ll21.setOnClickListener(this);
        ll22.setOnClickListener(this);
        ll23.setOnClickListener(this);
        ll24.setOnClickListener(this);

        initData();
    }

    private void initData(){
        BackTo = 0;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!isPause){
                    if (imgStar.getVisibility()==View.VISIBLE){
                        imgStar.setVisibility(View.INVISIBLE);
                    }else {
                        imgStar.setVisibility(View.VISIBLE);
                    }
                    initData();
                }

            }
        },300);
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            isPause = false;
            initData();
        }else {
            isPause = true;
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll1:
                CatId = 1;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type1Detail(),"Type1Detail");
                break;
            case R.id.ll2:
                CatId = 7;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type1Detail(),"Type1Detail");
                break;
            case R.id.ll3:
                CatId = 22;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type3Detail(),"Type3Detail");
                break;
            case R.id.ll4:
                CatId = 8;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type2Detail(),"Type2Detail");
                break;
            case R.id.ll5:
                CatId = 21;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type3Detail(),"Type3Detail");
                break;
            case R.id.ll6:
                CatId = 20;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type3Detail(),"Type3Detail");
                break;
            case R.id.ll7:
                CatId = 19;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type4Detail(),"Type4Detail");
                break;
            case R.id.ll8:
                CatId = 5;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type1Detail(),"Type1Detail");
                break;
            case R.id.ll9:
                CatId = 2;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type1Detail(),"Type1Detail");
                break;
            case R.id.ll10:
                CatId = 18;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type3Detail(),"Type3Detail");
                break;
            case R.id.ll11:
                CatId = 17;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type4Detail(),"Type4Detail");
                break;
            case R.id.ll12:
                CatId = 14;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type1Detail(),"Type1Detail");
                break;
            case R.id.ll13:
                CatId = 16;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type3Detail(),"Type3Detail");
                break;
            case R.id.ll14:
                CatId = 3;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type1Detail(),"Type1Detail");
                break;
            case R.id.ll15:
                CatId = 13;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type1Detail(),"Type1Detail");
                break;
            case R.id.ll16:
                CatId = 12;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type1Detail(),"Type1Detail");
                break;
            case R.id.ll17:
                CatId = 4;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type1Detail(),"Type1Detail");
                break;
            case R.id.ll18:
                CatId = 11;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type1Detail(),"Type1Detail");
                break;
            case R.id.ll19:
                CatId = 6;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type1Detail(),"Type1Detail");
                break;
            case R.id.ll20:
                CatId = 15;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type3Detail(),"Type3Detail");
                break;
            case R.id.ll21:
                CatId = 10;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type1Detail(),"Type1Detail");
                break;
            case R.id.ll22:
                CatId = 9;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type1Detail(),"Type1Detail");
                break;
            case R.id.ll23:
                CatId = 0;
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Type1Detail(),"Type1Detail");
                break;
            case R.id.ll24:
                fragmentManager = getActivity().getSupportFragmentManager();
                changeFragment(new Hafez(),"Hafez");
                break;

        }
    }
}
