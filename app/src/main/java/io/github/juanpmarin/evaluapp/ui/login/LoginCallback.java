package io.github.juanpmarin.evaluapp.ui.login;

import io.github.juanpmarin.evaluapp.domain.UserType;

public interface LoginCallback {

    void login(UserType userType);

}
