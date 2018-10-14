package nelu.com.motivationquotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mainButton = findViewById(R.id.main);

        final TextView text = findViewById(R.id.textMain);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGym = new Intent("app.java.nelu.com.motivationquotes.GymActivity");
                startActivity(openGym);
                text.setText("Button clicked");
            }
        });

    }
}
