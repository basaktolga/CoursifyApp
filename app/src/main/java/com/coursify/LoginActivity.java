package com.coursify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.coursify.R;

public class LoginActivity extends AppCompatActivity {
    EditText usernameEditText, passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add some basic validation
                String username = usernameEditText.getText().toString();
                if(username.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter your username", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Start MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}

