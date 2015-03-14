package BBDD;
import java.io.Serializable;
/**
 * Created by Miguel on 27/02/2015.
 */
public class Questions implements Serializable{
    private int idQuestion;
    private String textQuestion;
    private String Category;

    public Questions(int idQuestion, String textQuestion, String category) {
        this.idQuestion = idQuestion;
        this.textQuestion = textQuestion;
        this.Category = category;
    }
    public Questions(){
    }
    public int getIdQuestion() {
        return idQuestion;
    }
    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }
    public String getTextQuestion() {
        return textQuestion;
    }
    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }
    public String getCategory() {
        return Category;
    }
    public void setCategory(String category) {
        Category = category;
    }
    @Override
    public String toString() {
        return "Questions{" +
                "idQuestion=" + idQuestion +
                ", textQuestion='" + textQuestion + '\'' +
                ", Category='" + Category + '\'' +
                '}';
    }
}