package io.github.juanpmarin.evaluapp.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Integer> currentNavigationItem;

    public MainViewModel() {
        this.currentNavigationItem = new MutableLiveData<>();
    }

    MutableLiveData<Integer> getCurrentNavigationItem() {
        return currentNavigationItem;
    }

    void setCurrentNavigationItem(Integer currentNavigationItem) {
        this.currentNavigationItem.setValue(currentNavigationItem);
    }

}
