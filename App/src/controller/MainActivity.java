package controller;

import service.DBService;
import birdpro.app.R;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends FragmentActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        DBService.init(this);
        
        FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		RecorderFragment recorderFragment = new RecorderFragment();
		fragmentTransaction.add(R.id.fragment_container, recorderFragment);
		
		TrackListFragment trackListFragment = new TrackListFragment();
		fragmentTransaction.add(R.id.fragment_container, trackListFragment);
		
		fragmentTransaction.commit();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
