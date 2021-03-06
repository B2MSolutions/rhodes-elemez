package com.elemez;

import android.os.*;
import android.content.*;
import android.content.pm.*;

import com.rhomobile.rhodes.*;
import com.elemez.*;

public class Elemez {

   	public static void raiseDisruption(String sender, String source, boolean userInitiated) {
        Logger.D("Elemez", "raiseDisruption");        

        long timestamp = System.currentTimeMillis();
        Intent intent = new Intent("elemez.intent.action.ACTION_DISRUPTED");
       
        intent.putExtra("elemez.intent.extra.TIMESTAMP", timestamp);
        intent.putExtra("elemez.intent.extra.SENDER", sender);
        intent.putExtra("elemez.intent.extra.SOURCE", source);
        intent.putExtra("elemez.intent.extra.USER_INITIATED", userInitiated);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        
        Context context = RhodesService.getContext();
        context.sendBroadcast(intent);
	}	
}