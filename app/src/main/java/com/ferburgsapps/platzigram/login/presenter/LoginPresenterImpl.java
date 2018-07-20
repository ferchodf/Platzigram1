package com.ferburgsapps.platzigram.login.presenter;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.ferburgsapps.platzigram.login.interactor.LoginInteractor;
import com.ferburgsapps.platzigram.login.interactor.LoginInteractorImpl;
import com.ferburgsapps.platzigram.login.view.LoginView;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenterImpl implements LoginPresenter{

    private LoginView loginView;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void signIn(String username, String password, Activity activity, FirebaseAuth firebasAuth) {
        loginView.disableInputs();
        loginView.showProgressBar();
        interactor.signIn(username, password, activity, firebasAuth);
    }

    @Override
    public void loginSuccess() {
        loginView.hideProgressBar();
        loginView.goHome();
    }

    @Override
    public void loginUnSuccess(String error) {
        loginView.enableInputs();
        loginView.hideProgressBar();
        loginView.loginError(error);
    }
}
