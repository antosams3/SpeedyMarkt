package it.appaccademy.speedymarkt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Carrello extends AppCompatActivity {
    String email;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrello);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("email");
        }
    }


}
