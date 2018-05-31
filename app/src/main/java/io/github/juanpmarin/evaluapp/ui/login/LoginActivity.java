package io.github.juanpmarin.evaluapp.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.EditText;

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
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.USER_TYPE, userType.ordinal());

        if (userType == UserType.STUDENT) {
            EditText identification = new EditText(this);
            identification.setInputType(InputType.TYPE_CLASS_NUMBER);

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.what_is_your_id)
                    .setView(identification)
                    .setPositiveButton(R.string.enter, (dialog1, which) -> {
                        String userId = String.valueOf(identification.getText());
                        intent.putExtra(MainActivity.USER_ID, userId);

                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton(android.R.string.cancel, null)
                    .create();

            dialog.show();
        } else {
            startActivity(intent);
            finish();
        }
    }

}

