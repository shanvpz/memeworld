package in.techfantasy.memeworld;

import android.content.Context;

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

public class FeedParser {
    public static List<memeItem> mainList=new ArrayList<>();
    private static RequestQueue mRequestQueue;
    private Context ctx;
    private static String[] comicsPageIDS = {"ojoclickz"};



    public static List<memeItem> getFeed(int categoryID,Context ctx){
        mRequestQueue= Volley.newRequestQueue(ctx);

        for (String item:comicsPageIDS) {
            ParseJson(mRequestQueue,item);
        }
        return mainList;
    }


    private static void ParseJson(RequestQueue requestQueue,String pageID){
        String url="https://graph.facebook.com/"+pageID+"/feed?fields=full_picture,message&access_token=EAAFPpuji4vwBAPU0x0OjZCsGoLy7YwTgHaeKRtkTAAHmEJcKpeHXz4WwaPm8jS0budNAO8UO65NwOMWyMTFhLi2pFIXDCPvNi9EWrutcJ1Yzrs4YuG0Q2qIaacMNUs0PkrozC7meCyQK9wiLJ4lTZBNujkGv2scEXnMzTwywZDZD";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String imgUrl = jsonObject.getString("full_picture");
                        String message = jsonObject.getString("message");
                        mainList.add(new memeItem(imgUrl, message));

                    }

                   // vpAdapter = new VPAdapter( memeItems,VPActivity.this);
                    //viewPager.setAdapter(vpAdapter);
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
        requestQueue.start();
    }




}
