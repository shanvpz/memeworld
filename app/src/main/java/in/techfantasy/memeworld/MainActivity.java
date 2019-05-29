package in.techfantasy.memeworld;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

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

public class MainActivity extends AppCompatActivity {


    private ArrayList<memeItem> memeItemArrayList;
    private memeAdapter memeAdapter;
    private RequestQueue mRequestQueue;
    Context ctx;
    JsonObjectRequest request;
    GridView gv;
    GridAdapter ga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        memeItemArrayList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        ctx = MainActivity.this;


        int[] icons = {R.drawable.com_facebook_button_icon, R.drawable.com_facebook_button_icon_blue, R.drawable.com_facebook_button_login_logo};
        gv = findViewById(R.id.gridview);
        ga = new GridAdapter(getApplicationContext(), icons);
        gv.setAdapter(ga);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                new FeedParserTask().execute();
            }
        });
    }
        private void ParseJson (RequestQueue requestQueue,final Context ctx){
            String url = "https://graph.facebook.com/1830913010536016/feed?fields=full_picture,message&access_token=EAAFPpuji4vwBAPU0x0OjZCsGoLy7YwTgHaeKRtkTAAHmEJcKpeHXz4WwaPm8jS0budNAO8UO65NwOMWyMTFhLi2pFIXDCPvNi9EWrutcJ1Yzrs4YuG0Q2qIaacMNUs0PkrozC7meCyQK9wiLJ4lTZBNujkGv2scEXnMzTwywZDZD";

            request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("data");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String imgUrl = jsonObject.getString("full_picture");
                            String message = jsonObject.getString("message");
                            memeItemArrayList.add(new memeItem(imgUrl, message));

                        }


                        FeedParser.mainList = memeItemArrayList;

                        Intent intent = new Intent(MainActivity.this, VPActivity.class);
                        ctx.startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            requestQueue.add(request);
        }

        private class FeedParserTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... Void) {
                ParseJson(mRequestQueue,ctx);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }


}
