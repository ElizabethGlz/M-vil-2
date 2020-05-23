package p.gonzalez.broadcastreceiverreiniciar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BroadcastReceiverReiniciar extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
    if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
      Intent i = new Intent(context,MainActivity.class);
      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      context.startActivity(i);
        Toast.makeText(context, "Dispositivo reiniciado", Toast.LENGTH_SHORT).show();
    }

    }
}
