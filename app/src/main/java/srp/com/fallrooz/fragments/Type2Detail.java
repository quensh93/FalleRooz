package srp.com.fallrooz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import srp.com.fallrooz.Db.AppDatabase;
import srp.com.fallrooz.Db.tables.Type2Db;
import srp.com.fallrooz.MainActivity;
import srp.com.fallrooz.R;

import static srp.com.fallrooz.util.Global.BackTo;
import static srp.com.fallrooz.util.Global.CatId;
import static srp.com.fallrooz.util.Global.iranSans;

/**
 * Created by s.rahmanipour on 9/10/2016.
 */
public class Type2Detail extends Fragment{


    private ImageButton ibMenu;
    private TextView txtMatn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_type2detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);

    }

    private void initUI(View view){

        ibMenu = view.findViewById(R.id.ib_menu);
        txtMatn = view.findViewById(R.id.txt_matn);
        txtMatn.setTypeface(iranSans);
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
        String desc = AppDatabase.getAppDatabase(getContext()).type1Dao().findByCatId(CatId).getDescription();
        txtMatn.setText(desc);
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            initData();
        }
        super.onHiddenChanged(hidden);
    }
}
