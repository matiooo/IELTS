package ir.yottahouse.EnjoyIELTS.Test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;

import ir.yottahouse.EnjoyIELTS.CustomView.CustomViewItem;
import ir.yottahouse.EnjoyIELTS.R;

public class Activity_test extends AppCompatActivity {

    RelativeLayout rel_body,rel_list_item;
    ImageView img_body;
    LinearLayout lin;
    String Type = "";
    int num_type = 0;

    TextView tv1_about_icon,tv2_about_icon;

    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportActionBar().hide();
        init();
        CheckIntent();




    }
    public void init()
    {


        rel_body = findViewById(R.id.activity_test_rel_body);
        rel_list_item = findViewById(R.id.activity_test_rel_list_item);
        img_body = findViewById(R.id.activity_test_img_body);
        lin = findViewById(R.id.activity_test_lin_list_item);

        tv1_about_icon = findViewById(R.id.activity_test_tv1_about_icon);
        tv2_about_icon = findViewById(R.id.activity_test_tv2_about_icon);

        img_back = findViewById(R.id.activity_test_actionbar_img_icon_back);





    }


    public void CheckIntent()
    {
        if(getIntent().getExtras().getString("Listen") != null)
        {
            Type = "Listen";
            num_type = 1;

            SetPropertiesRelBody();
            SetPropertiesCustomView();
        }else if(getIntent().getExtras().getString("Speak") != null)
        {
            Type = "Speak";
            num_type = 2;

            SetPropertiesRelBody();
            SetPropertiesCustomView();
        }else if(getIntent().getExtras().getString("Read") != null)
        {
            Type = "Read";
            num_type = 3;

            SetPropertiesRelBody();
            SetPropertiesCustomView();
        }else if (getIntent().getExtras().getString("Write") != null)
        {
            Type = "Write";
            num_type = 4;
            SetPropertiesRelBody();
            SetPropertiesCustomView();

        }
    }

    public void SetPropertiesRelBody()
    {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);



        if(num_type == 1)
        {
            tv1_about_icon.setText("Test");
            tv2_about_icon.setText("Listening");

            Glide.with(this).load(R.drawable.listening_icon).into(img_body);
            //img_body.setBackground(getResources().getDrawable(R.drawable.test_menue));
        }else if(num_type == 2)
        {
            tv1_about_icon.setText("Test");
            tv2_about_icon.setText("Speaking");
            Glide.with(this).load(R.drawable.speaking_icon).into(img_body);
            //img_body.setBackground(getResources().getDrawable(R.drawable.test_menue));
        }else if(num_type == 3)
        {
            tv1_about_icon.setText("Test");
            tv2_about_icon.setText("Reading");
            Glide.with(this).load(R.drawable.reading_icon).into(img_body);
            //img_body.setBackground(getResources().getDrawable(R.drawable.test_menue));
        }else if(num_type == 4)
        {
            tv1_about_icon.setText("Test");
            tv2_about_icon.setText("Writing");

            Glide.with(this).load(R.drawable.writing_icon).into(img_body);
            //img_body.setBackground(getResources().getDrawable(R.drawable.test_menue));
        }

        img_body.getLayoutParams().width = (int) (dm.widthPixels*.25);
        img_body.getLayoutParams().height = (int) (dm.widthPixels*.25);

        rel_body.getLayoutParams().height = (int)(dm.heightPixels*0.27);
        rel_list_item.getLayoutParams().height = (int) (dm.heightPixels*0.73);

        tv1_about_icon.setTextSize((int) (dm.widthPixels * 0.020));
        tv2_about_icon.setTextSize((int) (dm.widthPixels * 0.0125));

        tv1_about_icon.setTextColor(Color.BLACK);

        img_body.getLayoutParams().width = (int) (dm.widthPixels*0.25);
        img_body.getLayoutParams().height = (int) (dm.widthPixels*0.25);


        img_back.getLayoutParams().width = (int) (dm.widthPixels*.1);
        img_back.getLayoutParams().height = (int) (dm.widthPixels*.1);

        Glide.with(this).load(R.drawable.icon_arrow_back).into(img_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });




    }


    public void SetPropertiesCustomView()
    {







        if(num_type == 1)
        {

            File file = new File(getFilesDir().getAbsolutePath() + "/ielts/listening/test/");

            File[] files = file.listFiles();

            for(int i = 0;i<files.length;i++)
            {
                CustomViewItem custom1 = new CustomViewItem(this,"Listening","Test","",i+1,"","",false,files[i].getName());
                SetSettingCustomItem("",files[i].getName(),custom1,getResources().getDrawable(R.drawable.test_menue));
                custom1.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_list_item));


                lin.addView(custom1);
            }


        }
        if(num_type == 2)
        {


            File file = new File(getFilesDir().getAbsolutePath() + "/ielts/speaking/test/");

            File[] files = file.listFiles();

            for(int i = 0;i<files.length;i++)
            {
                CustomViewItem custom1 = new CustomViewItem(this,"Speaking","Test","",i+1,"","",false,files[i].getName());
                SetSettingCustomItem("",files[i].getName(),custom1,getResources().getDrawable(R.drawable.test_menue));
                custom1.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_list_item));
                lin.addView(custom1);
            }


        }
        if(num_type == 3)
        {


            if(getIntent().getExtras().getString("Academic") != null)
            {

                File file = new File(getFilesDir().getAbsolutePath() + "/ielts/reading/test/academic");


                File[] files = file.listFiles();

                for(int i = 0;i<files.length;i++)
                {
                    CustomViewItem custom1 = new CustomViewItem(this,"Reading","Test","Academic",i+1,"","",false,files[i].getName());
                    SetSettingCustomItem("",files[i].getName(),custom1,getResources().getDrawable(R.drawable.test_menue));
                    custom1.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_list_item));
                    lin.addView(custom1);
                }





            }else if(getIntent().getExtras().getString("General") != null)
            {

                File file = new File(getFilesDir().getAbsolutePath() + "/ielts/reading/test/general");

                File[] files = file.listFiles();

                for(int i = 0;i<files.length;i++)
                {
                    CustomViewItem custom1 = new CustomViewItem(this,"Reading","Test","General",i+1,"","",false,files[i].getName());
                    SetSettingCustomItem("",files[i].getName(),custom1,getResources().getDrawable(R.drawable.test_menue));
                    custom1.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_list_item));
                    lin.addView(custom1);
                }

            }


            //SetSettingCustomItem("","How i can Go to office ...",custom4,getResources().getDrawable(R.drawable.gif_icon),getResources().getDrawable(R.drawable.tip_item_gift_icon));



        }
        if(num_type == 4)
        {


            if(getIntent().getExtras().getString("Academic") != null)
            {

                File file = new File(getFilesDir().getAbsolutePath() + "/ielts/writing/test/academic");

                File[] files = file.listFiles();

                for(int i = 0;i<files.length;i++)
                {

                    CustomViewItem custom1 = new CustomViewItem(this,"Writing","Test","Academic",i+1,files[i].getName(),"",false,files[i].getName());
                    SetSettingCustomItem("",files[i].getName(),custom1,getResources().getDrawable(R.drawable.test_menue));
                    custom1.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_list_item));
                    lin.addView(custom1);
                }



            }else if(getIntent().getExtras().getString("General") != null)
            {

                File file = new File(getFilesDir().getAbsolutePath() + "/ielts/writing/test/general");

                File[] files = file.listFiles();

                for(int i = 0;i<files.length;i++)
                {
                    CustomViewItem custom1 = new CustomViewItem(this,"Writing","Test","General",i+1,files[i].getName(),"",false,files[i].getName());
                    SetSettingCustomItem("",files[i].getName(),custom1,getResources().getDrawable(R.drawable.test_menue));
                    custom1.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_list_item));
                    lin.addView(custom1);
                }


            }

        }


    }

    public void SetSettingCustomItem(String Body, String title, CustomViewItem custom, Drawable icon1)
    {
        //init all view custom view
        TextView tv_title = custom.getTv_title();
        TextView tv_body = custom.getTv_body();
        ImageView img_icon = custom.getImg_icon();
        ImageView img_icon1 = custom.getImg_icon1();

        RelativeLayout relativeLayout = custom.getrel();


        //Give value to view
        custom.SetTextTvTitle(title);
        Glide.with(this).load(R.drawable.arrowmore).into(img_icon);
        Glide.with(this).load(icon1).into(img_icon1);
        //custom.SetIcon(getResources().getDrawable(R.drawable.arrowmore));
        //img_icon1.setBackground(icon1);


        // set width and height icon custom view
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        img_icon.getLayoutParams().width = (int)(dm.widthPixels*0.10);
        img_icon.getLayoutParams().height = (int)(dm.widthPixels*0.10);

        img_icon1.getLayoutParams().width = (int)(dm.widthPixels*0.10);
        img_icon1.getLayoutParams().height = (int)(dm.widthPixels*0.10);


        //set width and height layout custom view
        relativeLayout.getLayoutParams().width = (int)(dm.widthPixels*0.95);


        //set maxwidth tv body
        tv_body.setMaxWidth((int) (dm.widthPixels * 0.65));

        tv_body.setTextColor(Color.BLACK);
        tv_title.setTextColor(Color.BLACK);



        //set margin
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, (int) (dm.widthPixels * 0.04), 0, 0);
        custom.setLayoutParams(params);


        //set text size tv body,tv title
        tv_body.setTextSize((int) (dm.widthPixels * 0.010));
        tv_title.setTextSize((int) (dm.widthPixels * 0.013));

        //set center veritcal tv title

        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params1.setMargins(0, (int) (dm.widthPixels * 0.04), 0, 0);
        tv_title.setLayoutParams(params1);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tv_title.getLayoutParams();
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        tv_title.setLayoutParams(lp);
    }

    public void SetSettingCustomItem(String Body, String title, CustomViewItem custom, Drawable icon,Drawable icon1)
    {
        //init all view custom view
        TextView tv_title = custom.getTv_title();
        TextView tv_body = custom.getTv_body();
        ImageView img_icon = custom.getImg_icon();
        ImageView img_icon1 = custom.getImg_icon1();

        RelativeLayout relativeLayout = custom.getrel();


        //Give value to view
        custom.SetTextTvTitle(title);
        custom.SetIcon(getResources().getDrawable(R.drawable.arrowmore));
        img_icon1.setBackground(icon1);
        img_icon.setBackground(icon);


        // set width and height icon custom view
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        img_icon.getLayoutParams().width = (int)(dm.widthPixels*0.10);
        img_icon.getLayoutParams().height = (int)(dm.widthPixels*0.10);

        img_icon1.getLayoutParams().width = (int)(dm.widthPixels*0.10);
        img_icon1.getLayoutParams().height = (int)(dm.widthPixels*0.10);


        //set width and height layout custom view
        relativeLayout.getLayoutParams().width = (int)(dm.widthPixels*0.95);


        //set maxwidth tv body
        tv_body.setMaxWidth((int) (dm.widthPixels * 0.65));

        tv_body.setTextColor(Color.BLACK);
        tv_title.setTextColor(Color.BLACK);



        //set margin
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, (int) (dm.widthPixels * 0.04), 0, 0);
        custom.setLayoutParams(params);


        //set text size tv body,tv title
        tv_body.setTextSize((int) (dm.widthPixels * 0.010));
        tv_title.setTextSize((int) (dm.widthPixels * 0.013));

        //set center veritcal tv title

        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params1.setMargins(0, (int) (dm.widthPixels * 0.04), 0, 0);
        tv_title.setLayoutParams(params1);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tv_title.getLayoutParams();
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        tv_title.setLayoutParams(lp);
    }
}
