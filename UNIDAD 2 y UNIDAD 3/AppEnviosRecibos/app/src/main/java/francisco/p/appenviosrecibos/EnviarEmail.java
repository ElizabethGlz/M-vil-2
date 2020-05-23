package francisco.p.appenviosrecibos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.sip.SipAudioCall;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Properties;



import android.util.Log;

public class EnviarEmail extends AppCompatActivity {
    Button btnEnviar;
    ListView lvnombres;
    public String telefono,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_email);
        btnEnviar=(Button)findViewById(R.id.btnEmail);
        lvnombres=(ListView)findViewById(R.id.lvnombres);
        ArrayAdapter<String>nombresAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,MainActivity.nombres);
        ArrayAdapter<Contacto>contactoAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,MainActivity.contactos);
        lvnombres.setAdapter(contactoAdapter);
        lvnombres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contacto c=MainActivity.contactos.get(position);
                telefono=c.getTelefono();
                email=c.getEmail();
                EnviarMensaje.telefono=c.getTelefono();
                EnviarMensaje.nombre=c.getNombre();
                Toast.makeText(EnviarEmail.this, "Contacto seleccionado: "+c.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void enviar(View view) {
      /*  String mensaje="Hola :v";
        String enviador="perry.john62@gmail.com";
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT,enviador);
        intent.putExtra(Intent.EXTRA_TEXT,mensaje);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Escoge un email Client"));
        */
      Intent intent=new Intent(Intent.ACTION_SENDTO,Uri.fromParts("mailto",email,null));
      intent.putExtra(Intent.EXTRA_SUBJECT,"App");
      startActivity(Intent.createChooser(intent,"Selecciona un Email Client"));
    }


    public void llamar(View view){
        String numero=telefono;
        String uri="tel:"+numero.trim();
        Intent intent=new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }
    public void sms(View view){
        Intent intent=new Intent(this,EnviarMensaje.class);
        startActivity(intent);
    }

    }


