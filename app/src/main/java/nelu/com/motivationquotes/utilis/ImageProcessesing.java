package nelu.com.motivationquotes.utilis;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ImageProcessesing {
    private static DatabaseReference mDatabaseRef;

    public static List<String> images = new ArrayList<>();
    private static final String TAG = "ImageProcessesing";

    private ImageProcessesing() {
    }

    /**
     * Gets the images which are load in firebase
     *
     * @param context
     * @param recyclerView
     * @param folder  the folder where the images will be loaded, eg. Sad Images, Business Images, etc.
     * @return
     */
    public static List getListImage(final Context context, final RecyclerView recyclerView, String folder) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(folder);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String upload = postSnapshot.getValue(String.class);
                    images.add(upload);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return images;
    }

    /**
     * Knowing each folder, we will have a list with all quotes.
     * This method is used to generate in MainActivity the random quote.
     */
    public static List getAllImages() {

        images.addAll(null);
        return images;
    }
}
