package nyc.c4q.hw7simongame;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Button buttonRed0;
    private Button buttonGreen2;
    private Button buttonBlue1;
    private Button buttonYellow3;
    private Button buttonStart;
    private int timeWait = 1;
    private Score score;
    Animation animation = new AlphaAnimation(1f, 0f);
    private TextView rounds;
    private static final String KEY_SCORE = "";
    private static final String KEY_ArrayList = "";
    private static final String KEY_TEMP_ArrayList = "";


    Queue<Integer> queue = new LinkedList<>();
    ArrayList<Integer> deletedNum = new ArrayList<>();
    ArrayList<Integer> temp = new ArrayList<>();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int y = score.getValue();
        outState.putInt (KEY_SCORE, y);
        outState.putIntegerArrayList(KEY_ArrayList, deletedNum);
        temp.addAll(queue);
        outState.putIntegerArrayList(KEY_TEMP_ArrayList, temp);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        buttonBlue1 = findViewById(R.id.blue);
        buttonBlue1.setBackgroundResource(R.drawable.blue);

        buttonRed0 = findViewById(R.id.red);
        buttonRed0.setBackgroundResource(R.drawable.image);

        buttonGreen2 = findViewById(R.id.green);
        buttonGreen2.setBackgroundResource(R.drawable.green);

        buttonYellow3 = findViewById(R.id.yellow);
        buttonYellow3.setBackgroundResource(R.drawable.yellow);

        buttonStart = findViewById(R.id.start);
        rounds = findViewById(R.id.rounds);

        animation.setDuration(80);

        score = new Score(0);

        if (savedInstanceState != null) {
            int savedScore = savedInstanceState.getInt(KEY_SCORE);
            score.setValue(savedScore);
            rounds.setText("SCORE" + savedScore);
            deletedNum = savedInstanceState.getIntegerArrayList(KEY_ArrayList);
            temp = savedInstanceState.getIntegerArrayList(KEY_TEMP_ArrayList);
            queue.addAll(temp);
        }

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue.clear();
                deletedNum.clear();
                randomNumber();

            }
        });

        buttonRed0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = addToArrayList(buttonRed0);
                if (number != 0) {
                    Toast.makeText(getApplicationContext(), "you lost!", Toast.LENGTH_LONG).show();
                } else if (queue.size() == 0) {

                    startNewRound();
                }
            }
        });

        buttonBlue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = addToArrayList(buttonBlue1);
                if (number != 1) {
                    Toast.makeText(getApplicationContext(), "you lost!", Toast.LENGTH_LONG).show();
                } else if (queue.size() == 0) {
                    startNewRound();
                }
            }
        });

        buttonGreen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = addToArrayList(buttonGreen2);
                if (number != 2) {
                    Toast.makeText(getApplicationContext(), "you lost!", Toast.LENGTH_LONG).show();
                } else if (queue.size() == 0) {
                    startNewRound();

                }
            }
        });

        buttonYellow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = addToArrayList(buttonYellow3);
                if (number != 3) {
                    Toast.makeText(getApplicationContext(), "you lost!", Toast.LENGTH_LONG).show();
                } else if (queue.size() == 0) {
                    startNewRound();

                }
            }
        });
    }

    public void randomNumber() {
        Random r = new Random();
        int n = r.nextInt(4);
        queue.add(n);
        for (int i : queue) {
            if (i == 0) {
                timeWait++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        buttonRed0.startAnimation(animation);
                    }
                }, 500 * timeWait);
            } else if (i == 1) {
                timeWait++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        buttonBlue1.startAnimation(animation);
                    }
                }, 500 * timeWait);
            } else if (i == 2) {
                timeWait++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        buttonGreen2.startAnimation(animation);
                    }
                }, 500 * timeWait);
            } else if (i == 3) {
                timeWait++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        buttonYellow3.startAnimation(animation);
                    }
                }, 500 * timeWait);
            }
        }
        timeWait =1;

    }

    public void startNewRound() {
        int x = score.getValue()+1;
        score.setValue(x);
        rounds.setText("SCORE" + x);
        queue.addAll(deletedNum);
        deletedNum.clear();
        randomNumber();
    }

    public int addToArrayList(Button button) {
        button.startAnimation(animation);
        int number = queue.poll();
        deletedNum.add(number);
        return number;
    }
}
