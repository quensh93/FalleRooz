package srp.com.fallrooz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

import srp.com.fallrooz.Db.database;
import srp.com.fallrooz.R;

import static srp.com.fallrooz.util.Global.BackTo;
import static srp.com.fallrooz.util.Global.iranSans;

/**
 * Created by s.rahmanipour on 9/10/2016.
 */
public class AboutUs extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_aboutus, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);

    }
    private void initUI(View view){

        initData();
    }

    private void initData(){
        BackTo = 1;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            initData();
        }
        super.onHiddenChanged(hidden);
    }
}
