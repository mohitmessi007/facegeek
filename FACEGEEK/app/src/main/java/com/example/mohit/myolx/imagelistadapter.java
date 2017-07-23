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
public class imagelistadapter extends ArrayAdapter<ImageUpload> {

    private Activity context;
    private int resource;
    private DatabaseReference mDatabase;
    private List<ImageUpload> listimage;
    private String uri;
    Boolean doRefresh = true;
    private String email;
    private String ema;

    public imagelistadapter(@Nullable Activity context, @LayoutRes int resource, List<ImageUpload> objects) {
        super(context, resource, objects);
        this.context =context;
        this.resource = resource;
        listimage = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertview, @NonNull ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();

        @SuppressLint("ViewHolder") View v = inflater.inflate(resource, null);
        TextView tvname = (TextView) v.findViewById(R.id.imgname);
        ImageView img = (ImageView) v.findViewById(R.id.imgview);
        TextView t = (TextView) v.findViewById(R.id.user);
        tvname.setText(listimage.get(position).getName());
        t.setText(listimage.get(position).getLoader());
        if((listimage.get(position).getUrl()).equals("null")){
            img.setVisibility(View.GONE);
        }
        Glide.with(context).load(listimage.get(position).getUrl()).into(img);

        return v;
    }
}
