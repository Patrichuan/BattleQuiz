package BBDD;
/**
 * Created by Miguel on 27/02/2015.
 */
public class Answers {
    private int idAnswer;
    private String textAnswer;
    private int idQuestion;
    private int isCorrect;
    public Answers(int idAnswer, String textAnswer, int isCorrect, int idQuestion) {
        this.idAnswer = idAnswer;
        this.isCorrect = isCorrect;
        this.idQuestion = idQuestion;
        this.textAnswer = textAnswer;
    }
    public Answers(){
    }
    public int getIdAnswer() {
        return idAnswer;
    }
    public void setIdAnswer(int idAnswer) {
        this.idAnswer = idAnswer;
    }
    public String getTextAnswer(String string) {
        return textAnswer;
    }
    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }
    public int getIdQuestion() {
        return idQuestion;
    }
    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }
    public int isCorrect() {
        return isCorrect;
    }
    public void setCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
    }
    @Override
    public String toString() {
        return "Answers{" +
                "idAnswer=" + idAnswer +
                ", textAnswer='" + textAnswer + '\'' +
                ", idQuestion=" + idQuestion +
                ", isCorrect=" + isCorrect +
                '}';
    }
}