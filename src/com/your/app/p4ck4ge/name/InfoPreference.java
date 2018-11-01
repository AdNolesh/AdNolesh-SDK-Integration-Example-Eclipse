package com.your.app.p4ck4ge.name;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class InfoPreference extends Preference{
    
    private final String TAG = getClass().getName();
             
    public InfoPreference(Context context, AttributeSet attrs) {
        super(context, attrs);       
    }

    public InfoPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);        
    }
       
    @Override
    protected View onCreateView(ViewGroup parent){
        
        RelativeLayout layout =  null;
        
        try {
            LayoutInflater mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (RelativeLayout)mInflater.inflate(R.layout.info_preference, parent, false);
        }
        catch(Exception e)
        {
            Log.e(TAG, "Error creating info preference", e);
        }
        return layout;        
    }     
}