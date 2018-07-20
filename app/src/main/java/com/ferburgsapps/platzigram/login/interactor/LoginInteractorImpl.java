package com.ferburgsapps.platzigram.login.interactor;

import android.app.Activity;

import com.ferburgsapps.platzigram.login.presenter.LoginPresenter;
import com.ferburgsapps.platzigram.login.repository.LoginRepository;
import com.ferburgsapps.platzigram.login.repository.LoginRepositoryImpl;
import com.google.firebase.auth.FirebaseAuth;

public class LoginInteractorImpl implements LoginInteractor {

    private LoginPresenter presenter;
    private LoginRepository repository;

    public LoginInteractorImpl(LoginPresenter presenter){
        this.presenter = presenter;
        repository = new LoginRepositoryImpl(presenter);
    }

    @Override
    public void signIn(String username, String password, Activity activity, FirebaseAuth firebasAuth) {
        repository.signIn(username, password, activity, firebasAuth);
    }
}
