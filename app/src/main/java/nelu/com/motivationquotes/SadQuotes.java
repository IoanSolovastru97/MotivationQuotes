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
    private List<String> images;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sad_quotes);

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        images = new ArrayList();


        adapter = new MyAdapter(images,SadQuotes.this);
        recyclerView.setAdapter(adapter);
    }


}
