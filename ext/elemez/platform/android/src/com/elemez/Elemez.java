package com.elemez;

import android.os.*;
import android.content.*;
import android.content.pm.*;

import com.rhomobile.rhodes.*;
import com.elemez.*;

public class Elemez {

   	public static void raiseDisruption(Long timestamp, String sender, String source, Boolean userInitiated) {
        Logger.D("Elemez", "raiseDisruption");        

        Intent intent = new Intent("elemez.intent.action.ACTION_DISRUPTED");
        intent.putExtra("elemez.intent.extra.TIMESTAMP", 1);
        intent.putExtra("elemez.intent.extra.SENDER", "SENDER");
        intent.putExtra("elemez.intent.extra.SOURCE", "source");
        intent.putExtra("elemez.intent.extra.USER_INITIATED", true);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        
        Context context = RhodesService.getContext();
        context.sendBroadcast(intent);
	}	
}