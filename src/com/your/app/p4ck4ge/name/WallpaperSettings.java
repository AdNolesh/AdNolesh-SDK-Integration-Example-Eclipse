package com.your.app.p4ck4ge.name;

import com.nolesh.ads.AdNoleshSDK;
import com.your.app.p4ck4ge.name.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;


public class WallpaperSettings extends PreferenceActivity {
		
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
				
//		setContentView(R.layout.ads_preference);
		
		//You should initialize the SDK in real app.
		//In this example we don't do this, because we did it earlier in the MainActivity.
		//AdNoleshSDK.initialize(this, false, false);				
	}
	
}
