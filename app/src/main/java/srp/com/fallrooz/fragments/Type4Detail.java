package srp.com.fallrooz.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import srp.com.fallrooz.Db.AppDatabase;
import srp.com.fallrooz.Db.tables.Type1Db;
import srp.com.fallrooz.MainActivity;
import srp.com.fallrooz.R;
import srp.com.fallrooz.databinding.FragmentType4detailBinding;
import srp.com.fallrooz.util.Helper;

import static srp.com.fallrooz.util.Global.BackTo;
import static srp.com.fallrooz.util.Global.CatId;

/**
 * Created by s.rahmanipour on 9/10/2016.
 */
public class Type4Detail extends Fragment{

    private FragmentType4detailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_type4detail, container, false);
        binding.setFragment(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }
    public void onMenuClick(){
        Log.e("srptesting","onMenuClick");
        MainActivity.MenuControl();
    }
    public void onButtonClick(){
        Log.e("srptesting","onButtonClick");
        switch (CatId){
            case 17:
                int num = Helper.NameRenge(binding.tieName.getText().toString());
                if (num>10){
                    calculate(num);
                }else {
                    setData(num);
                }
                break;
            case 19:
                int num1 = Helper.CheckFirstName(binding.tieName.getText().toString());
                setData(num1);
                break;
        }
    }
    private void setData(int num){
        //Type1Db type1Db = Type1Db.find(Type1Db.class,"cat_id = ? AND model = ?",new String[]{""+CatId,""+num}).get(0);
        Type1Db type1Db = AppDatabase.getAppDatabase(getContext()).type1Dao().findByCatIdAndModel(CatId,num);
        binding.setType1db(type1Db);
        if (CatId==17){
            String[] mTestArray = getResources().getStringArray(R.array.colors);
            binding.txtMatn.setText(mTestArray[(num-1)] +"\n"+type1Db.getDescription());
        }else {
            binding.txtMatn.setText(type1Db.getDescription());
        }
    }


    private void calculate(int num){
        String s = ""+num;
        num = 0;
        for (int i = 0; i < s.length(); i++) {
            num = num+Integer.parseInt(Character.toString(s.charAt(i)));
        }
        if (num>9){
            calculate(num);
        }else {
            setData(num);
        }
    }
    private void initData(){
        BackTo = 1;
        binding.txtMatn.setText("");
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            initData();
        }
        super.onHiddenChanged(hidden);
    }
}
