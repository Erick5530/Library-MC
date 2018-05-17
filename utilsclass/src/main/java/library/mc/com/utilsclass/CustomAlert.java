package library.mc.com.utilsclass;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomAlert {

    private static final String TAG = CustomAlert.class.getName();

    private Activity act;

    private View view;
    private LayoutInflater inflater;
    private Dialog alertDialog;


    public CustomAlert(Activity act) {

        inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.act = act;
        this.view = inflater.inflate(R.layout.custom_alert, null);

        alertDialog = new Dialog(act);
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                ((ImageView) view.findViewById(R.id.circleView)).setImageDrawable(null);
                ((ImageView) view.findViewById(R.id.close)).setImageDrawable(null);
                view.findViewById(R.id.buttonLeft).setBackground(null);
                view.findViewById(R.id.buttonRight).setBackground(null);
            }
        });
        view.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    public void addView(View viewCustom) {
        ((LinearLayout) view.findViewById(R.id.Content)).addView(viewCustom);
    }

    public void setTypeCustom(Drawable icon, String title, String text, String textOneButton) {
        view.findViewById(R.id.circleView).setBackgroundResource(R.drawable.circle_alert);
        ((ImageView) view.findViewById(R.id.circleView)).setImageDrawable(icon);
        ((TextView) view.findViewById(R.id.titulo)).setText(title);
        ((TextView) view.findViewById(R.id.text)).setText(text);
        ((Button) view.findViewById(R.id.buttonLeft)).setText(textOneButton);
        view.findViewById(R.id.viewVertical).setVisibility(View.GONE);
        view.findViewById(R.id.buttonRight).setVisibility(View.GONE);
    }

    public void setTypeWarning(String title, String text, String textButtonLeft, String textButtonRight) {
        view.findViewById(R.id.circleView).setBackgroundResource(R.drawable.circle_alert);
        ((ImageView) view.findViewById(R.id.circleView)).setImageDrawable(ContextCompat.getDrawable(act, R.drawable.baseline_priority_high_24));
        ((TextView) view.findViewById(R.id.titulo)).setText(title);
        ((TextView) view.findViewById(R.id.text)).setText(text);
        view.findViewById(R.id.buttonLeft).setVisibility(View.VISIBLE);
        view.findViewById(R.id.viewVertical).setVisibility(View.VISIBLE);
        view.findViewById(R.id.buttonRight).setVisibility(View.VISIBLE);
        ((Button) view.findViewById(R.id.buttonLeft)).setText(textButtonLeft);
        ((Button) view.findViewById(R.id.buttonRight)).setText(textButtonRight);
    }

    public Button getBtnLeft() {
        return view.findViewById(R.id.buttonLeft);
    }

    public Button getBtnRight() {
        return view.findViewById(R.id.buttonRight);
    }

    public void setCancelable(boolean status) {
        alertDialog.setCancelable(status);
    }

    public void show() {
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(view);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        alertDialog.show();
    }

    public void close() {
        alertDialog.dismiss();
    }
}
