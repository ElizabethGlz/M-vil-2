package francisco.p.transferusb;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
EditText texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto=(EditText)findViewById(R.id.txttexto);

        Thread myThread=new Thread(new MyServerThread());
        myThread.start();
    }

    class MyServerThread implements Runnable{
        Socket s;
        ServerSocket ss;
        InputStreamReader isr;
        BufferedReader bufferedReader;

        String message;

        Handler h=new Handler();
        @Override
        public void run() {
            try{
                ss=new ServerSocket(7801);
                while(true){
                    s=ss.accept();
                    isr=new InputStreamReader(s.getInputStream());
                    bufferedReader=new BufferedReader(isr);
                    message=bufferedReader.readLine();

                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }catch(IOException e){
                e.printStackTrace();
            }

        }
    }

    public void send(View view){
        MessageSender messageSender=new MessageSender();
        messageSender.execute(texto.getText().toString());
    }
}
