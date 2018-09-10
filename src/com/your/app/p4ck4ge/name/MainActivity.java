package com.your.app.p4ck4ge.name;

import com.nolesh.ads.AdNoleshBanner;
import com.nolesh.ads.AdNoleshSDK;
import com.nolesh.ads.BannerPreference;
import com.nolesh.ads.InitSDKEvents;
import com.nolesh.ads.NotificationScheduler;
import com.nolesh.ads.OfferwallAdapter;
import com.your.app.p4ck4ge.name.R;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	
	ViewPager Tab;
	TabPagerAdapter TabAdapter;
	ActionBar actionBar;	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_main);

		//Initialize SDK 
		AdNoleshSDK.initialize(this, "YOUR_API_KEY", true, new InitSDKEvents() {		
			@Override
			public void onSuccess() {
				MainActivity.this.runOnUiThread(new Runnable() {
					  public void run() {
						  Toast.makeText(MainActivity.this, "AdNoleshSDK is initialized", Toast.LENGTH_LONG).show();						  
					  }
				});
			}			
			@Override
			public void onError(final String errorMsg) {
				MainActivity.this.runOnUiThread(new Runnable() {
					  public void run() {						 
						  Toast.makeText(MainActivity.this, errorMsg+"", Toast.LENGTH_LONG).show();
					  }
				});
			}
		});
		
		AdNoleshBanner.setPersistentMode(true);
		BannerPreference.setPersistentMode(true);
//		AdNoleshSDK.setPersistentVideoRequestMode(true);
		AdNoleshSDK.enableInterstitials(true);
				
		OfferwallAdapter.setTitleColor("#ff9900");
		OfferwallAdapter.setDescriptionColor("#77ccff");
		OfferwallAdapter.setBackgroundColor("#007a95");
		
		//Customizes ad units in the offerwall and wrapped ListAdapter.
//		OfferwallAdapter.customizeAdUnits(R.layout.ad_listview_item, R.id.adIcon, R.id.adTitle, R.id.adDesc);
		
		NotificationScheduler.setTitle(this, "My Awesome App");
		NotificationScheduler.setContentText(this, "Pull it down to see the content");
		
		//Embed hidden offerwall
		//AdNoleshSDK.embedHiddenOfferwall(this, "#55c5f5", "popular apps"); 
		AdNoleshSDK.embedHiddenOfferwall(this, false, "#55c5f5", "popular apps");
		
				
		TabAdapter = new TabPagerAdapter(getSupportFragmentManager());

		Tab = (ViewPager) findViewById(R.id.pager);
		Tab.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar = getActionBar();
				actionBar.setSelectedNavigationItem(position);
			}
		});
		Tab.setAdapter(TabAdapter);

		actionBar = getActionBar();
		actionBar.setDisplayOptions(Window.FEATURE_NO_TITLE);
		// Enable Tabs on Action Bar
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.TabListener tabListener = new ActionBar.TabListener() {

			@Override
			public void onTabReselected(android.app.ActionBar.Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
				
				//close hidden offerwall if it's opened
				AdNoleshSDK.hiddenOfferwallSetState(false, true);
				
				Tab.setCurrentItem(tab.getPosition());				
			}

			@Override
			public void onTabUnselected(android.app.ActionBar.Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
			}
		}; 
		// Add New Tab
		actionBar.addTab(actionBar.newTab().setText(getBaseContext().getResources().getString(R.string.banner_tab)).setTabListener(tabListener));		
		actionBar.addTab(actionBar.newTab().setText(getBaseContext().getResources().getString(R.string.main_tab)).setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText(getBaseContext().getResources().getString(R.string.offerwall_tab)).setTabListener(tabListener));
		
		actionBar.setSelectedNavigationItem(1);		
		
	}

	public class TabPagerAdapter extends FragmentStatePagerAdapter {
	    public TabPagerAdapter(FragmentManager fm) {
	        super(fm);
	        // TODO Auto-generated constructor stub
	    }
	 
	    @Override
	    public Fragment getItem(int i) {
	        switch (i) {
	        case 0:	           
	            return new BannerTab();
//	        	return new OfferwallTab();
	        case 1:	           
	            return new MainTab();
	        case 2:	           
	            return new OfferwallTab();
	        }
	        return null;
	 
	    }
	 
	    @Override
	    public int getCount() {	       
	        return 3; //Number of Tabs
	    }	 
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
	public void finish() {			
		super.finish();		
		//Interrupts video download, stops the SDK initialization and unregisters broadcast receivers.
		//This method must be called in the "finish" method in your main activity.
		AdNoleshSDK.finish();
	}
	
}
