package model;

public class QuizModel {

    static  int generalID = 0;
    private int quizId;
    private String savol;
    private String javob1;
    private String javob2;
    private String javob3;
    private String javob4;
    private int topic_id;
    private int togri_javob_id;

    public int getQuizId() {
        return quizId;
    }

    public String getSavol() {
        return savol;
    }

    public String getJavob1() {
        return javob1;
    }

    public String getJavob2() {
        return javob2;
    }

    public String getJavob3() {
        return javob3;
    }

    public String getJavob4() {
        return javob4;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public int getTogri_javob_id() {
        return togri_javob_id;
    }

    public QuizModel(String savol, String javob1, String javob2, String javob3, String javob4, int topic_id, int togri_javob_id) {
        this.savol = savol;
        this.javob1 = javob1;
        this.javob2 = javob2;
        this.javob3 = javob3;
        this.javob4 = javob4;
        this.topic_id = topic_id;
        this.togri_javob_id = togri_javob_id;
        this.quizId = generalID;
        generalID ++;
    }

    public QuizModel(int id, String savol, String javob1, String javob2, String javob3, String javob4, int topic_id, int togri_javob_id) {
        this.quizId = id;
        this.savol = savol;
        this.javob1 = javob1;
        this.javob2 = javob2;
        this.javob3 = javob3;
        this.javob4 = javob4;
        this.topic_id = topic_id;
        this.togri_javob_id = togri_javob_id;
    }

    static void  generateIdFirtly(){

    }
}
