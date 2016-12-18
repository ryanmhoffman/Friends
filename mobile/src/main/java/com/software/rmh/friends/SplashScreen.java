package com.software.rmh.friends;

import android.animation.Animator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

	ImageView imageViewLogo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash_screen);

		imageViewLogo = (ImageView) findViewById(R.id.logo);
		imageViewLogo.setScaleX(0);
		imageViewLogo.setScaleY(0);

		initSplashScreen();
	}

	private void initSplashScreen() {
		// Animate the logo on to the screen for a short time as an intro to the app.
		imageViewLogo.animate().scaleX(1).scaleY(1)
				.setDuration(500).setInterpolator(new OvershootInterpolator())
				.setStartDelay(200).setListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animator) {
				// Do nothing
			}

			@Override
			public void onAnimationEnd(Animator animator) {
				// Once the animation ends wait for 1 second before launching the main activity.
				TimerTask timerTask = new TimerTask() {
					@Override
					public void run() {
						Intent i = new Intent(SplashScreen.this, MainActivity.class);
						startActivity(i);
						finish();
					}
				};
				new Timer().schedule(timerTask, 1000);
			}

			@Override
			public void onAnimationCancel(Animator animator) {
				// Do nothing
			}

			@Override
			public void onAnimationRepeat(Animator animator) {
				// Do nothing
			}
		}).start();
	}
}
