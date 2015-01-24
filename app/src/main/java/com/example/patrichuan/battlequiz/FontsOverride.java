package com.example.patrichuan.battlequiz; /**
 * Created by daniel on 24/01/15.
 */
import java.lang.reflect.Field;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.EditText;

public final class FontsOverride {

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setPasswordFont(Context context, EditText editText){

        // Ruta de la fuente dentro de la carpeta assets:
        String fontPath = "fonts/HVD_Comic_Serif_Pro.otf";

        // Cargamos la fuente:
        Typeface tf = Typeface.createFromAsset(context.getAssets(), fontPath);

        // Aplicamos la fuente:
        editText.setTypeface(tf);
    }
}
