package com.example.mohit.myolx;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import java.util.List;
public class chatlistadapter extends ArrayAdapter<chatbbox> {
    private int resource;
    private Activity context;
    private DatabaseReference mDatabase;
    private List<chatbbox> listimage;
    private String uri;
    Boolean doRefresh = true;
    private String email;
    private String ema;
    public chatlistadapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<chatbbox> objects) {
        super(context, resource, objects);
        this.context =context;
        this.resource = resource;
        listimage = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertview, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View v = inflater.inflate(resource, null);

        ImageView abc = (ImageView) v.findViewById(R.id.chatimg);
        TextView bcd = (TextView) v.findViewById(R.id.chatuser);

        bcd.setText(listimage.get(position).getEname());
//        if(listimage.get(position).getProfurl().equals("null"))
//        {

//        }
        Glide.with(context).load(listimage.get(position).getProfurl()).into(abc);
        return v;
    }
}