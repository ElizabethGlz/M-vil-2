package francisco.p.appherramientabraille.Fragmentos;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

import francisco.p.appherramientabraille.DeviceList;
import francisco.p.appherramientabraille.MainActivity;
import francisco.p.appherramientabraille.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoAlfabeto extends Fragment {
    private Button btna;
    private Button btnb;
    private Button btnc;
    private Button btnd;
    private Button btne;
    private Button btnf;
    private Button btng;
    private Button btnh;
    private Button btni;
    private Button btnj;
    private Button btnk;
    private Button btnl;
    private Button btnm;
    private Button btnn;
    private Button btno;
    private Button btnp;
    private Button btnq;
    private Button btnr;
    private Button btns;
    private Button btnt;
    private Button btnu;
    private Button btnv;
    private Button btnw;
    private Button btnx;
    private Button btny;
    private Button btnz;
   // String address=null;
//Agregado
   private static final String TAG = "bluetooth1";

    // SPP UUID service
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // MAC-address of Bluetooth module (you must edit this line)
    private static String address = null;
    public FragmentoAlfabeto() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Agregado

        address=MainActivity.address;

        MainActivity.btAdapter  = BluetoothAdapter.getDefaultAdapter();
        checkBTState();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragmento_alfabeto, container, false);
        btna=(Button)view.findViewById(R.id.btna);
        btnb=(Button)view.findViewById(R.id.btnb);
        btnc=(Button)view.findViewById(R.id.btnc);
        btnd=(Button)view.findViewById(R.id.btnd);
        btne=(Button)view.findViewById(R.id.btne);
        btnf=(Button)view.findViewById(R.id.btnf);
        btng=(Button)view.findViewById(R.id.btng);
        btnh=(Button)view.findViewById(R.id.btnh);
        btni=(Button)view.findViewById(R.id.btni);
        btnj=(Button)view.findViewById(R.id.btnj);
        btnk=(Button)view.findViewById(R.id.btnk);
        btnl=(Button)view.findViewById(R.id.btnl);
        btnm=(Button)view.findViewById(R.id.btnm);
        btnn=(Button)view.findViewById(R.id.btnn);
        btno=(Button)view.findViewById(R.id.btno);
        btnp=(Button)view.findViewById(R.id.btnp);
        btnq=(Button)view.findViewById(R.id.btnq);
        btnr=(Button)view.findViewById(R.id.btnr);
        btns=(Button)view.findViewById(R.id.btns);
        btnt=(Button)view.findViewById(R.id.btnt);
        btnu=(Button)view.findViewById(R.id.btnu);
        btnv=(Button)view.findViewById(R.id.btnv);
        btnw=(Button)view.findViewById(R.id.btnw);
        btnx=(Button)view.findViewById(R.id.btnx);
        btny=(Button)view.findViewById(R.id.btny);
        btnz=(Button)view.findViewById(R.id.btnz);

        btna.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Toast.makeText(getActivity(), "a", Toast.LENGTH_SHORT).show();
                sendData("a");

            }
        });

        btnb.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "b", Toast.LENGTH_SHORT).show();
                sendData("b");
            }
        }));
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "c", Toast.LENGTH_SHORT).show();
                sendData("c");
            }
        });
        btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "d", Toast.LENGTH_SHORT).show();
                sendData("d");
            }
        });
        btne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "e", Toast.LENGTH_SHORT).show();
                sendData("e");
            }
        });
        btnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "f", Toast.LENGTH_SHORT).show();
                sendData("f");
            }
        });
        btng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "g", Toast.LENGTH_SHORT).show();
                sendData("g");
            }
        });
        btnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "h", Toast.LENGTH_SHORT).show();
                sendData("h");
            }
        });
        btni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "i", Toast.LENGTH_SHORT).show();
                sendData("i");
            }
        });
        btnj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "j", Toast.LENGTH_SHORT).show();
                sendData("j");
            }
        });
        btnk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "k", Toast.LENGTH_SHORT).show();
                sendData("k");
            }
        });
        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "l", Toast.LENGTH_SHORT).show();
                sendData("l");
            }
        });
        btnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "m", Toast.LENGTH_SHORT).show();
                sendData("m");
            }
        });
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "n", Toast.LENGTH_SHORT).show();
                sendData("n");
            }
        });
        btno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "o", Toast.LENGTH_SHORT).show();
                sendData("o");
            }
        });

        btnp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "p", Toast.LENGTH_SHORT).show();
                sendData("p");
            }
        });
        btnq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "q", Toast.LENGTH_SHORT).show();
                sendData("q");
            }
        });
        btnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "r", Toast.LENGTH_SHORT).show();
                sendData("r");
            }
        });
        btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "s", Toast.LENGTH_SHORT).show();
                sendData("s");
            }
        });
        btnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "t", Toast.LENGTH_SHORT).show();
                sendData("t");
            }
        });
        btnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "u", Toast.LENGTH_SHORT).show();
                sendData("u");
            }
        });
        btnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "v", Toast.LENGTH_SHORT).show();
                sendData("v");
            }
        });
        btnw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "w", Toast.LENGTH_SHORT).show();
                sendData("w");
            }
        });
        btnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "x", Toast.LENGTH_SHORT).show();
                sendData("x");
            }
        });
        btny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "y", Toast.LENGTH_SHORT).show();
                sendData("y");
            }
        });
        btnz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "z", Toast.LENGTH_SHORT).show();
                sendData("z");
            }
        });
         return view;
    }
//Métodos BT
private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
    if(Build.VERSION.SDK_INT >= 10){
        try {
            final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[] { UUID.class });
            return (BluetoothSocket) m.invoke(device, MY_UUID);
        } catch (Exception e) {
          //  Log.e(TAG, "Could not create Insecure RFComm Connection",e);
        }
    }
    return  device.createRfcommSocketToServiceRecord(MY_UUID);
}

    @Override
    public void onResume() {
        super.onResume();
        try {
            Log.d(TAG, "...onResume - try connect...");

            // Set up a pointer to the remote node using it's address.
            BluetoothDevice device = MainActivity.btAdapter.getRemoteDevice(address);

            // Two things are needed to make a connection:
            //   A MAC address, which we got above.
            //   A Service ID or UUID.  In this case we are using the
            //     UUID for SPP.

            try {
                if(MainActivity.btSocket==null){
                    MainActivity.btSocket = createBluetoothSocket(device);
                }

            } catch (IOException e1) {
                errorExit("Fatal Error", "In onResume() and socket create failed: " + e1.getMessage() + ".");
            }

            // Discovery is resource intensive.  Make sure it isn't going on
            // when you attempt to connect and pass your message.
            MainActivity.btAdapter.cancelDiscovery();

            // Establish the connection.  This will block until it connects.
            Log.d(TAG, "...Connecting...");
            try {
                if(!MainActivity.btSocket.isConnected()){
                    MainActivity.btSocket.connect();
                }

                Log.d(TAG, "...Connection ok...");
            } catch (IOException e) {
                try {
                    MainActivity.btSocket.close();
                } catch (IOException e2) {
                    errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
                }
            }

            // Create a data stream so we can talk to server.
            Log.d(TAG, "...Create Socket...");

            try {
                MainActivity.outStream = MainActivity.btSocket.getOutputStream();
            } catch (IOException e) {
                errorExit("Fatal Error", "In onResume() and output stream creation failed:" + e.getMessage() + ".");
            }
        }catch(Exception ex){
            errorExit("Verifique conectividad BT", "Debe estar conectado a la herramienta. Vaya a la opción conectar.");
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "...In onPause()...");

        if (MainActivity.outStream != null) {
           // try {
              //  outStream.flush();
           // } catch (IOException e) {
               // errorExit("Fatal Error", "In onPause() and failed to flush output stream: " + e.getMessage() + ".");
           // }
        }

       // try     {
         //   btSocket.close();
        //} catch (IOException e2) {
       //     errorExit("Fatal Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
       // }


    }
    private void checkBTState() {
        // Check for Bluetooth support and then check to make sure it is turned on
        // Emulator doesn't support Bluetooth and will return null
        if(MainActivity.btAdapter==null) {
            errorExit("Fatal Error", "Bluetooth not support");
        } else {
            if (MainActivity.btAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth ON...");
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    private void errorExit(String title, String message){
        Toast.makeText(getActivity(), title + " - " + message, Toast.LENGTH_LONG).show();
    }
    private void sendData(String message) {
        byte[] msgBuffer = message.getBytes();

        Log.d(TAG, "...Send data: " + message + "...");

        try {
            MainActivity.outStream.write(msgBuffer);
        } catch (IOException e) {
            String msg = "In onResume() and an exception occurred during write: " + e.getMessage();
            if (address.equals("00:00:00:00:00:00"))
                msg = msg + ".\n\nUpdate your server address from 00:00:00:00:00:00 to the correct address on line 35 in the java code";
            msg = msg +  ".\n\nCheck that the SPP UUID: " + MY_UUID.toString() + " exists on server.\n\n";

            errorExit("Fatal Error", msg);
        }
    }

}
