package posterity.com.posterity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class PosterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PosterHelper posterHelper = new PosterHelper(getApplicationContext());
        List<List<String>> posterData = posterHelper.queryAll();
        int eventNum = getIntent().getIntExtra("eventnum", 0);
        if (!posterData.isEmpty()) {
            List<String> posterRow = posterData.get(eventNum);
            File imageFile = new File(posterRow.get(0));
            if (imageFile.exists()) {
                ImageView imageView = (ImageView) findViewById(R.id.poster);
                Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                imageView.setImageBitmap(bitmap);
            }
            CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            collapsingToolbar.setTitle(posterRow.get(1));
            TextView eventTime = (TextView) findViewById(R.id.time_text);
            eventTime.setText(posterRow.get(3));
            TextView eventDate = (TextView) findViewById(R.id.date_text);
            eventDate.setText(posterRow.get(2));
            TextView eventLoc = (TextView) findViewById(R.id.location_text);
            eventLoc.setText(posterRow.get(4));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_poster, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
