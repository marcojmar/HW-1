package com.codepath.instagram.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.instagram.R;
import com.codepath.instagram.helpers.Utils;
import com.codepath.instagram.models.InstagramComment;
import com.codepath.instagram.models.InstagramImage;
import com.codepath.instagram.models.InstagramPost;
import com.codepath.instagram.models.InstagramUser;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_posts);

        List<InstagramPost> instagramPosts = fetchPost();

        RecyclerView rvPosts = (RecyclerView) findViewById(R.id.rvPosts);

        PostsAdapter adapter = new PostsAdapter(instagramPosts, this);

        // create adapter
        rvPosts.setAdapter(adapter);

        // set adapter
        rvPosts.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

    public List<InstagramPost> fetchPost() {
         List<InstagramPost> allPosts = null;

        try {
            JSONObject jsonObject = (JSONObject) Utils.loadJsonFromAsset(this, "popular.json");
            allPosts = Utils.decodePostsFromJsonResponse(jsonObject);
        }
        catch (Exception e) {
            e.getStackTrace();
        }

        return allPosts;
    }

    private void readPost(InstagramPost post) {
        String imageUrl = post.image.imageUrl;
        String fullName = post.user.fullName;
        int likesCount = post.likesCount;
        long createTime = post.createdTime;

        String[] captions;
        String comment = null;
        String hashtag = null;
        if (post.caption != null) {
            captions = post.caption.split(" #");
            comment = captions[0];
            hashtag = "#" + captions[1];
        }

        int i =1;
    }
}
