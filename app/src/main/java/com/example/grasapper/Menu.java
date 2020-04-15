package com.example.grasapper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    private Button button;
    private Button exit_button;
    private Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        button = (Button) findViewById(R.id.back);
        exit_button = (Button) findViewById(R.id.exit);
        back_button = (Button) findViewById(R.id.back_game);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openGra();
            }
        });
        exit_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                closeApp();
            }
        });
        back_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                back_button.setTextColor(Color.rgb(59, 39, 37));
                openGra();
            }
        });
    }

    public void closeApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void openGra(){
        Intent intent = new Intent(this, Gra.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
