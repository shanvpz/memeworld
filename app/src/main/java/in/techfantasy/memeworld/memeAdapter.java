package in.techfantasy.memeworld;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class memeAdapter extends RecyclerView.Adapter<memeAdapter.memeViewHolder> {

    private Context mContext;
    private ArrayList<memeItem> mArraylist;

    public memeAdapter(Context mContext, ArrayList<memeItem> mArraylist) {
        this.mContext = mContext;
        this.mArraylist = mArraylist;
    }

    @NonNull
    @Override
    public memeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(mContext).inflate(R.layout.memeitem,viewGroup,false);
        return new memeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull memeViewHolder memeViewHolder, int i) {
        memeItem currentItem=mArraylist.get(i);
        String imgUrl=currentItem.getImgUrl();
        String msg=currentItem.getMsg();

        memeViewHolder.mTextview.setText(msg);
        Picasso.with(mContext).load(imgUrl).fit().centerInside().into(memeViewHolder.mImageview);
    }

    @Override
    public int getItemCount() {
        return mArraylist.size();
    }


    public class memeViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageview;
        public TextView mTextview;
        public memeViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageview=itemView.findViewById(R.id.memeImage);
            mTextview=itemView.findViewById(R.id.mememessage);
        }
    }
}
