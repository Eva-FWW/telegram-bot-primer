package org.campus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TGUser {
    private Long userId;
    private List<Question> questions = new ArrayList<>();

    private Integer scalperPoint = 0;
    private Integer investorPoint = 0;
    private Integer maxinatorPoint = 0;

    public TGUser(Long userId) {
        this.userId = userId;
        fillQuestions();
    }

    private void fillQuestions(){
        questions.add(new Question(Texts.Q1, Arrays.asList(
                new Answer(Texts.Q1_A1, LanguageType.SCALPER)
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

    public Integer getScalperPoint() {
        return scalperPoint;
    }

    public void setScalperPoint(Integer scalperPoint) {
        this.scalperPoint = scalperPoint;
    }

    public Integer getInvestorPoint() {
        return investorPoint;
    }

    public void setInvestorPoint(Integer investorPoint) {
        this.investorPoint = investorPoint;
    }

    public Integer getMaxinatorPoint() {
        return maxinatorPoint;
    }

    public void setMaxinatorPoint(Integer maxinatorPoint) {
        this.maxinatorPoint = maxinatorPoint;
    }
}
