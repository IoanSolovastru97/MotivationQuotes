package nelu.com.motivationquotes.utilis;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import nelu.com.motivationquotes.MyAdapter;

public class ImageProcessesing {
    private static DatabaseReference mDatabaseRef;
    private static MyAdapter adapter;
    private static List<String> images = new ArrayList<>();

    private ImageProcessesing() {
    }

    /**
     * Gets the images which are load in firebase
     * @param context
     * @param recyclerView
     * @param folder the folder where the images will be loaded, eg. Sad Images, Business Images, etc.
     * @return
     */
    public static List getListImage(final Context context, final RecyclerView recyclerView,String folder){
        final List<String> list = new ArrayList();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(folder);

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String upload = postSnapshot.getValue(String.class);
                    list.add(upload);
                }

                adapter = new MyAdapter(list,context);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return list;
    }
}
