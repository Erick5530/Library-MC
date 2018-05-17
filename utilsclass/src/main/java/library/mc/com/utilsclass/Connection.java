package library.mc.com.utilsclass;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.view.View;



public class Connection {

    private Activity act;

    public Connection(Activity act) {
        this.act = act;
    }

    public boolean getConnection(String info) {

        ConnectivityManager cm = (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        final CustomAlert alert = new CustomAlert(act);

        /*--------------------------------- CONECTADO A INTERNET ---------------------------------*/
        if (activeNetwork != null) {
            /*------------------------------- CONECTADO A WIFI -----------------------------------*/
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                if (info.equals("Informar")) {
                    alert.setTypeCustom(ContextCompat.getDrawable(act, R.drawable.baseline_wifi_black_24),
                            "CONECTADO", "Tu dispositivo tiene conexión a Wifi", "Ok");
                    alert.getBtnLeft().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alert.close();
                        }
                    });
                    alert.show();
                }
                return true;
            }
            /*------------------------------- CONECTADO A DATOS ----------------------------------*/
            else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                if (info.equals("Informar")) {
                    alert.setTypeCustom(ContextCompat.getDrawable(act, R.drawable.baseline_wifi_black_24),
                            "CONECTADO", "Tu dispositivo tiene conexión móvil", "Ok");
                    alert.getBtnLeft().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alert.close();
                        }
                    });
                    alert.show();
                }
                return true;
            }
        }
        /*-------------------------------- NO CONECTADO A INTERNET -------------------------------*/
        else {
            if (info.equals("Informar")) {
                alert.setTypeCustom(ContextCompat.getDrawable(act, R.drawable.baseline_wifi_off_24),
                        "NO CONECTADO", "Tu Dispositivo no tiene Conexion a Internet", "Ok");
                alert.getBtnLeft().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.close();
                    }
                });
                alert.show();
            } else {
                return false;
            }
        }
        return false;
    }

}
