package com.example.budgetmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivty extends AppCompatActivity {
    Boolean session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activty);
        ImageView imageView = findViewById( R.id.imagelogo );
        Animation animation = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.fade );
        imageView.startAnimation( animation );

        Thread timer = new Thread(  ) {
            @Override
            public void run() {
                try {
                    sleep( 5000 );
                    SESSION();

                    super.run();



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.start();
    }

    public void SESSION(){
        //default value false
        session = Boolean.valueOf(Save.read(getApplicationContext(),"session","false"));
        if (!session){
            //when user first or logout
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class );
            startActivity( intent );
            finish();


        }
        else{
            //when user loged in
            //here value true
            //how the value can change true

            Intent intent = new Intent(SplashActivty.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}