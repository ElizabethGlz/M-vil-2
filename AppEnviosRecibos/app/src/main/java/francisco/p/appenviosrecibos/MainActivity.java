package francisco.p.appenviosrecibos;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.Toast;


import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
public static ArrayList<String>telefonos,emails,nombres;
public static ArrayList<Contacto>contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Llamadas


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},1);
        }

//recibir correo
        getContentResolver().registerContentObserver(Uri.parse("content://com.google.android.gm/elizabeth.10.xc@gmail.com/labels"), true,
                new MyObserver(new Handler(){}));

        //Agregado (ya sirve).
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            String nombre=extras.getString("MessageNombre");
            String message=extras.getString("Message");
            TextView mensaje=(TextView)(findViewById(R.id.txt_recibido));
            mensaje.setText("De: "+nombre+"\n"+"Mensaje:\n"+message);
            Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

        //Bien
        telefonos=new ArrayList<>();
        emails=new ArrayList<>();
        nombres=new ArrayList<>();
        contactos=new ArrayList<>();
        ContentResolver resolver=getContentResolver();
        Cursor cursor =resolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        while(cursor.moveToNext()){
            String id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String nombre=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            this.nombres.add(nombre);
            Cursor telefonos=resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" = ?",new String[]{id},null);
            String telefono=null;
            while(telefonos.moveToNext()){
                telefono=telefonos.getString(telefonos.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                this.telefonos.add(telefono);
            }

            Cursor emails=resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID+" = ?",new String[]{id},null);
            String email=null;
            while(emails.moveToNext()){
                email=emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                this.emails.add(email);
            }
            this.contactos.add(new Contacto(nombre,telefono,email));
        }

        Button btnEmail=(Button)findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salirEmail();
            }
        });
        //Agregado

    }

    public void salirEmail(){
        Intent irEmail=new Intent(this,EnviarEmail.class);
        startActivity(irEmail);
        //Toast.makeText(this, this.emails.get(0), Toast.LENGTH_SHORT).show();
    }

    //Agregado
    class MyObserver extends ContentObserver {
        // left blank below constructor for this Contact observer example to work
        // or if you want to make this work using Handler then change below registering  //line
        public MyObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            this.onChange(selfChange, null);
            Log.e("AA", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + selfChange);
            Toast.makeText(MainActivity.this, "aa", Toast.LENGTH_SHORT).show();
        }
        int cont = 0;
        @Override
        public void onChange(boolean selfChange, Uri uri) {
            if(cont%3==0) {

                NotificationCompat.Builder mBuilder;
                NotificationManager mNotifyMgr = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

                int icono = R.mipmap.ic_launcher;
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, i, 0);

                mBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(icono)
                        .setContentTitle("Correo recibido")
                        .setContentText("Se ha detectado la llegada de un nuevo correo")
                        .setVibrate(new long[]{100, 250, 100, 500})
                        .setAutoCancel(true);


                mNotifyMgr.notify(1, mBuilder.build());
            }
            cont++;
            // depending on the handler you might be on the UI
            // thread, so be cautious!
        }
    }


}
