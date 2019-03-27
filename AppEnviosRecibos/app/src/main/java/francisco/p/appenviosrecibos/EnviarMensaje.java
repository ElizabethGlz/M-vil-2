package francisco.p.appenviosrecibos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnviarMensaje extends AppCompatActivity {
    public static String telefono, nombre, mensaje;
    TextView text;
    EditText sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_mensaje);
        text=(TextView)(findViewById(R.id.texto));
        text.setText(text.getText().toString()+" "+nombre);
        sms=(EditText)(findViewById(R.id.et_mensaje));


    }
    public void enviarSMS(View v){
        mensaje=sms.getText().toString();
       try{
           SmsManager smsManager=SmsManager.getDefault();
           smsManager.sendTextMessage(telefono,null,mensaje,null,null);
           Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_SHORT).show();
       }catch (Exception e){
           Toast.makeText(this, "No se pudo enviar el mensaje", Toast.LENGTH_SHORT).show();
       }
    }
}
