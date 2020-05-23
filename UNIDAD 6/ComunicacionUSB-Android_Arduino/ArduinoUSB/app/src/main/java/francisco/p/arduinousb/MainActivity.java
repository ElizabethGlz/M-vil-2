package francisco.p.arduinousb;

import android.hardware.usb.UsbDevice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import me.aflak.arduino.Arduino;
import me.aflak.arduino.ArduinoListener;

public class MainActivity extends AppCompatActivity implements ArduinoListener {

    private Arduino arduino;
    private TextView textView;
    private EditText editText;
    private Button btnEnviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arduino=new Arduino(this);
        textView=(TextView)findViewById(R.id.recibido);
        editText=(EditText)findViewById(R.id.txtmandar);
        btnEnviar=(Button)findViewById(R.id.btnMandar);
    }

    public void mandar(View view){
        String msg = editText.getText().toString();
        arduino.send(msg.getBytes());
    }

    @Override
    protected void onStart() {
        super.onStart();
        arduino.setArduinoListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        arduino.unsetArduinoListener();
        arduino.close();
    }

    @Override
    public void onArduinoAttached(UsbDevice device) {
        display("Arduino attached!");
        arduino.open(device);
    }

    @Override
    public void onArduinoDetached() {
        display("Arduino detached");
    }

    @Override
    public void onArduinoMessage(byte[] bytes) {
        display("Received: "+new String(bytes));
    }

    @Override
    public void onArduinoOpened() {
        String str = "Hello World !";
        arduino.send(str.getBytes());
    }

    public void display(final String message){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.append(message+"\n");
            }
        });
    }
}
