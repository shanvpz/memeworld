package in.techfantasy.memeworld;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class VPActivity extends AppCompatActivity {

    ViewPager viewPager;
    VPAdapter vpAdapter;
   public static List<memeItem> memeItems;
    static RequestQueue mRequestQueue;
    private static String[] comicsPageIDS = {"ojoclickz"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp);
      //  memeItems=(ArrayList<memeItem>) getIntent().getSerializableExtra("mylist");
        viewPager = findViewById(R.id.viewPager);
        memeItems=new ArrayList<>();
//        memeItems.addAll(FeedParser.getFeed(0,this));
        //mRequestQueue=Volley.newRequestQueue(this);

        mRequestQueue=Volley.newRequestQueue(this);
        vpAdapter = new VPAdapter(memeItems,VPActivity.this);
        viewPager.setAdapter(vpAdapter);
    }
}
