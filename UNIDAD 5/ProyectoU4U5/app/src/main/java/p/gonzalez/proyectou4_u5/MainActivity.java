package p.gonzalez.proyectou4_u5;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.nfc.Tag;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {
    GestureDetector gestureDetector;
    double latitud;
    double longitud;
    public static String direccion = "";
    String mensaje;
    Geocoder geocoder;
    List<Address> addresses;
    private FusedLocationProviderClient client;

    LocationManager lm;
    Location location;
    double longitude = 0;
    double latitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();
       // client = LocationServices.getFusedLocationProviderClient(this);
        geocoder = new Geocoder(this, Locale.getDefault());
        this.gestureDetector = new GestureDetector(this, this);
        gestureDetector.setOnDoubleTapListener(this);

        direccion = "";


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
       /* lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return false;
        }*/
       /* location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
       client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                Toast.makeText(MainActivity.this, ".............", Toast.LENGTH_SHORT).show();
                if(location!=null){
                    latitud=location.getLatitude();
                    longitud=location.getLongitude();
                   Toast.makeText(MainActivity.this, latitud+"", Toast.LENGTH_SHORT).show();
                    try {
                        addresses=geocoder.getFromLocation(latitud,longitud,1);

                        direccion=addresses.get(0).getAddressLine(0);

                        Toast.makeText(MainActivity.this, direccion, Toast.LENGTH_SHORT).show();

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

        });
*/
         longitude = 0;
        latitude = 0;

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return false;
        }
        if (lm.getLastKnownLocation(LocationManager.GPS_PROVIDER) != null) {
            location = lm
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        } else {
            location = lm
                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
        try {
            addresses=geocoder.getFromLocation(latitude,longitude,1);

            direccion=addresses.get(0).getAddressLine(0);

            Toast.makeText(MainActivity.this, direccion, Toast.LENGTH_SHORT).show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        mensaje="Auxilio: "+direccion;
        try{
            SmsManager smsManager=SmsManager.getDefault();
          //  String numero="4433708258";
            Telefono telefono=obtenerNum();
            Toast.makeText(this, telefono.getTelefono(), Toast.LENGTH_SHORT).show();
            smsManager.sendTextMessage(telefono.getTelefono(),null,mensaje,null,null);
            Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }


        return false;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Bundle extras = data.getExtras();
            longitud = extras.getDouble("Longitude");
            latitud = extras.getDouble("Latitude");
        }
    }
private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},1);
}
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
    public void agregarNum(View view){
        MyDBHandler dbHandler = new MyDBHandler(this, null,
                null, 1);
        boolean result = dbHandler.deleteHandler(1);
        if (result) {
           // studentid.setText("");
            //studentname.setText("");
            //lst.setText("Record Deleted");
        }
        //Agregar nuevo
        EditText et=(EditText)(findViewById(R.id.txt_numero));
        int id = 1;
        String numero = et.getText().toString();
        Telefono telefono = new Telefono(id, numero);
        dbHandler.addHandler(telefono);
        Toast.makeText(this, "Número agregado", Toast.LENGTH_SHORT).show();
        et.setText("");
    }
    public Telefono obtenerNum(){
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Telefono telefono =
                dbHandler.findHandler(1);
      /*  if (telefono != null) {
            lst.setText(String.valueOf(student.getID()) + " " + student.getStudentName() + System.getProperty("line.separator"));
            studentid.setText("");
            studentname.setText("");
        } else {
            lst.setText("No Match Found");
            studentid.setText("");
            studentname.setText("");
        }*/
        return telefono;
    }

}
