package p.gonzalez.statetwitter;

import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Uri imageUri = FileProvider.getUriForFile(MainActivity.this,
         //       BuildConfig.APPLICATION_ID + ".file_provider",
           //     new File("/path/to/image"));
        TweetComposer.Builder builder = new TweetComposer.Builder(this)
                .text("just setting up my Twitter Kit.")
                ;
        builder.show();

    }
}
