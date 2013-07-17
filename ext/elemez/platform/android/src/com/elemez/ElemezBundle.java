package com.elemez;

import android.os.*;
import android.content.*;

public class ElemezBundle {
	public static Bundle raiseDisruption(Long timestamp, String sender, String source, Boolean userInitiated) {
        Bundle bundle = new Bundle();
        // bundle.putString(ElemezConstants.APIField.Operation, ElemezConstants.Operation.SessionBegin);
        // bundle.putString(ElemezConstants.APIField.SessionId, sessionId);
        // bundle.putString(ElemezConstants.APIField.ApplicationId, applicationId);
        // bundle.putString(ElemezConstants.APIField.Version, version);
        // bundle.putString(ElemezConstants.APIField.Instance, instance);
        // bundle.putString(ElemezConstants.APIField.Other, other);
        return bundle;
    }    
}