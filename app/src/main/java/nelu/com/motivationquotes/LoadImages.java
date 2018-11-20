package nelu.com.motivationquotes;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class LoadImages extends MainActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button chooseImageButton;
    private Button uploadButton;
    private ImageView imageView;
    private ProgressBar progressBar;
    private Uri imageUri;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private StorageTask mUploadTask;

    private DrawerLayout loadImagesDrawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_images);


        //Implementing navigation bar
        loadImagesDrawerLayout = findViewById(R.id.load_images_drawer_layout); //Main Activity layout
        toggle = new ActionBarDrawerToggle(this, loadImagesDrawerLayout, R.string.open, R.string.close);
        loadImagesDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.quotes_load_images);

        chooseImageButton = findViewById(R.id.button_choose_image);
        uploadButton = findViewById(R.id.button_upload);
        progressBar = findViewById(R.id.progress_bar);
        imageView = findViewById(R.id.load_image);
        // "quotes" is the folder which is created
        storageReference = FirebaseStorage.getInstance().getReference("quotes1");
        databaseReference = FirebaseDatabase.getInstance().getReference("quotes1");


        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(LoadImages.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        });
    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    /**
     * Used to get the request which is huge and that's why we use PICK_IMAGE_REQUEST
     * Gets the image and shows it.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {

            imageUri = data.getData();
            Picasso.with(this).load(imageUri).into(imageView);
        }
    }

    /**
     * Get the file extension of the image( jpg, png )
     */
    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    /**
     * The images has to have unique names, that why we add as name the time.
     */
    private void uploadFile() {
        if (imageUri != null) {
            StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
            mUploadTask = fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    //In order to see the progress bar, we introduce a handler(for delay,5 sec), because the upload is done really fast
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);
                        }
                    },500);

                    Toast.makeText(LoadImages.this,"Upload successful ",Toast.LENGTH_SHORT).show();
                    //To get the image we use taskSnapshot
//                    String image = taskSnapshot.getStorage().getDownloadUrl().toString();
                    String image = taskSnapshot.getDownloadUrl().toString();
                    //To upload the meta data on Cloud
                    //uploadId gets an unique ID and at that ID we put the image URL
                    String uploadId = databaseReference.push().getKey();
                    databaseReference.child(uploadId).setValue(image);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoadImages.this, "Faild to upload", Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressBar.setProgress((int) progress);
                }
            });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.quotesFun:
                startActivity(new Intent(LoadImages.this, SadActivity.class));
                break;
            case R.id.quotesGym:
                startActivity(new Intent(LoadImages.this, GymActivity.class));
                break;
            case R.id.about_us:
                startActivity(new Intent(LoadImages.this, AboutUsActivity.class));
                break;
            case R.id.rate_us:
                super.rateUs();
                break;
        }
        loadImagesDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
