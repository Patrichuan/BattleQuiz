package BBDD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Miguel on 27/02/2015.
 */
public class Querys extends ConnectSQLite {

    public Querys(Context context) {
        super(context);
    }

    public void Question(int idQuestion) {

        String query = "SELECT * FROM Question WHERE idquestion = " + idQuestion;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Questions question = new Questions();

        if (cursor.moveToFirst()) {
            do{
                int idPregunta = cursor.getInt(0);
                String textPregunta = cursor.getString(1);
                String Categoria = cursor.getString(2);
            }while (cursor.moveToNext());
            question = null;
        }

        db.close();
    }

    public void Answer(int idQuestion) {

        String query = "SELECT * FROM Answer WHERE idQuestionFK = " + idQuestion;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Answers respuesta = new Answers();

        if (cursor.moveToFirst()){
            do{
                int idRespuesta = cursor.getInt(0);
                String textRespuesta = cursor.getString(1);
                int idPregunta = cursor.getInt(2);
                int esCorrecta = cursor.getInt(3);
            }while (cursor.moveToNext());
            respuesta = null;
        }
    }
}
