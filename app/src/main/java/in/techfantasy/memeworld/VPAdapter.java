package in.techfantasy.memeworld;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class VPAdapter extends PagerAdapter {

    private List<memeItem> memeItems;
    private Inflater inflater;
    private Context ctx;

    public VPAdapter(List<memeItem> memeItems, Context ctx) {
        this.memeItems = memeItems;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return memeItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v=LayoutInflater.from(ctx).inflate(R.layout.vpitem,container,false);
        ImageView iv;
        TextView tv;

        iv=v.findViewById(R.id.imageView);
        tv=v.findViewById(R.id.itemtitle);

        Picasso.with(ctx).load(memeItems.get(position).getImgUrl()).fit().centerInside().into(iv);
        tv.setText(memeItems.get(position).getMsg());
        container.addView(v,0);
        return v;
    }
}
