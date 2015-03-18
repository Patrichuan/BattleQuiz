package es.patrichuan.battlequiz;

/**
 * Created by daniel on 24/01/15.
 */

import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;

public final class FontsOverride {

    static String FONTPATH = "fonts/HVD_Comic_Serif_Pro.otf";

    public static void setDefaultFont(Context context, String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(), fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName, final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class.getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setPasswordFont(Context context, EditText editText){

        // Cargamos la fuente:
        Typeface tf = Typeface.createFromAsset(context.getAssets(), FONTPATH);

        // Aplicamos la fuente:
        editText.setTypeface(tf);
    }

    public static void setButtonFont(Context context, Button button){

        // Cargamos la fuente:
        Typeface tf = Typeface.createFromAsset(context.getAssets(), FONTPATH);

        // Aplicamos la fuente:
        button.setTypeface(tf);
    }

    public static void setTextViewFont(Context context, TextView textView){

        Typeface tf = Typeface.createFromAsset(context.getAssets(), FONTPATH);
        textView.setTypeface(tf);
    }

    public static void setTextViewFontFragment(Fragment fragment, TextView textView){

        Typeface tf = Typeface.createFromAsset(fragment.getActivity().getAssets(), FONTPATH);
        textView.setTypeface(tf);
    }

    public static void setEditTextFont(Context context, EditText editText){

        Typeface tf = Typeface.createFromAsset(context.getAssets(), FONTPATH);
        editText.setTypeface(tf);
    }

/*    public static void setTextViewFontGameS(GameScreen_Pregunta context, TextView textView){

        Typeface tf = Typeface.createFromAsset(GameScreen_Pregunta.context.getAssets(), FONTPATH);
        textView.setTypeface(tf);
    }*/
}
