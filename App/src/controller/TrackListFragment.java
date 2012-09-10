package controller;

import java.util.ArrayList;

import model.Model;
import model.Track;

import birdpro.app.R;
import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TrackListFragment extends Fragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ScrollView view = (ScrollView) inflater.inflate(R.layout.track_list_view, container, false);
		TableLayout trackList = (TableLayout) view.findViewById(R.id.trackList);
		view.setScrollbarFadingEnabled(true);
		view.setFadingEdgeLength(50);
		
		ArrayList<Track> tracks = Model.getInstance().getTracks();
		for(Track track : tracks){
			
		}
		
		for(int i=0; i<10; i++){
			TableRow row = new TableRow(getActivity());
			Button playButton = new Button(getActivity());
			playButton.setBackgroundResource(R.drawable.play);
			//playButton.setLayoutParams(new LayoutParams(48, 48));
			row.addView(playButton);
			TextView date = new TextView(getActivity());
			date.setText("04.09.2012");
			row.addView(date);
			trackList.addView(row);
		}
		
		return view;
	}
	
	private TableRow createViewForTrack(Track track){
		TableRow row = new TableRow(getActivity());
		
		return row;
	}
	
	/*
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		ArrayList<Long> tracks = Model.getInstance().getTracks();
		for(long track : tracks){
			fragmentTransaction.add(R.id.trackList, new TrackFragment(track));
		}
		fragmentTransaction.commit();
		
		super.onActivityCreated(savedInstanceState);
	}*/
}
