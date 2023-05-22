package com.example.trenbokids;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class gnome_deadlift extends AppCompatActivity {
    private ImageView gnomeView;
    private TextView textScore;
    private int score = 0;

    private AnimationDrawable deadliftAnimation;
    private int screenWidth;
    private int screenHeight;
    String text;
    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private ValueAnimator colorAnimator;

    private MediaPlayer mediaPlayer;
    TextView descuentoTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gnome_deadlift);
        textScore = findViewById(R.id.textScore2);
        textScore.setVisibility(View.GONE);
        descuentoTextView = findViewById(R.id.descuento);


        gnomeView = findViewById(R.id.gnomeView);

        deadliftAnimation = (AnimationDrawable) gnomeView.getDrawable();
        deadliftAnimation.start();
        Map<String, ImageView> n = new HashMap<>(); //Dynamic ID's hashmap fumada del siglo más abajo
        startColorAnimation();

        for (int i = 1; i <= 6; i++) {


            n.put("gnome" + i, (ImageView) findViewById(getResources().getIdentifier("gnome" + i, "id", getPackageName())));

            float startX =  n.get("gnome" + i).getX();
            float startY =  n.get("gnome" + i).getY();
            float endX = getRandomX( n.get("gnome" + i));
            float endY = getRandomY( n.get("gnome" + i));
            animateGnomeView( n.get("gnome" + i), startX, startY, endX, endY);

            int finalI = i;


            n.get("gnome" + i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    performDeadlift();

                    MediaPlayer mediaPlayer;
                    mediaPlayer = MediaPlayer.create(gnome_deadlift.this, R.raw.gnome_effect);
                    mediaPlayer.start();
                    score++;
                    textScore.setText("+" + score);
                    textScore.setVisibility(View.VISIBLE);
                    descuentoTextView.setText("CODE 20% OFF: "+generateRandomString(6));



                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textScore.setVisibility(View.GONE);


                        }
                    }, 1000);
                }
            });
        }

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading gnome (I'm autistic...");
        progressDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                showAlert();


            }
        }, 3000);
        mediaPlayer = MediaPlayer.create(this, R.raw.gnome_hunting);
        mediaPlayer.setLooping(true);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });
        mediaPlayer.start();


    }
    private void startColorAnimation() {
        int[] rainbowColors = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};

        colorAnimator = ValueAnimator.ofInt(rainbowColors);
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.setDuration(2000); // Adjust the duration as needed
        colorAnimator.setRepeatCount(ValueAnimator.INFINITE);
        colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int color = (int) animator.getAnimatedValue();
                descuentoTextView.setTextColor(color);
            }
        });

        colorAnimator.start();
    }


    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Gnome");
        builder.setMessage("¡Los gnomos están huyendo!\nCapturalos para hacer una REP.\n(Millions wear the hats)");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle the OK button click event
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        if (colorAnimator != null) {
            colorAnimator.cancel();
        }
    }
    private void animateGnomeView(ImageView gnome, float startX, float startY, float endX, float endY) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(gnome, "x", startX, endX);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(gnome, "y", startY, endY);

        animatorX.setDuration(1000);
        animatorY.setDuration(1000);

        animatorX.setInterpolator(new LinearInterpolator());
        animatorY.setInterpolator(new LinearInterpolator());

        animatorX.setRepeatCount(0); // Disable repeat count for individual animations
        animatorY.setRepeatCount(0); // Disable repeat count for individual animations

        animatorX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                float newEndX = getRandomX(gnome); // Get new random X coordinate for the next animation
                float newEndY = getRandomY(gnome); // Get new random Y coordinate for the next animation
                animateGnomeView(gnome, endX, endY, newEndX, newEndY); // Restart the animation with new coordinates
            }
        });

        animatorX.start();
        animatorY.start();
    }

    private float getRandomX(ImageView gnome) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        return (float) (Math.random() * (screenWidth - gnome.getWidth()));
    }

    private float getRandomY(ImageView gnome) {
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        return (float) (Math.random() * (screenHeight - gnome.getHeight()));
    }

    private void performDeadlift() {
        // Start the deadlift animation
        if (deadliftAnimation.isRunning()) {
            deadliftAnimation.stop();
        }

        gnomeView.setImageDrawable(null);
        gnomeView.setImageDrawable(deadliftAnimation);

        // Start the animation

        // Reload the animation frames
        deadliftAnimation.start();
    }
}