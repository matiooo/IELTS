package ir.hhadanooo.ielts.Practice.Listen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ir.hhadanooo.ielts.R;
import ir.hhadanooo.ielts.Test.Listen.TestListenActivity;

public class ListenPracticeActivity extends AppCompatActivity {


    DisplayMetrics dm;
    LinearLayout lay_PracticeL , lay_box_playerPL;
    RelativeLayout lay_playerPL;
    ImageView   iv_play_playerPL , iv_next_playerPL , iv_ic_org_playerPL
            , iv_shareAnswer_playerPL , iv_seeAnswer_playerPL , iv_arrowBack_practiceL
            , iv_ic_logoPage_practiceL;
    View view_space_playerPL , view_space_button_see_share;
    EditText et_PracticeL , et_PracticeLResult;
    TextView tv_TitleLogo_practiceL , tv_PathLogo_practiceL , tv_parts_playerPL ,tv_time_playerPL;
    List<String> answerList = new ArrayList<>();
    Handler mHandler = new Handler();
    MediaPlayer mPlayer;
    int duration = 0;
    int min = 0;
    int sec = 0;
    int secPlay = 10;

    boolean nextPart = false;
    int counterPlay = 2;

    int timePart = 10;
    int totalParts = 0;
    boolean lockButton = false;


    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen_practice);
        CheckIntent();
        Objects.requireNonNull(getSupportActionBar()).hide();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        initActionBar();
        init();



        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/bit.mp3");
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        duration = mPlayer.getDuration()/1000;

        int parts = duration%timePart;
        if (parts == 0){
            totalParts = (duration/timePart);
        }
        tv_parts_playerPL.setText("(1/"+totalParts+")");

        iv_play_playerPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((mPlayer.getCurrentPosition()/1000) != (mPlayer.getDuration()/1000)) {

                    iv_next_playerPL.setEnabled(false);
                    iv_play_playerPL.setEnabled(false);

                    if (counterPlay == 2){
                        iv_play_playerPL.setImageResource(R.drawable.play2_icon);
                        iv_play_playerPL.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                iv_next_playerPL.setEnabled(true);
                                iv_play_playerPL.setEnabled(true);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Toast.makeText(ListenPracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                                      //  mHandler.removeCallbacksAndMessages(null);
                                    }
                                });

                            }
                        }  , 11500);
                    }else if (counterPlay == 1){
                        iv_play_playerPL.setImageResource(R.drawable.play1_icon);
                        iv_play_playerPL.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                iv_next_playerPL.setEnabled(true);
                                iv_play_playerPL.setEnabled(true);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Toast.makeText(ListenPracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                                       // mHandler.removeCallbacksAndMessages(null);
                                    }
                                });

                            }
                        }  , 11500);
                    }else if (counterPlay == 0){
                        iv_play_playerPL.setImageResource(R.drawable.play_icon);
                        if ((mPlayer.getCurrentPosition()/1000)+timePart != (mPlayer.getDuration()/1000)){
                            iv_play_playerPL.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    iv_play_playerPL.setImageResource(R.drawable.play3_icon);
                                    iv_next_playerPL.setEnabled(true);
                                    iv_play_playerPL.setEnabled(true);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                           // Toast.makeText(ListenPracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                                           // mHandler.removeCallbacksAndMessages(null);
                                        }
                                    });
                                }
                            }  , 11500);
                        }
                    }




                    mPlayer.start();

                    ListenPracticeActivity.this.runOnUiThread(new Runnable() {

                        @SuppressLint("SetTextI18n")
                        @Override
                        public void run() {
                            if(mPlayer != null){
                                int mCurrentPosition = mPlayer.getCurrentPosition() / 1000;
                                if (duration > mCurrentPosition){
                                    if(mCurrentPosition < 60){
                                        if (mCurrentPosition < 10){
                                            tv_time_playerPL.setText("00:0"+mCurrentPosition);
                                        }else {
                                            tv_time_playerPL.setText("00:"+mCurrentPosition);
                                        }
                                    }else {
                                        sec = (mCurrentPosition%60);
                                        min = (mCurrentPosition-sec)/60;
                                        if (min < 10 ){
                                            if (sec < 10){
                                                tv_time_playerPL.setText("0"+min+":0"+sec);
                                            }else {
                                                tv_time_playerPL.setText("0"+min+":"+sec);
                                            }
                                        }else {
                                            if (sec < 10){
                                                tv_time_playerPL.setText(min+":0"+sec);
                                            }else {
                                                tv_time_playerPL.setText(min+":"+sec);
                                            }
                                        }
                                    }

                                }



                                if ((mPlayer.getCurrentPosition() / 1000) == secPlay ){
                                    mPlayer.pause();

                                    //Log.i("timerP" , ""+counterPlay);

                                    if (nextPart){
                                        nextPart = false;
                                        secPlay = secPlay+timePart;
                                    }else {
                                        if ((mPlayer.getCurrentPosition() / 1000)  > 9){
                                            counterPlay = counterPlay-1;
                                            mPlayer.seekTo(mPlayer.getCurrentPosition()-(timePart*1000));
                                            Log.i("timerP" , timePart+"cc"+(mPlayer.getCurrentPosition() / 1000));
                                        }
                                    }
                                    int currentPart = (mPlayer.getCurrentPosition() / 1000)/timePart;
                                    tv_parts_playerPL.setText("("+(currentPart+1)+"/"+totalParts+")");
                                /*if ((currentPart+1) <= totalParts ){
                                }*/
                                }


                            }
                            Log.i("handeds" , "ha");
                            mHandler.postDelayed(this, 1000);
                        }
                    });


                    if (counterPlay == 0){
                        if ((mPlayer.getCurrentPosition()/1000)+timePart != (mPlayer.getDuration()/1000)){

                            nextPart = true;
                            counterPlay=2;
                            Log.i("timerP" , ""+nextPart);
                        }else {
                            iv_play_playerPL.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mHandler.removeCallbacksAndMessages(null);
                                    mPlayer.stop();
                                    mPlayer.reset();
                                    //Toast.makeText(ListenPracticeActivity.this, "Ended", Toast.LENGTH_SHORT).show();
                                    Log.i("handeds" , "current : "+(mPlayer.getCurrentPosition()/1000)+"\n time : "+(mPlayer.getDuration()/1000));
                                    iv_next_playerPL.setEnabled(false);
                                    iv_play_playerPL.setEnabled(false);
                                }
                            } , 10000);

                        }
                    }

                }else {
                    iv_next_playerPL.setEnabled(false);
                    iv_play_playerPL.setEnabled(false);
                    //Toast.makeText(ListenPracticeActivity.this, "Ended", Toast.LENGTH_SHORT).show();
                }


            }
        });


        iv_next_playerPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((mPlayer.getCurrentPosition()/1000)+timePart != (mPlayer.getDuration()/1000)){
                    iv_play_playerPL.setImageResource(R.drawable.play3_icon);
                    mHandler.removeCallbacksAndMessages(null);
                    counterPlay=2;
                    secPlay = secPlay+timePart;
                    mPlayer.seekTo(mPlayer.getCurrentPosition()+(timePart*1000));
                    int currentPart = (mPlayer.getCurrentPosition() / 1000)/timePart;
                    tv_parts_playerPL.setText("("+(currentPart+1)+"/"+totalParts+")");

                    int mCurrentPosition = mPlayer.getCurrentPosition() / 1000;
                    if (duration > mCurrentPosition){
                        if(mCurrentPosition < 60){
                            if (mCurrentPosition < 10){
                                tv_time_playerPL.setText("00:0"+mCurrentPosition);
                            }else {
                                tv_time_playerPL.setText("00:"+mCurrentPosition);
                            }
                        }else {
                            sec = (mCurrentPosition%60);
                            min = (mCurrentPosition-sec)/60;
                            if (min < 10 ){
                                if (sec < 10){
                                    tv_time_playerPL.setText("0"+min+":0"+sec);
                                }else {
                                    tv_time_playerPL.setText("0"+min+":"+sec);
                                }
                            }else {
                                if (sec < 10){
                                    tv_time_playerPL.setText(min+":0"+sec);
                                }else {
                                    tv_time_playerPL.setText(min+":"+sec);
                                }
                            }
                        }

                    }



                }else {
                    //Toast.makeText(ListenPracticeActivity.this, "Ended", Toast.LENGTH_SHORT).show();
                }


                /*if ((currentPart+1) <= totalParts ){
                }*/
            }
        });



        et_PracticeL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (et_PracticeL.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_SCROLL) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        return true;
                    }
                }
                return false;

            }
        });
        et_PracticeLResult.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (et_PracticeLResult.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_SCROLL) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        return true;
                    }
                }
                return false;

            }
        });


        iv_seeAnswer_playerPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((mPlayer.getCurrentPosition() / 1000) == (mPlayer.getDuration() / 1000) ||
                (mPlayer.getCurrentPosition()/1000)+timePart == (mPlayer.getDuration()/1000)){

                    et_PracticeLResult.setText("hhadanooo hassan ramin matiooo ni al ali reza bis");
                    answerList.clear();
                    String test = et_PracticeL.getText().toString();
                    String[] tests = test.split(" ");
                    String result = et_PracticeLResult.getText().toString();
                    String[] results = result.split(" ");
                    int darsad = (100 / results.length);
                    int ttrue = 0;
                    int d = 0;
                    for (int i = 0; i < results.length; i++) {
                        for (int j = 0; j < tests.length; j++) {
                            if (!checkForRepeatTrueAnswer(tests[j])) {
                                if (tests[j].equals(results[i])) {
                                    ttrue++;

                                    d = d + darsad;
                                    if (ttrue == results.length) {
                                        d = 100;
                                    }
                                    Log.i("tesssst", "" + tests[j]);
                                    answerList.add(tests[j]);
                                }
                            }
                        }
                    }
                    Toast.makeText(ListenPracticeActivity.this, " number of true answer : " + ttrue + "\n number of answer : " + results.length + "\n percentage of true answer : " + d + "%", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ListenPracticeActivity.this, "pls wait for end", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

   /* @Override
    protected void onStop() {
        super.onStop();
        mPlayer.stop();
        mHandler.removeCallbacksAndMessages(null);
        finish();
    }*/

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
        mHandler.removeCallbacksAndMessages(null);
    }*/

    public boolean checkForRepeatTrueAnswer(String answer){
        boolean repeat = false;
        for (int i = 0 ; i < answerList.size() ; i++){
            if (answerList.get(i).equals(answer)){
                repeat = true;
            }
        }
        return repeat;
    }


    public void init(){
        lay_PracticeL = findViewById(R.id.lay_PracticeL);
        lay_box_playerPL = findViewById(R.id.lay_box_playerPL);
        lay_playerPL = findViewById(R.id.lay_playerPL);
        tv_parts_playerPL = findViewById(R.id.tv_parts_playerPL);
        tv_time_playerPL = findViewById(R.id.tv_time_playerPL);
        iv_play_playerPL = findViewById(R.id.iv_play_playerPL);
        iv_next_playerPL = findViewById(R.id.iv_next_playerPL);
        view_space_playerPL = findViewById(R.id.view_space_playerPL);
        iv_ic_org_playerPL = findViewById(R.id.iv_ic_org_playerPL);
        et_PracticeL = findViewById(R.id.et_PracticeL);
        et_PracticeLResult = findViewById(R.id.et_PracticeLResult);
        iv_shareAnswer_playerPL = findViewById(R.id.iv_shareAnswer_playerPL);
        iv_seeAnswer_playerPL = findViewById(R.id.iv_seeAnswer_playerPL);
        view_space_button_see_share = findViewById(R.id.view_space_button_see_share);


        lay_PracticeL.getLayoutParams().width = (int) (dm.widthPixels*.93);


        lay_playerPL.getLayoutParams().width = (int) (dm.widthPixels*.8);
        lay_playerPL.getLayoutParams().height = (int) (dm.widthPixels*.275);


        lay_box_playerPL.getLayoutParams().height = (int) (dm.widthPixels*.23);


        tv_parts_playerPL.setTextSize((int) (dm.widthPixels*.015));


        tv_time_playerPL.setTextSize((int) (dm.widthPixels*.015));

        //iv_repeat_playerPL.getLayoutParams().width = (int) (dm.widthPixels*.18);
        //iv_repeat_playerPL.getLayoutParams().height = (int) (dm.widthPixels*.15);

        //iv_play_playerPL.getLayoutParams().width = (int) (dm.widthPixels*.18);
        iv_play_playerPL.getLayoutParams().height = (int) (dm.widthPixels*.15);
        iv_play_playerPL.setImageResource(R.drawable.play3_icon);

        //iv_next_playerPL.getLayoutParams().width = (int) (dm.widthPixels*.18);
        iv_next_playerPL.getLayoutParams().height = (int) (dm.widthPixels*.15);

        //view_space_playerPL.getLayoutParams().width = (int) (dm.widthPixels*.25);
        view_space_playerPL.getLayoutParams().height = (int) (dm.widthPixels*.16);

        iv_ic_org_playerPL.getLayoutParams().width = (int) (dm.widthPixels*.21);
        iv_ic_org_playerPL.getLayoutParams().height = (int) (dm.widthPixels*.21);

        et_PracticeL.getLayoutParams().width = (int) (dm.widthPixels*.75);
        et_PracticeL.getLayoutParams().height = (int) (dm.widthPixels*.4);
        et_PracticeLResult.getLayoutParams().width = (int) (dm.widthPixels*.75);
        et_PracticeLResult.getLayoutParams().height = (int) (dm.widthPixels*.4);

        iv_shareAnswer_playerPL.getLayoutParams().width = (int) (dm.widthPixels*.25);
        iv_shareAnswer_playerPL.getLayoutParams().height = (int) (dm.widthPixels*.074);

        iv_seeAnswer_playerPL.getLayoutParams().width = (int) (dm.widthPixels*.25);
        iv_seeAnswer_playerPL.getLayoutParams().height = (int) (dm.widthPixels*.074);

        view_space_button_see_share.getLayoutParams().width = (int) (dm.widthPixels*.2);
    }


    public void CheckIntent() {
        if(getIntent().getExtras().getString("Type1") != null)
        {
            if(getIntent().getExtras().getString("Easy") != null)
            {
                if(getIntent().getExtras().getInt("Number") != 0)
                {
                    Toast.makeText(this,"Type1Easy"+getIntent().getExtras().getInt("Number"),Toast.LENGTH_LONG).show();
                }
            }else if(getIntent().getExtras().getString("Normal") != null)
            {
                if(getIntent().getExtras().getInt("Number") != 0)
                {
                    Toast.makeText(this,"Type1Normal"+getIntent().getExtras().getInt("Number"),Toast.LENGTH_LONG).show();
                }
            }else if(getIntent().getExtras().getString("Hard") != null)
            {
                if(getIntent().getExtras().getInt("Number") != 0)
                {
                    Toast.makeText(this,"Type1Hard"+getIntent().getExtras().getInt("Number"),Toast.LENGTH_LONG).show();
                }
            }


        }else if(getIntent().getExtras().getString("Type2") != null)
        {
            if(getIntent().getExtras().getString("Easy") != null)
            {
                if(getIntent().getExtras().getInt("Number") != 0)
                {
                    Toast.makeText(this,"Type2Easy"+getIntent().getExtras().getInt("Number"),Toast.LENGTH_LONG).show();
                }
            }else if(getIntent().getExtras().getString("Normal") != null)
            {
                if(getIntent().getExtras().getInt("Number") != 0)
                {
                    Toast.makeText(this,"Type2Normal"+getIntent().getExtras().getInt("Number"),Toast.LENGTH_LONG).show();
                }
            }else if(getIntent().getExtras().getString("Hard") != null)
            {
                if(getIntent().getExtras().getInt("Number") != 0)
                {
                    Toast.makeText(this,"Type2Hard"+getIntent().getExtras().getInt("Number"),Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    private void initActionBar() {

        iv_arrowBack_practiceL = findViewById(R.id.iv_arrowBack_practiceL);
        iv_ic_logoPage_practiceL = findViewById(R.id.iv_ic_logoPage_practiceL);
        tv_TitleLogo_practiceL = findViewById(R.id.tv_TitleLogo_practiceL);
        tv_PathLogo_practiceL = findViewById(R.id.tv_PathLogo_practiceL);

        iv_arrowBack_practiceL.getLayoutParams().width = (int) (dm.widthPixels*.1);
        iv_arrowBack_practiceL.getLayoutParams().height = (int) (dm.widthPixels*.1);

        iv_ic_logoPage_practiceL.getLayoutParams().width = (int) (dm.widthPixels*.1);
        iv_ic_logoPage_practiceL.getLayoutParams().height = (int) (dm.widthPixels*.1);

        tv_TitleLogo_practiceL.setTextSize((int) (dm.widthPixels*.025));

        tv_PathLogo_practiceL.setTextSize((int) (dm.widthPixels*.012));

        tv_TitleLogo_practiceL.setText("type1");
        tv_PathLogo_practiceL.setText("Listening");

        iv_arrowBack_practiceL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

}
