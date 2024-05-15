package com.example.edux.ViewModels.signup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SigninViewModel extends AndroidViewModel {

    private MutableLiveData<AccountDetailsViewModel> details;

    public SigninViewModel(@NonNull Application application) {
        super(application);
    }

    public void setDetails(AccountDetailsViewModel details) {
        this.details.setValue(details);
    }

    public LiveData<AccountDetailsViewModel> getDetails() {
        return details;
    }
 

}
