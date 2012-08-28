package birdpro.app;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//Min kommentar er sterkere ennd din
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Testkommentar
    //Testkommentar 2
    //Testkommentar 3

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
