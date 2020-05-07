package ir.hhadanooo.ielts.DialogAG;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import ir.hhadanooo.ielts.MainActivity;
import ir.hhadanooo.ielts.R;

public class DialogAGShow {

    Context context;
    DisplayMetrics dm;
    Dialog dialog;
    ImageView iv_a_dialogAG , iv_g_dialogAG;
    TextView tv_title_dialogAG;


    public DialogAGShow(final Context context, DisplayMetrics dm , final String intent){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_dialog_a_g);
        Objects.requireNonNull(dialog.getWindow()).setLayout((int) (dm.widthPixels*.8)
                ,ViewGroup.LayoutParams.WRAP_CONTENT );
        tv_title_dialogAG = dialog.findViewById(R.id.tv_title_dialogAG);
        iv_a_dialogAG = dialog.findViewById(R.id.iv_a_dialogAG);
        iv_g_dialogAG = dialog.findViewById(R.id.iv_g_dialogAG);

        tv_title_dialogAG.setTextSize((int) (dm.widthPixels*0.02));

        iv_a_dialogAG.getLayoutParams().width = (int) (dm.widthPixels*0.27);
        iv_a_dialogAG.getLayoutParams().height = (int) (dm.widthPixels*0.085);

        iv_g_dialogAG.getLayoutParams().width = (int) (dm.widthPixels*0.27);
        iv_g_dialogAG.getLayoutParams().height = (int) (dm.widthPixels*0.085);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        iv_a_dialogAG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.equals("read")){
                    Toast.makeText(context, "readActivity A", Toast.LENGTH_SHORT).show();
                    //context.startActivity(new Intent(DialogAGShow.this , ));
                }else if (intent.equals("write")){
                    Toast.makeText(context, "writeActivity A", Toast.LENGTH_SHORT).show();
                    //context.startActivity(new Intent(DialogAGShow.this , ));
                }
            }
        });

        iv_g_dialogAG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.equals("read")){
                    Toast.makeText(context, "readActivity G", Toast.LENGTH_SHORT).show();
                    //context.startActivity(new Intent(DialogAGShow.this , ));
                }else if (intent.equals("write")){
                    Toast.makeText(context, "writeActivity G", Toast.LENGTH_SHORT).show();
                    //context.startActivity(new Intent(DialogAGShow.this , ));
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