package francisco.p.gpsdistance;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    boolean crearInicio=true;
    boolean eliminar=false;
    LatLng inicio,fin;
    Marker inicioMarca,finMarca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng itsur = new LatLng(20.1391, -101.1501);
        mMap.addMarker(new MarkerOptions().position(itsur).title("Marker in ITSUR"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(itsur));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(inicioMarca!=null&&finMarca!=null&&eliminar){
                    inicioMarca.remove();
                    finMarca.remove();
                    eliminar=false;
                }
                if(crearInicio) {
                    inicio = new LatLng(latLng.latitude, latLng.longitude);
                    inicioMarca=mMap.addMarker(new MarkerOptions().position(inicio).title("Marker in Inicio"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(inicio));
                    crearInicio = false;
                }else{
                    fin=new LatLng(latLng.latitude,latLng.longitude);
                    finMarca=mMap.addMarker(new MarkerOptions().position(fin).title("Marker in Fin"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(fin));
                    double distancia=getDistanceMeters(inicio,fin);
                    Toast.makeText(MapsActivity.this, "La distancia en metros es: "+Math.round(distancia), Toast.LENGTH_LONG).show();
                    crearInicio=true;
                    eliminar=true;
                }
            }
        });
    }

    public static double getDistanceMeters(LatLng pt1, LatLng pt2){
        double distance = 0d;
        try{
            double theta = pt1.longitude - pt2.longitude;
            double dist = Math.sin(Math.toRadians(pt1.latitude)) * Math.sin(Math.toRadians(pt2.latitude))
                    + Math.cos(Math.toRadians(pt1.latitude)) * Math.cos(Math.toRadians(pt2.latitude)) * Math.cos(Math.toRadians(theta));

            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            distance = dist * 60 * 1853.1596;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return distance;
    }
}
