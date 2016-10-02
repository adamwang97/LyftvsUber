package adam.doggo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterMap(View v) {

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void readAbout(View v) {

        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
