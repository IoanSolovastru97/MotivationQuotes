package nelu.com.motivationquotes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SadQuotes extends MainActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Integer> images;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sad_quotes);

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        images = new ArrayList();
        images.add(R.drawable.quote1);
        images.add(R.drawable.quote2);
        images.add(R.drawable.quote3);
        images.add(R.drawable.quote4);
        images.add(R.drawable.quote5);

        adapter = new MyAdapter(images,this);
        recyclerView.setAdapter(adapter);
    }


}
