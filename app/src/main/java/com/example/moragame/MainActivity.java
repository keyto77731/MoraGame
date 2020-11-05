package com.example.moragame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.moragame.game.Computer;
import com.example.moragame.game.Mora;
import com.example.moragame.game.Player;
import com.example.moragame.game.WinState;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,OnActionListener,Runnable{

    private ImageButton scissorsIbn;
    private ImageButton rockIbn;
    private ImageButton paperIbn;
    private Button startBtn;
    private Button quitBtn;
    private Player player;
    private Computer computer;
    private ImageView computerImg;
    private int gameMillisecond;
    private int targetMillisecond;
    private boolean gameCountDownFinish;
    private Handler gameTimer;
    private TextView countText;
    private GameState gameState;

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initGame();
    }

    public void initGame(){
        player = new Player();
        computer = new Computer(this);
        gameState = GameState.INIT_GAME;

        computer.AI();

    }


    public void onAction(GameState state){
        gameState=state;
        switch (state){
            case START_GAME:
                startGame();
                break;
            case COMPUTER_ROUND:
                computer.AI();
                break;
            case PLATER_ROUND:
                startGameCountDown();
                break;
            case CHECK_WIN_STATE:
                WinState winState = WinState.getWinState(
                        player.getMora(),computer.getMora());
                Log.d(TAG,winState.toString());
                onAction(GameState.START_GAME);
                break;
        }
    }

    private void startGameCountDown() {
        computerImg.setImageResource(Mora.getMoraResId(computer.getMora()));
        if(gameTimer != null){
            gameTimer.removeCallbacks(this);
        }
        gameTimer = new Handler(Looper.getMainLooper());
        gameTimer.post(this);


    }

    private void startGame() {
        gameMillisecond = 0;
        targetMillisecond = 1000;
        gameCountDownFinish = false;
        //進行遊戲局數倒數
        onAction(GameState.COMPUTER_ROUND);
    }

    public void findViews(){
        scissorsIbn = findViewById(R.id.scissors_ibn);
        rockIbn = findViewById(R.id.rock_ibn);
        paperIbn = findViewById(R.id.paper_ibn);
        startBtn = findViewById(R.id.start_btn);
        quitBtn = findViewById(R.id.quit_btn);
        computerImg = findViewById(R.id.computer_img);
        scissorsIbn.setOnClickListener(this);
        rockIbn.setOnClickListener(this);
        paperIbn.setOnClickListener(this);
        startBtn.setOnClickListener(this);
        quitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scissors_ibn:
                if(gameState == GameState.PLATER_ROUND){
                player.setMora(Mora.SCISSOR);
                onAction(GameState.CHECK_WIN_STATE);}
                Log.d(TAG,getResources().getString(R.string.scissors));
                break;
            case R.id.rock_ibn:
                player.setMora(Mora.ROCK);
                onAction(GameState.CHECK_WIN_STATE);
                Log.d(TAG,getResources().getString(R.string.rock));
                break;
            case R.id.paper_ibn:
                player.setMora(Mora.PAPER);
                onAction(GameState.CHECK_WIN_STATE);
                Log.d(TAG,getResources().getString(R.string.paper));
                break;
            case R.id.start_btn:
                onAction(GameState.START_GAME);
                initGame();
                Log.d(TAG,getResources().getString(R.string.start));
                break;
            case R.id.quit_btn:
                Log.d(TAG,getResources().getString(R.string.quit));
                break;
        }
    }

    @Override
    public void run() {
        if(gameCountDownFinish){
            return;
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gameMillisecond += 10;
        if(gameMillisecond >= targetMillisecond){
            gameMillisecond = targetMillisecond;
            gameCountDownFinish = true;
            player.setMora(Mora.NONE);
            onAction(GameState.CHECK_WIN_STATE);
        }
        int sec = (targetMillisecond - gameMillisecond) / 1000;
        int ms = (targetMillisecond - gameMillisecond) % 1000;
        String time = String.format("%d:%03d",sec,ms);
        countText.setText(time);
        gameTimer = new Handler(Looper.getMainLooper());
        gameTimer.postDelayed(this,10);
    }
}