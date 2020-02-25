package com.luis.firstcodes.databind;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private MutableLiveData<Integer> score;

    public MutableLiveData<Integer> getScore() {
        if (score == null) {
            score = new MutableLiveData<>();
            score.postValue(0);
        }
        return score;
    }

    public void increase() {
        getScore().postValue(getScore().getValue() + 1);
    }

    public void decrease() {
        getScore().postValue(getScore().getValue() - 1);
    }
}
