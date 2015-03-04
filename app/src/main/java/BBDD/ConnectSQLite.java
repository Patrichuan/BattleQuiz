package BBDD;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * Created by Miguel on 20/02/2015.
 */

public class ConnectSQLite extends SQLiteOpenHelper {

    private static final String DB_NAME = "sqliteBD.sqlite";
    private static String DB_PATH ;
    private SQLiteDatabase myDataBase;
    private Context myContext;
    private static final int DB_SCHEME_VERSION = 1;

    public ConnectSQLite(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
        this.myContext = context;
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
        } catch (SQLException e) {
            Log.e("SQLException ", e.toString());
        }
    }

    public void borrarDataBase() {
        myContext.deleteDatabase(DB_NAME);
    }

    public void createDataBase() {
    // borro la base de datos para que la copie cada vez (por los nuevos cambios),
    // solo mientras se crea el proyecto
        borrarDataBase();
        File dbFile = myContext.getDatabasePath(DB_NAME);
        boolean dbExist = dbFile.exists();
        if (dbExist) {
            borrarDataBase();
        } else {
            this.getReadableDatabase();
            copiarbasededatos();
        }
    }

    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    public void copiarbasededatos() {
    //coge la base de datos db_name de main/assets para copiarla
        InputStream myInput = null;
        try {
            myInput = myContext.getAssets().open(DB_NAME);
            // la copia dentro de android en data/data/..../databases/
            String outFileName = DB_PATH + DB_NAME;
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}