package p.gonzalez.gestos;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{
    TextView et;
    GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=(TextView)findViewById(R.id.txtTexto);
        this.gestureDetector=new GestureDetector(this, this);
        gestureDetector.setOnDoubleTapListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        et.setText("Mantenga presionado para abrir la cámara."+
                "\nToque dos veces para ir al teléfono."+
                "\nDeslice horizontalmente para ir a mensajes."
        );
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        et.setText("Teléfono.");
       // Intent i=getPackageManager().getLaunchIntentForPackage("com.contactos");
        Intent i=new Intent(Intent.ACTION_DIAL);

        startActivity(i);
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
       // et.setText("onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
      //  et.setText("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
       // et.setText("onSingleTapUp");
        return false;
    }


    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
       // et.setText("onScroll");
       // Intent i=getPackageManager().getLaunchIntentForPackage("com.playermusic.musicplayerapp");
        //startActivity(i);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        et.setText("Abriendo Cámara");
        Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(i);

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        et.setText("Enviar mensaje");
        //Intent sendIntent=new Intent(Intent.ACTION_VIEW);
       // sendIntent.setData(Uri.parse("sms:"));
       // startActivity(sendIntent);
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setType("vnd.android-dir/mms-sms");
        startActivity(intent);
        return false;
    }
}
