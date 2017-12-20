package com.example.pavol.footballquiz2;

/**
 * Created by pavol on 20. 12. 2017.
 */

public class ConfigurationModel {
    private Boolean soundsOn;
    private Integer questionCount;

    public ConfigurationModel(Boolean soundsOn, Integer questionCount) {
        this.soundsOn = soundsOn;
        this.questionCount = questionCount;
    }

    public Boolean getSoundsOn() {
        return soundsOn;
    }

    public void setSoundsOn(Boolean soundsOn) {
        this.soundsOn = soundsOn;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

}
