package org.campus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TGUser {
    private Long userId;
    private List<Question> questions = new ArrayList<>();

    private Integer backendPoint = 0;
    private Integer frontendPoint = 0;
    private Integer qaPoint = 0;

    public TGUser(Long userId) {
        this.userId = userId;
        fillQuestions();
    }

    private void fillQuestions(){
        questions.add(new Question(Texts.Q1, Arrays.asList(
                new Answer(Texts.Q1_A1, LanguageType.FRONTEND),
                new Answer(Texts.Q1_A2, LanguageType.FRONTEND),
                new Answer(Texts.Q1_A3, LanguageType.BACKEND),
                new Answer(Texts.Q1_A4, LanguageType.QA)
        )));

        questions.add(new Question(Texts.Q2, Arrays.asList(
                new Answer(Texts.Q2_A1, LanguageType.FRONTEND),
                new Answer(Texts.Q2_A2, LanguageType.BACKEND),
                new Answer(Texts.Q2_A3, LanguageType.QA),
                new Answer(Texts.Q2_A4, LanguageType.FRONTEND)
        )));

        questions.add(new Question(Texts.Q3, Arrays.asList(
                new Answer(Texts.Q3_A1, LanguageType.QA),
                new Answer(Texts.Q3_A2, LanguageType.FRONTEND),
                new Answer(Texts.Q3_A3, LanguageType.FRONTEND),
                new Answer(Texts.Q3_A4, LanguageType.BACKEND)
        )));

        questions.add(new Question(Texts.Q4, Arrays.asList(
                new Answer(Texts.Q4_A1, LanguageType.FRONTEND),
                new Answer(Texts.Q4_A2, LanguageType.FRONTEND),
                new Answer(Texts.Q4_A3, LanguageType.QA),
                new Answer(Texts.Q4_A4, LanguageType.BACKEND)
        )));

        questions.add(new Question(Texts.Q5, Arrays.asList(
                new Answer(Texts.Q5_A1, LanguageType.FRONTEND),
                new Answer(Texts.Q5_A2, LanguageType.QA),
                new Answer(Texts.Q5_A3, LanguageType.FRONTEND),
                new Answer(Texts.Q5_A4, LanguageType.QA)
        )));
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Integer getBackendPoint() {
        return backendPoint;
    }

    public void setBackendPoint(Integer backendPoint) {
        this.backendPoint = backendPoint;
    }

    public Integer getFrontendPoint() {
        return frontendPoint;
    }

    public void setFrontendPoint(Integer frontendPoint) {
        this.frontendPoint = frontendPoint;
    }

    public Integer getQaPoint() {
        return qaPoint;
    }

    public void setQaPoint(Integer qaPoint) {
        this.qaPoint = qaPoint;
    }
}
