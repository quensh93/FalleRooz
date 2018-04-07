package srp.com.fallrooz.util;

import android.util.Log;

/**
 * Created by s.rahmanipour on 1/15/2018.
 */

public class Helper {

    public static int Num = 9;

    public static int NameRenge(String name){
        Log.e("setNumData","name : "+name);
        int src = 0;
        for (int i = 0; i < name.length(); i++) {
            src = src+CharNumber(name.charAt(i));
        }

        return src;
    }
    public static int CheckSum(String name){
        Log.e("setNumData","name : "+name);
        int src = 0;
        for (int i = 0; i < name.length(); i++) {
            src = src+Integer.parseInt(Character.toString(name.charAt(i)));
        }

        return src;
    }
    public static int CharNumber(char s){

        int src = 0;
        switch (Character.toString(s).toUpperCase()){
            case "ا":
                src = 1;
                break;
            case "ب":
                src = 2;
                break;
            case "پ":
                src = 7;
                break;
            case "ت":
                src = 2;
                break;
            case "ث":
                src = 2;
                break;
            case "ج":
                src = 1;
                break;
            case "چ":
                src = 5;
                break;
            case "ح":
                src = 6;
                break;
            case "خ":
                src = 6;
                break;
            case "د":
                src = 4;
                break;
            case "ذ":
                src = 9;
                break;
            case "ر":
                src = 9;
                break;
            case "ز":
                src = 8;
                break;
            case "ژ":
                src = 4;
                break;
            case "س":
                src = 1;
                break;
            case "ش":
                src = 1;
                break;
            case "ص":
                src = 3;
                break;
            case "ض":
                src = 3;
                break;
            case "ط":
                src = 5;
                break;
            case "ظ":
                src = 5;
                break;
            case "ع":
                src = 7;
                break;
            case "غ":
                src = 7;
                break;
            case "ف":
                src = 6;
                break;
            case "ق":
                src = 8;
                break;
            case "ک":
                src = 2;
                break;
            case "گ":
                src = 9;
                break;
            case "ل":
                src = 3;
                break;
            case "م":
                src = 4;
                break;
            case "ن":
                src = 5;
                break;
            case "و":
                src = 4;
                break;
            case "ه":
                src = 8;
                break;
            case "ی":
                src = 3;
                break;

        }
        return src;
    }

    public static int CheckFirstName(String s){
        int src = 0;
        char c = s.charAt(0);
        Log.e("CheckFirstName","s: "+s);
        Log.e("CheckFirstName","c: "+Character.toString(c));
        switch (Character.toString(c).toUpperCase()){
            case "ا":
                src = 1;
                break;
            case "ب":
                src = 2;
                break;
            case "پ":
                src = 2;
                break;
            case "ت":
                src = 3;
                break;
            case "ث":
                src = 4;
                break;
            case "ج":
                src = 6;
                break;
            case "چ":
                src = 6;
                break;
            case "ح":
                src = 5;
                break;
            case "خ":
                src = 7;
                break;
            case "د":
                src = 8;
                break;
            case "ذ":
                src = 8;
                break;
            case "ر":
                src = 27;
                break;
            case "ز":
                src = 9;
                break;
            case "ژ":
                src = 9;
                break;
            case "س":
                src = 10;
                break;
            case "ش":
                src = 11;
                break;
            case "ص":
                src = 12;
                break;
            case "ض":
                src = 13;
                break;
            case "ط":
                src = 14;
                break;
            case "ظ":
                src = 15;
                break;
            case "ع":
                src = 16;
                break;
            case "غ":
                src = 17;
                break;
            case "ف":
                src = 18;
                break;
            case "ق":
                src = 19;
                break;
            case "ک":
                src = 20;
                break;
            case "گ":
                src = 20;
                break;
            case "ل":
                src = 21;
                break;
            case "م":
                src = 22;
                break;
            case "ن":
                src = 23;
                break;
            case "و":
                src = 24;
                break;
            case "ه":
                src = 25;
                break;
            case "ی":
                src = 26;
                break;

        }

        Log.e("CheckFirstName","src: "+src);
        return src;
    }


}
