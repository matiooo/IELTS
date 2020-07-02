package ir.hhadanooo.ielts.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import ir.hhadanooo.ielts.CustomView.CustomViewItem;
import ir.hhadanooo.ielts.R;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Pointer;
import tourguide.tourguide.ToolTip;
import tourguide.tourguide.TourGuide;



public class SlideFragmentTestReading extends Fragment {

    View rootview;
    int Width;
    int Height;
    String intent;

    RelativeLayout rel_text_and_question;
    TextView tv_timer;

    ImageView img_timer,img_see_answer;
    int num;

    long time;
    int num_tab;

    WebView webView;
    @SuppressLint("StaticFieldLeak")
    public static TourGuide mtg;
    SharedPreferences showHelppp1;
    boolean showHelp = false;
    public static boolean isShow = false;


    String TextAnswer1,TextAnswer2,TextAnswer3,Filename1,Filename2,Filename3;


    public static SlideFragmentTestReading newSlide(int Width,int Height,String intent,int num,int num_tab,String TextAnswer1,String TextAnswer2,String TextAnswer3,String filename1,String filename2,String filename3){
        SlideFragmentTestReading fragment = new SlideFragmentTestReading();
        Bundle args = new Bundle();
        args.putInt("Width",Width);
        args.putInt("Height",Height);
        args.putString("Intent",intent);
        args.putInt("num",num);
        args.putInt("num_tab",num_tab);


        args.putString("TextAnswer1",TextAnswer1);
        args.putString("TextAnswer2",TextAnswer2);
        args.putString("TextAnswer3",TextAnswer3);

        args.putString("filename1",filename1);
        args.putString("filename2",filename2);
        args.putString("filename3",filename3);


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args == null) return;
        Width = args.getInt("Width");
        Height = args.getInt("Height");
        intent = args.getString("Intent");
        num  = args.getInt("num");
        num_tab = args.getInt("num_tab");

        showHelppp1 = Objects.requireNonNull(getContext()).getSharedPreferences("show" , getContext().MODE_PRIVATE);
        showHelp = showHelppp1.getBoolean("helper" , false);
        TextAnswer1 = args.getString("TextAnswer1");
        TextAnswer2 = args.getString("TextAnswer2");
        TextAnswer3 = args.getString("TextAnswer3");

        Filename1 = args.getString("filename1");
        Filename2 = args.getString("filename2");
        Filename3 = args.getString("filename3");



    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.layout_fragment_tabs_test_reading, container, false);

        init();
        SetPropertiesRelBody();


        CheckIntnet();

        time = 3600000;
        Timer(tv_timer);
        

        return rootview;
    }

    public void CheckIntnet()
    {
        if(intent.contains("Academic"))
        {
            if(num != 0)
            {
                if(num_tab == 1)
                {

                    //tab 1 academic
                    File file_html = new File(getContext().getFilesDir().getAbsolutePath() + "/ielts/reading/test/academic/" + Filename1+ "/passage1.html");
                    webView.loadUrl("file:///" + file_html);

                    if (!showHelp){
                        isShow =true;
                        mtg = TourGuide.init(Objects.requireNonNull(getActivity())).with(TourGuide.Technique.HORIZONTAL_LEFT);
                        mtg.setPointer(new Pointer())
                                .setToolTip( new ToolTip()
                                        .setDescription("... to MrBool website!!")
                                        .setBackgroundColor(Color.parseColor("#bcd9f9"))
                                        .setShadow(true).setGravity(Gravity.TOP ))
                                .setOverlay(new Overlay()) ;
                        mtg.playOn(rel_text_and_question) ;
                        showHelppp1.edit().putBoolean("helper" , true).apply();
                        showHelp = true;
                    }


              /*   new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         mtg.cleanUp();
                     }
                 } , 3000);*/
                }else if(num_tab == 2)
                {
                    File file_html = new File(getContext().getFilesDir().getAbsolutePath() + "/ielts/reading/test/academic/" + Filename2 + "/passage2.html");
                    webView.loadUrl("file:///" + file_html);
                    //tab 2 academic

                }else if(num_tab == 3) {
                    //tab 3 academic
                    File file_html = new File(getContext().getFilesDir().getAbsolutePath() + "/ielts/reading/test/academic/"+Filename3 + "/passage3.html");
                    webView.loadUrl("file:///" + file_html);
                }

            }
        }else if(intent.contains("General"))
        {
            if(num != 0)
            {
                if(num_tab == 1)
                {

                    //tab 1 general
                    File file_html = new File(getContext().getFilesDir().getAbsolutePath() + "/ielts/reading/test/general/" + Filename1 + "/passage1.html");
                    webView.loadUrl("file:///" + file_html);

                }else if(num_tab == 2)
                {
                    //tab 2 general
                    File file_html = new File(getContext().getFilesDir().getAbsolutePath() + "/ielts/reading/test/general/" + Filename2 + "/passage2.html");
                    webView.loadUrl("file:///" + file_html);


                }else if(num_tab == 3)
                {
                    //tab 3 general
                    File file_html = new File(getContext().getFilesDir().getAbsolutePath() + "/ielts/reading/test/academic/" + Filename3 + "/passage3.html");
                    webView.loadUrl("file:///" + file_html);
                }
            }
        }
    }




    public void init()
    {
        webView = rootview.findViewById(R.id.layout_fragment_webview);
        webView.getSettings().setJavaScriptEnabled(true);


        rel_text_and_question = rootview.findViewById(R.id.layout_fragment_rel_text_question);

        img_see_answer = rootview.findViewById(R.id.layout_ftagment_img_see_answer);
        img_timer = rootview.findViewById(R.id.layout_fragment_img_timer);

        tv_timer = rootview.findViewById(R.id.layout_fragment_tv_timer);


        rel_text_and_question.getLayoutParams().width = (int) (Width*.90);
        rel_text_and_question.getLayoutParams().height = (int)(Height*0.61);




        img_timer.getLayoutParams().width = (int) (Width*0.1);
        img_timer.getLayoutParams().height = (int) (Width*0.1);

        img_see_answer.getLayoutParams().width = (int) (Width*0.295);
        img_see_answer.getLayoutParams().height = (int) (Height*0.045);



    }
    public void SetPropertiesRelBody() {
        rel_text_and_question.getLayoutParams().width = (int) (Width*.90);
        rel_text_and_question.getLayoutParams().height = (int)(Height*0.61);
        img_timer.getLayoutParams().width = (int) (Width*0.1);
        img_timer.getLayoutParams().height = (int) (Width*0.1);

        img_see_answer.setBackground(getContext().getDrawable(R.drawable.seeanswer_icon1));

        img_see_answer.getLayoutParams().width = (int) (Width*0.295);
        img_see_answer.getLayoutParams().height = (int) (Width*0.085);

        CustomViewItem.progressDialog.dismiss();

        img_see_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num_tab == 1)
                {

                    Dialog dia = new Dialog(getContext());
                    dia.setContentView(R.layout.layout_dialog_audioscripts);
                    TextView tv_audioScripts = dia.findViewById(R.id.tv_audioScripts);

                    tv_audioScripts.getLayoutParams().width = (int) (Width*.8);
                    //tv_audioScripts.getLayoutParams().height = (int) (Height);
                    tv_audioScripts.setTextSize((int) (Width*.015));
                    tv_audioScripts.setText(TextAnswer1);
                    dia.show();

                }else if(num_tab == 2)
                {
                    Dialog dia = new Dialog(getContext());
                    dia.setContentView(R.layout.layout_dialog_audioscripts);
                    TextView tv_audioScripts = dia.findViewById(R.id.tv_audioScripts);

                    tv_audioScripts.getLayoutParams().width = (int) (Width*.8);
                    //tv_audioScripts.getLayoutParams().height = (int) (Height);
                    tv_audioScripts.setTextSize((int) (Width*.015));
                    tv_audioScripts.setText(TextAnswer2);
                    dia.show();
                }else if(num_tab == 3)
                {
                    Dialog dia = new Dialog(getContext());
                    dia.setContentView(R.layout.layout_dialog_audioscripts);
                    TextView tv_audioScripts = dia.findViewById(R.id.tv_audioScripts);

                    tv_audioScripts.getLayoutParams().width = (int) (Width*.8);
                    //tv_audioScripts.getLayoutParams().height = (int) (Height);
                    tv_audioScripts.setTextSize((int) (Width*.015));
                    tv_audioScripts.setText(TextAnswer3);
                    dia.show();
                }
            }
        });



    }

    public void Timer(final TextView tv_timer)
    {

        new CountDownTimer(time,1000) {
            @Override
            public void onTick(long l) {
                time = l;
                int minute =(int) time / 60000;
                int second = (int) time % 60000 / 1000;
                String timestring = "" + minute;
                timestring += ":";
                if(second < 10) timestring += "0";
                timestring += second;
                tv_timer.setText(timestring);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }


}
