package ir.yottahouse.EnjoyIELTS.DialagENH;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Objects;

import ir.yottahouse.EnjoyIELTS.CustomView.CustomViewItem;
import ir.yottahouse.EnjoyIELTS.Practice.Activity_Practice;
import ir.yottahouse.EnjoyIELTS.Practice.Activity_practice_read;
import ir.yottahouse.EnjoyIELTS.R;

public class DialogENH {


    Context context;
    DisplayMetrics dm;
    Dialog dialog;
    ImageView iv_e_dialogENH , iv_n_dialogENH , iv_h_dialogENH;
    TextView tv_title_dialogENH;


    public DialogENH(final Context context, DisplayMetrics dm , final String intent){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_dialog_e_n_h);
        Objects.requireNonNull(dialog.getWindow()).setLayout((int) (dm.widthPixels*.8)
                , ViewGroup.LayoutParams.WRAP_CONTENT );
        tv_title_dialogENH = dialog.findViewById(R.id.tv_title_dialogENH);
        iv_e_dialogENH = dialog.findViewById(R.id.iv_e_dialogENH);
        iv_n_dialogENH = dialog.findViewById(R.id.iv_n_dialogENH);
        iv_h_dialogENH = dialog.findViewById(R.id.iv_h_dialogENH);

        Glide.with(context).load(R.drawable.btn_easy1).into(iv_e_dialogENH);
        Glide.with(context).load(R.drawable.btn_normal1).into(iv_n_dialogENH);
        Glide.with(context).load(R.drawable.btn_hard1).into(iv_h_dialogENH);

        tv_title_dialogENH.setTextSize((int) (dm.widthPixels*0.02));

        iv_e_dialogENH.getLayoutParams().width = (int) (dm.widthPixels*0.2);
        iv_e_dialogENH.getLayoutParams().height = (int) (dm.widthPixels*0.07);

        iv_n_dialogENH.getLayoutParams().width = (int) (dm.widthPixels*0.2);
        iv_n_dialogENH.getLayoutParams().height = (int) (dm.widthPixels*0.07);

        iv_h_dialogENH.getLayoutParams().width = (int) (dm.widthPixels*0.2);
        iv_h_dialogENH.getLayoutParams().height = (int) (dm.widthPixels*0.07);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        iv_e_dialogENH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.equals("Type1")){
                    Intent i = new Intent(context, Activity_Practice.class);
                    i.putExtra("Easy","easy");
                    i.putExtra("Type1","type1");
                    i.putExtra("Listen","listen");
                    dialog.dismiss();

                    context.startActivity(i);
                }else if (intent.equals("Type2")){
                    Intent i = new Intent(context, Activity_Practice.class);
                    i.putExtra("Easy","easy");
                    i.putExtra("Type2","type2");
                    i.putExtra("Listen","listen");
                    dialog.dismiss();
                    context.startActivity(i);
                }else if (intent.equals("ReadPractice"))
                {
                    Intent intent = new Intent(context, Activity_practice_read.class);
                    intent.putExtra("Easy","Easy");
                    dismiss();
                    context.startActivity(intent);
                    CustomViewItem.progressDialog = new ProgressDialog(context);
                    CustomViewItem.progressDialog.setMessage("     Loading Page...     ");
                    CustomViewItem.progressDialog.show();

                }
            }
        });

        iv_n_dialogENH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.equals("Type1")){
                    Intent i = new Intent(context, Activity_Practice.class);
                    i.putExtra("Normal","normal");
                    i.putExtra("Type1","type1");
                    i.putExtra("Listen","listen");
                    dialog.dismiss();
                    context.startActivity(i);
                }else if (intent.equals("Type2")){
                    Intent i = new Intent(context, Activity_Practice.class);
                    i.putExtra("Normal","normal");
                    i.putExtra("Type2","type2");
                    i.putExtra("Listen","listen");
                    dialog.dismiss();
                    context.startActivity(i);
                }else if (intent.equals("ReadPractice"))
                {
                    Intent intent = new Intent(context, Activity_practice_read.class);
                    intent.putExtra("Normal","Normal");
                    dismiss();
                    context.startActivity(intent);
                    CustomViewItem.progressDialog = new ProgressDialog(context);
                    CustomViewItem.progressDialog.setMessage("     Loading Page...     ");
                    CustomViewItem.progressDialog.show();
                }
            }
        });

        iv_h_dialogENH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.equals("Type1")){
                    Intent i = new Intent(context, Activity_Practice.class);
                    i.putExtra("Hard","hard");
                    i.putExtra("Type1","type1");
                    i.putExtra("Listen","listen");
                    dialog.dismiss();
                    context.startActivity(i);
                }else if (intent.equals("Type2")){
                    Intent i = new Intent(context, Activity_Practice.class);
                    i.putExtra("Hard","hard");
                    i.putExtra("Type2","type2");
                    i.putExtra("Listen","listen");
                    dialog.dismiss();
                    context.startActivity(i);
                }else if (intent.equals("ReadPractice"))
                {
                    Intent intent = new Intent(context, Activity_practice_read.class);
                    intent.putExtra("Hard","Hard");
                    dismiss();
                    context.startActivity(intent);
                    CustomViewItem.progressDialog = new ProgressDialog(context);
                    CustomViewItem.progressDialog.setMessage("     Loading Page...     ");
                    CustomViewItem.progressDialog.show();
                }
            }
        });


    }

    public void show(){
        dialog.show();
    }

    public void dismiss(){
        dialog.dismiss();
    }




}
