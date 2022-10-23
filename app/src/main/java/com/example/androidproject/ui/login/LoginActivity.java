package com.example.androidproject.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.androidproject.R;
import com.example.androidproject.SQLiteDBHelper;
import com.example.androidproject.ui.register.RegisterActivity;
import com.example.androidproject.ui.shops.ShopsActivity;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    TextView register;
    Button login;
    SQLiteDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        register = (TextView) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login_button);
        db = new SQLiteDBHelper(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("My notification","my notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please fill all the fields!", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUserPass = db.checkUsernameAndPassword(user, pass);
                    if (checkUserPass == true) {
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(LoginActivity.this,"My notification")
                                .setSmallIcon(R.drawable.ic_baseline_add_shopping_cart_24)
                                .setContentTitle("Shopify")
                                .setContentText("You have logged in succesfully")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(LoginActivity.this);
                        managerCompat.notify(1,builder.build());
                        Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ShopsActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}