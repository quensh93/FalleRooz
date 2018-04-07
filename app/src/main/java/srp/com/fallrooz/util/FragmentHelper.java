package srp.com.fallrooz.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import srp.com.fallrooz.MainActivity;
import srp.com.fallrooz.R;

/**
 * Created by s.rahmanipour on 10/18/2017.
 */

public class FragmentHelper {
    public static FragmentTransaction fragmentTransaction;
    public static FragmentManager fragmentManager;
    public static String lastTagMain ="";

    public static void addFragment(Fragment fr, String tag){
        Log.e("FragmentHelper","addFragment");
        if (fragmentManager!=null){
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fl_main,fr,tag);
            if (lastTagMain!=null && !lastTagMain.isEmpty() && !lastTagMain.equals("")){
                fragmentTransaction.hide(findFragmentByTag(lastTagMain));
            }else {
                Log.e("FragmentHelper","lastTagMain null");
            }
            lastTagMain = tag;
            fragmentTransaction.commit();
            Log.e("FragmentHelper","addFragment done!");
        }else {
            Log.e("FragmentHelper","fragmentManager null");
        }
    }
    public static void changeFragment(Fragment fr, String tag){

        Log.e("FragmentHelper","changeFragment start");
        try {
            Log.e("FragmentHelper","isFragmentExist(tag) : "+isFragmentExist(tag));
            if (isFragmentExist(tag)){
                Fragment newf = findFragmentByTag(tag);
                Fragment old = null;
                old = findFragmentByTag(lastTagMain);
                setVisibleFragment(old,newf);
            }else {
                addFragment(fr,tag);
            }
        }catch (IllegalStateException e){
            Log.e("FragmentHelper","IllegalStateException : "+e.toString());
            e.printStackTrace();
        }
    }
    public static boolean isFragmentExist(String tag){
        if (fragmentManager!=null){
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment !=null){
                return true;
            }else {
                return false;
            }
        }else {
            Log.e("FragmentHelper","fragmentManager null");
            return false;
        }
    }
    public static Fragment findFragmentByTag(String tag){
        if (fragmentManager!=null){
            return  fragmentManager.findFragmentByTag(tag);
        }else {
            Log.e("FragmentHelper","fragmentManager null");
            return  null;
        }

    }
    public static void setVisibleFragment(Fragment old, Fragment newf){
        if (fragmentManager!=null){
            lastTagMain = newf.getTag();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.hide(old);
            fragmentTransaction.show(newf);
            fragmentTransaction.commit();
        }else {
            Log.e("FragmentHelper","fragmentManager null");
        }

    }
}
