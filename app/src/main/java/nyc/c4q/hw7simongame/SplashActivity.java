package nyc.c4q.hw7simongame;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, GameActivity.class);
                startActivity(intent);

            }
        };

        handler.postDelayed(r, 15000);
        Toast.makeText(getApplicationContext(), "Watch the buttons and repeat the same order", Toast.LENGTH_LONG).show();
    }
}
