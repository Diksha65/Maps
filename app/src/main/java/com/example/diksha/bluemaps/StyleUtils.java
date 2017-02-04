package com.example.diksha.bluemaps;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MapStyleOptions;

/**
 * Created by diksha on 4/2/17.
 */

public class StyleUtils {

    public static int getIconResourceID(int i){
        int iconResourceID = 0;
        if(i<10) //ME
            iconResourceID = R.drawable.ic_my_marker;
        else if(i>=10 && i<35) //My Team Member
            iconResourceID = R.drawable.ic_team_member;
        else if(i>=35 && i<60) //My Team Base
            iconResourceID = R.drawable.ic_team_base;
        else if(i>=60 && i<85) //My Enemy Member
            iconResourceID = R.drawable.ic_enemy_member;
        else if(i>=85) //My Enemy base
            iconResourceID = R.drawable.ic_enemy_base;
        return iconResourceID;
    }

    public static void setGoogleMapStyle(Activity activity,
                                         GoogleMap googleMap,
                                         int        resourceID){
        try{
            googleMap.getUiSettings().setMapToolbarEnabled(false);

            googleMap.setMaxZoomPreference(googleMap.getMaxZoomLevel());
            googleMap.setBuildingsEnabled(true);

            googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            activity,
                            resourceID
                    )
            );

        } catch (Resources.NotFoundException e){

            Log.e("STYLE", "NOT PRESENT");
        }
    }
}
