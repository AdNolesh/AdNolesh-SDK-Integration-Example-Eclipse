package com.your.app.p4ck4ge.name;

import com.nolesh.ads.AdNoleshBanner;
import com.nolesh.ads.AdNoleshSDK;
import com.your.app.p4ck4ge.name.R;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;

public class BannerTab extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_banner, container, false);			
		
		final Button button = (Button) view.findViewById(R.id.addBannerButton);		
		button.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				button.setVisibility(View.GONE);
				RelativeLayout rl = (RelativeLayout)view.findViewById(R.id.bannerContainer);
				addBanner(rl);
			}
		});
		
		return view;
	}
	
	//Creates banner programmatically
	public void addBanner(ViewGroup parent) {		
		AdNoleshBanner banner = new AdNoleshBanner(getContext());
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
			    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		parent.addView(banner, params);			
	}
}