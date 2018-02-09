package io.github.juanpmarin.evaluapp.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.databinding.ActivityLoginBinding;
import io.github.juanpmarin.evaluapp.domain.UserType;
import io.github.juanpmarin.evaluapp.ui.MainActivity;

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

