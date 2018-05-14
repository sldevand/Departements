package fr.m2iformation.departements.tools;

import android.content.Context;
import android.widget.Toast;

public class Tools {

    public static void showToast(Context c, String message){
        Toast.makeText(c,message, Toast.LENGTH_SHORT).show();
    }

    public static boolean isNumeric(String str){

        try {
            Integer.parseInt(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
}
