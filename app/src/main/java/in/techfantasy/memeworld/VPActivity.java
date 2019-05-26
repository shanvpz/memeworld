package in.techfantasy.memeworld;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VPActivity extends AppCompatActivity {

    ViewPager viewPager;
    VPAdapter vpAdapter;
    List<memeItem> memeItems;
    RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp);
        viewPager = findViewById(R.id.viewPager);
        memeItems=new ArrayList<>();
        memeItems.addAll(FeedParser.getFeed(0,this));
        //mRequestQueue=Volley.newRequestQueue(this);
         vpAdapter = new VPAdapter(memeItems,VPActivity.this);
        viewPager.setAdapter(vpAdapter);

    }



}
