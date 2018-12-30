package nelu.com.motivationquotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ZoomImage extends MainActivity {
    private static ImageView imageView;
    private static final String TAG = "ZoomImage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);
        getIncomingIntent();
    }

    public void setImageView(String imageURL){
        imageView = findViewById(R.id.zoom_image);
        Picasso.with(this)
                .load(imageURL)
                .fit()
                .placeholder(R.drawable.loading)
                .centerCrop()
                .into(imageView);
    }

    public void getIncomingIntent(){
        if(getIntent().hasExtra("imageURL")){
            String image = getIntent().getStringExtra("imageURL");
            setImageView(image);
        }
    }
}
