package francisco.p.facebookfeed;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.jar.JarException;

public class MainActivity extends AppCompatActivity {
CallbackManager callbackManager;
TextView texto;
Button entrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        callbackManager= CallbackManager.Factory.create();
        texto=(TextView)findViewById(R.id.texto);
        entrar=(Button)findViewById(R.id.boton);
        hash();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Cancelado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void login(View v){
LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email","public_profile","user_birthday","user_posts"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    GraphRequest graphRequest;
    private void handleFacebookToken(AccessToken accessToken){
        Log.d("tokenfb",accessToken.getToken());
        Profile profile=Profile.getCurrentProfile();

        if(profile!=null){
            String salida=profile.getLastName();
            texto.setText(salida);
            graphRequest=GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                        String feed=object.optString("feed");
                        Log.d("email",response.toString());
                        //texto.setText(response.toString());
                        texto.setText(object.optString("feed"));
                }
            });
            Bundle paramBundle=new Bundle();
            paramBundle.putString("fields","id,name,link,feed,email");
            graphRequest.setParameters(paramBundle);
graphRequest.executeAsync();
        }

    }

    public void hash(){
        PackageInfo info;
        try{
            info= getPackageManager().getPackageInfo("francisco.p.facebookfeed",PackageManager.GET_SIGNATURES);
            for (Signature signature:
                 info.signatures) {
                MessageDigest md;
                md=MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String algo=new String(Base64.encode(md.digest(),0));
                Log.e("Hash",algo);
            }
        }catch(PackageManager.NameNotFoundException el){
            el.printStackTrace();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }
}
