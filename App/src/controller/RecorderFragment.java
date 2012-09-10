package controller;

import service.RecorderService;
import birdpro.app.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecorderFragment extends Fragment {
	
	private static final String LOG_TAG = "RecorderFragment";
	
	private boolean recording;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		recording = false;
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.recorder_view, container, false);
		View recButton = view.findViewById(R.id.recButton);
		recButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				recButtonPressed(v);
			}
		});
		return view;
	}
	
	private void recButtonPressed(View recButton){
		if(!recording){
			recButton.setBackgroundResource(R.drawable.rec_stop);
			
			recording = true;
			Log.i(LOG_TAG, "Start recorder");
			RecorderService.getInstance().startRecording();
		}
		else{
			recButton.setBackgroundResource(R.drawable.rec_start);
			
			recording = false;
			Log.i(LOG_TAG, "Stop recorder");
			RecorderService.getInstance().stopRecording();
		}
	}
}
