package p.gonzalez.twitter234;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("IJUA0sCrGbCdwLaNUgMym4B1b",
                        "HbVycs3s1FnGxxQl6YuJdJe2tCjezpzKl6kpPXv2VLWX4xARGS"))
                .debug(true)
                .build();
        Twitter.initialize(config);
        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("Elizabeth_Glz_D")
                .build();//.screenName("twitterdev")
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();

        //ListView lista=(ListView)findViewById(R.id.list);
        setListAdapter(adapter);
    }
}
