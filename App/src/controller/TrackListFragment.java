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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class TrackListFragment extends Fragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.track_list_view, container, false);
		TableLayout trackList = (TableLayout) view.findViewById(R.id.trackList);
		
		ArrayList<Track> tracks = Model.getInstance().getTracks();
		for(Track track : tracks){
			
		}
		
		for(int i=0; i<10; i++){
			TableRow row = new TableRow(getActivity());
			Button playButton = new Button(getActivity());
			row.addView(playButton);
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
