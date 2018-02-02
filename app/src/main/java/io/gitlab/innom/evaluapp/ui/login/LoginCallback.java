package io.gitlab.innom.evaluapp.ui.login;

import io.gitlab.innom.evaluapp.domain.UserType;

public interface LoginCallback {

    void login(UserType userType);

}
