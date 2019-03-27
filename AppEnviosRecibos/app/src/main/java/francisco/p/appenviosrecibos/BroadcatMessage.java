package francisco.p.appenviosrecibos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class BroadcatMessage extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle pudsBundle=intent.getExtras();
        Object[] pdus=(Object[])pudsBundle.get("pdus");
        SmsMessage messages=SmsMessage.createFromPdu((byte[])pdus[0]);

        String num=messages.getDisplayOriginatingAddress();

        String nombre="";
        for (int i=0;i<MainActivity.contactos.size();i++){
            if(MainActivity.contactos.get(i).getTelefono().equals(num)){
                nombre=MainActivity.contactos.get(i).getNombre();
            }
        }
        Intent smsIntent=new Intent(context,MainActivity.class);
         smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         smsIntent.putExtra("MessageNombre",nombre);
         smsIntent.putExtra("Message",messages.getMessageBody());
         context.startActivity(smsIntent);
        Toast.makeText(context, "SMS recibido de: "+nombre+"\n"+messages.getMessageBody(),

                Toast.LENGTH_SHORT).show();




    }
}
