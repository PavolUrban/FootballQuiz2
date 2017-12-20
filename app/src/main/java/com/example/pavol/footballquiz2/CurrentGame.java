package com.example.pavol.footballquiz2;

/**
 * Created by pavol on 20. 12. 2017.
 */

public class CurrentGame {

    private Integer currentQuestion;
    private Integer currentScore;
    private Integer AllQuestionsCount;
    private Long ellapsedSeconds;


    public CurrentGame(Integer currentQuestion, Integer currentScore, Integer allQuestionsCount, Long ellapsedSeconds) {
        this.currentQuestion = currentQuestion;
        this.currentScore = currentScore;
        AllQuestionsCount = allQuestionsCount;
        this.ellapsedSeconds = ellapsedSeconds;
    }

    public Integer getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Integer currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
    }

    public Integer getAllQuestionsCount() {
        return AllQuestionsCount;
    }

    public void setAllQuestionsCount(Integer allQuestionsCount) {
        AllQuestionsCount = allQuestionsCount;
    }

    public Long getEllapsedSeconds() {
        return ellapsedSeconds;
    }

    public void setEllapsedSeconds(Long ellapsedSeconds) {
        this.ellapsedSeconds = ellapsedSeconds;
    }

}
