package com.fatihb.catchharry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;
    int number;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText=findViewById(R.id.time);
        scoreText=findViewById(R.id.score);
        imageView1=findViewById(R.id.im1);
        imageView2=findViewById(R.id.im2);
        imageView3=findViewById(R.id.im3);
        imageView4=findViewById(R.id.im4);
        imageView5=findViewById(R.id.im5);
        imageView6=findViewById(R.id.im6);
        imageView7=findViewById(R.id.im7);
        imageView8=findViewById(R.id.im8);
        imageView9=findViewById(R.id.im9);
        imageArray=new ImageView[]{imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};
        hideImages();
        number=0;
        new CountDownTimer(20000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time:"+millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {
                timeText.setText("GAME FİNİSH!!");
                handler.removeCallbacks(runnable);
                for (ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("Do you want to play again??");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Gamer OVER!!",Toast.LENGTH_LONG).show();
                    }
                });

                alert.show();
            }
        }.start();
    }

    public void increase(View view){
        number++;
        scoreText.setText("Score:"+number);
    }

    public void hideImages(){
        handler=new Handler();

        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random= new Random();
                int rand = random.nextInt(9);
                imageArray[rand].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }
}
