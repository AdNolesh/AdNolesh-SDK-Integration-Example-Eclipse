package com.your.app.p4ck4ge.name;

import java.util.Calendar;

import com.nolesh.ads.AdNoleshSDK;
import com.nolesh.ads.NotificationScheduler;
import com.nolesh.ads.VideoStatusEvents;
import com.your.app.p4ck4ge.name.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class MainTab extends Fragment {
		
	@Override
	public void onDetach() {		
		super.onDetach();		
	}
			
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_main, container, false);
				
		Button button = (Button) view.findViewById(R.id.notification);		
		button.setOnClickListener(new OnClickListener() {				
			
			@Override
			public void onClick(View v) {				
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				long interval = 30 * 1000;
				AdNoleshSDK.setNotification( 
						//calendar.get(Calendar.HOUR_OF_DAY), //current hour 
						//calendar.get(Calendar.MINUTE)+1, //current minute + 1
						12, 25,
						NotificationScheduler.INTERVAL_1_DAY, false,
						R.drawable.ic_statusbar);
				
			}
		});
		
		button = (Button) view.findViewById(R.id.offerwall);		
		button.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				AdNoleshSDK.showOfferwall();
//				AdNoleshSDK.cancelNotification();
			}
		});
		
		button = (Button) view.findViewById(R.id.hiddenOfferwall);		
		button.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				AdNoleshSDK.hiddenOfferwallSetState(true, true);
			}
		});
		
		button = (Button) view.findViewById(R.id.preferences);		
		button.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {				
				Intent intent = new Intent(getContext(), WallpaperSettings.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
		
		button = (Button) view.findViewById(R.id.interstitial);			
		button.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {	
				//Do not forget to enable Interstitials.
				//By default, they are disabled.
				AdNoleshSDK.showInterstitial();
			}
		});
		
		button = (Button) view.findViewById(R.id.video);			
		button.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				Toast.makeText(getContext(), "Start downloading video", Toast.LENGTH_LONG).show();
				//Use the following code if you are going to use 
				//persistent video request mode (persistentVideoRequest = true)				
//				if(!AdNoleshSDK.showVideo()){
//					Toast.makeText(getContext(), "Video is not ready!", Toast.LENGTH_LONG).show();
//				}
					
				//In case of persistentVideoRequest = true the event `onReady` 
				//will not be triggered to prevent an endless loop of video playback.				
				//So, if persistentVideoRequest = false you should use the code below				
				AdNoleshSDK.requestVideo(new VideoStatusEvents() {			
					@Override
					public void onReady() {
						//You need to call Toast.makeText() (and most other functions dealing with the UI) from the UI thread:
						MainTab.this.getActivity().runOnUiThread(new Runnable() {
							  public void run() {
								  Toast.makeText(getContext(), "Downloading of video is completed!", Toast.LENGTH_LONG).show();								  
							  }
						});
						AdNoleshSDK.showVideo();
					}
					@Override
					public void onFail(String error) {
						Log.e("ERROR", error);
					}
					@Override
					public void onInterrupted() {	
						Log.e("ERROR", "Video was interrupted");
					}
				});
			}
		});
		
		return view;
	}
	
	
}