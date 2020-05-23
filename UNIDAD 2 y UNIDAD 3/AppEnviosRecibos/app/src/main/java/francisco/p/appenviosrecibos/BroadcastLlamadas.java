package francisco.p.appenviosrecibos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class BroadcastLlamadas extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
            Toast.makeText(context, "Llamada iniciada", Toast.LENGTH_SHORT).show();
        }else if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_IDLE)){
            Toast.makeText(context, "Llamada finalizada", Toast.LENGTH_SHORT).show();
        }else if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)){
            Toast.makeText(context, "Llamada timbrando", Toast.LENGTH_SHORT).show();
        }

    }
}
