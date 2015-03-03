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

    public int countQuestions() {
        int numQuestions = 0;
        String query = "SELECT COUNT (*) FROM Question";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            numQuestions = cursor.getInt(0);
        }
        return numQuestions;
    }

    public Questions getQuestionById(int idQuestion) {
        String query = "SELECT * FROM Question WHERE idquestion = " + idQuestion;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int idPregunta;
        String textPregunta;
        String Categoria;
        Questions question = null;
        if (cursor.moveToFirst()) {
            idPregunta = cursor.getInt(0);
            textPregunta = cursor.getString(1);
            Categoria = cursor.getString(2);
            question = new Questions(idPregunta, textPregunta, Categoria);
        }
        db.close();
        return question;
    }

    public Answers[] getAnswersById(int idQuestion) {
        Answers[] respuestas = new Answers[4];
        String query = "SELECT * FROM Answer WHERE idQuestionFK = " + idQuestion;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int idRespuesta;
        String textRespuesta;
        int idPregunta;
        int esCorrecta;
        Answers respuesta;

        if (cursor.moveToFirst()) {
            int i = 0;
            do {
                idRespuesta = cursor.getInt(0);
                textRespuesta = cursor.getString(1);
                idPregunta = cursor.getInt(2);
                esCorrecta = cursor.getInt(3);
                respuesta = new Answers(idRespuesta, textRespuesta, idPregunta, esCorrecta);
                respuestas[i] = respuesta;
                i++;
            } while (cursor.moveToNext());
        }
        db.close();
        return respuestas;
    }

    public Answers[] getAnswersByQuestion(Questions Question) {
        Answers [] respuestas = getAnswersById(Question.getIdQuestion());
        return respuestas;
    }
}