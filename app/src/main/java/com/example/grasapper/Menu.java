package com.example.grasapper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        button = (Button) findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openGra();
            }
        });
    }

    public void openGra(){
        Intent intent = new Intent(this, Gra.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
