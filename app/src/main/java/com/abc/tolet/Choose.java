package com.abc.tolet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class Choose extends AppCompatActivity {

    private Button house,banquet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        house=findViewById(R.id.house);
        banquet=findViewById(R.id.banquet);

        house.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Choose.this , House.class);
                startActivity(intent1);
            }
        });

        banquet.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Choose.this , Banquet.class);
                startActivity(intent2);
            }
        });
        }




    }



