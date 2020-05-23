package francisco.p.transferusb;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageSender extends AsyncTask<String,Void,Void> {
    Socket s;
    DataOutputStream dos;
    PrintWriter pw;

    @Override
    protected Void doInBackground(String... voids) {
        String message=voids[0];
        String message2="Hola";
        try{
            //Dirección de la computadora, deben usar el mismo puerto la app de Java y de Android
            s=new Socket("192.168.0.13",7800);
            pw=new PrintWriter(s.getOutputStream());
           // dos=new DataOutputStream(s.getOutputStream());
            //dos.writeUTF(message2);
            pw.write(message);
            pw.flush();
            //dos.flush();
            pw.close();
            s.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
