package com.example.moragame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import

import com.example.moragame.game.Computer;
import com.example.moragame.game.Mora;
import com.example.moragame.game.Player;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton scissorsIbn;
    private ImageButton rockIbn;
    private ImageButton paperIbn;
    private Button startBtn;
    private Button quitBtn;
    private Player player;
    private Computer computer;
    private ImageView computerImg;

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
        computer = new Computer();

        computer.AI();
        computerImg.setImageResource(Mora.getMoraResId(computer.getMora()));
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
                Log.d(TAG,getResources().getString(R.string.scissors));
                break;
            case R.id.rock_ibn:
                Log.d(TAG,getResources().getString(R.string.rock));
                break;
            case R.id.paper_ibn:
                Log.d(TAG,getResources().getString(R.string.paper));
                break;
            case R.id.start_btn:
                initGame();
                Log.d(TAG,getResources().getString(R.string.start));
                break;
            case R.id.quit_btn:
                Log.d(TAG,getResources().getString(R.string.quit));
                break;
        }
    }
}