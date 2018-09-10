package com.your.app.p4ck4ge.name;

import com.nolesh.ads.AdNoleshSDK;
import com.your.app.p4ck4ge.name.R;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;


public class WallpaperSettings extends PreferenceActivity {
		
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
				
		//setContentView(R.layout.ads_preference);
		
		//You should initialize SDK in real app.
		//In this example we don't do this, because we did it earlier in MainActivity.
		//AdNoleshSDK.initialize(this, false, false);				
	}
	
	@Override
	public void onResume() {		
		//We have to keep track to instance of the current activity.
		//You can omit it if you are going to use SDK in single activity.
		//In this example we have to call "onResume" function to set current activity,  
		//because we use the SDK in several activities (WallpaperSettings & MainActivity).		
		AdNoleshSDK.onResume(this);	
		super.onResume();
	}
	
	@Override
	protected void onPause() {		
		super.onPause();			
	}
}
