package com.example.pavol.footballquiz2;

/**
 * Created by pavol on 20. 12. 2017.
 */

public class ScoreRecord {
    private String name;
    private Long time;
    private Float percentage;
    private Integer totalQuestions;
    private Integer correctAnswers;


    public ScoreRecord(String name, Long time, Float percentage, Integer totalQuestions, Integer correctAnswers) {
        this.name = name;
        this.time = time;
        this.percentage = percentage;
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Integer getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Integer correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

}
