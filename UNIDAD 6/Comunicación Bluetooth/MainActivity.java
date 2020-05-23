package francisco.p.appherramientabraille;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

import francisco.p.appherramientabraille.Fragmentos.FragmentoAlfabeto;
import francisco.p.appherramientabraille.Fragmentos.FragmentoDeviceList;
import francisco.p.appherramientabraille.Fragmentos.FragmentoMayusculas;
import francisco.p.appherramientabraille.Fragmentos.FragmentoNumeros;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        Button btnConectar;
        public static String address=null;
    public static BluetoothAdapter btAdapter = null;
    public static BluetoothSocket btSocket = null;
    public static OutputStream outStream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //cargar imagen o logo al iniciar
        cargarFragmento(new FragmentoDeviceList());
        //Traer dirección del dispositivo bluetooth (Agregado por nosotros)
        Intent newint=getIntent();
        address=newint.getStringExtra(DeviceList.EXTRA_ADDRESS);




    }



    //YA estaban

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Alfabeto) {
            // Handle the camera action
            cargarFragmento(new FragmentoAlfabeto());
        } else if (id == R.id.nav_Numeros) {
            cargarFragmento(new FragmentoNumeros());
        } else if (id == R.id.nav_Mayusculas) {
            cargarFragmento(new FragmentoMayusculas());
        }else if(id==R.id.nav_conectar){
            cargarFragmento(new FragmentoDeviceList());
        }
        else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_share2) {

        }else if (id == R.id.nav_share3) {

        }else if (id == R.id.nav_share4) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cargarFragmento(Fragment fragmento){
        try{
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.contenedorFragmento,fragmento).commit();
        }catch (Exception ex){
            Toast.makeText(this, "Debe conectarse a la herramienta", Toast.LENGTH_SHORT).show();
        }


    }

}
