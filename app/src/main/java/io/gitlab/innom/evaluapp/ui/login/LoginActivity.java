package io.gitlab.innom.evaluapp.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.gitlab.innom.evaluapp.R;
import io.gitlab.innom.evaluapp.databinding.ActivityLoginBinding;
import io.gitlab.innom.evaluapp.domain.UserType;
import io.gitlab.innom.evaluapp.ui.MainActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.setCallback(this::launchMainActivity);
    }

    private void launchMainActivity(UserType userType) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}

