package dev.martisv.userbehaviour.app;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dev.martisv.userbehaviour.tracker.UserBehaviourTracker;

public class MainActivity extends AppCompatActivity {

    private UserBehaviourTracker userBehaviourTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        userBehaviourTracker = new UserBehaviourTracker
//                .UserBehaviourTrackerBuilder(this)
//                .build();

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        userBehaviourTracker.getTouchListener().onTouch(null, ev);
//        return super.dispatchTouchEvent(ev);
//    }
}