package com.example.shrikanth.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationSet;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button click;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        view = (View)findViewById(R.id.view);
        click = (Button)findViewById(R.id.button);
        click.setOnClickListener(clickListener);
        setSupportActionBar(toolbar);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ease();      
        }
    };

    private void ease() {
        Easing easing = new Easing(1500);
        AnimatorSet animatorSet = new AnimatorSet();
        float fromX = view.getLeft();
        float fromY = view.getTop();
        float toX = 300;
        float toY = 300;
        ValueAnimator valueAnimatorX = ValueAnimator.ofFloat(fromX,toX, fromX);
        ValueAnimator valueAnimatorY = ValueAnimator.ofFloat(fromY,toY, fromY);

        valueAnimatorX.setEvaluator(easing);
        valueAnimatorY.setEvaluator(easing);

        valueAnimatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                /**/
                view.setTranslationX((float) animation.getAnimatedValue());
            }
        });

        valueAnimatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationY((float) animation.getAnimatedValue());
            }
        });

        animatorSet.playTogether(valueAnimatorX,valueAnimatorY);
        animatorSet.setDuration(1500);
        animatorSet.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
