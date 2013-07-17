package com.elemez;

import android.os.*;
import android.content.*;

import com.rhomobile.rhodes.*;

public class ElemezServiceConnection implements ServiceConnection {
    
    private boolean bound;

    private Messenger service;

    private Bundle[] initialMessages;

    private ElemezServiceConnection(Bundle[] initialMessages) {
        Logger.D("ElemezServiceConnection", "construction");
        this.service = null;
        this.bound = false;
        this.initialMessages = initialMessages;
        this.bind();
    }

    public static ElemezServiceConnection initialSend(Bundle[] initialMessages) {
        Logger.D("ElemezServiceConnection", "initialSend");
        return new ElemezServiceConnection(initialMessages);
    } 

    public void close() {
        Logger.D("ElemezServiceConnection", "close");
        this.unbind();
    }

    public void onServiceConnected(ComponentName className, IBinder binder) {
        Logger.D("ElemezServiceConnection", "onServiceConnected");
        this.service = new Messenger(binder);
        this.bound = true;
        
        for(Bundle b: initialMessages) {
            this.send(b);    
        }
        
        this.initialMessages = new Bundle[0];
    }

    public void onServiceDisconnected(ComponentName className) {
        Logger.D("ElemezServiceConnection", "onServiceDisconnected");
        this.service = null;
        this.bound = false;
    }

    public void send(Bundle bundle) {
        Logger.D("ElemezServiceConnection", "send");

        if(!this.bound) {
            Logger.D("ElemezServiceConnection", "send - not bound");
            return;
        }

        if(bundle == null) {
            Logger.D("ElemezServiceConnection", "send - null bundle");
            return;
        }

        Message msg = Message.obtain();
        msg.setData(bundle);
        
        try {
            this.service.send(msg);
        } catch (RemoteException e) {
            Logger.E("ElemezServiceConnection:send", e.toString());
        }
    }    

    private void bind() {
        Logger.D("ElemezServiceConnection", "bind");
        Context ctx = RhodesService.getContext();
        Intent intent = new Intent("com.b2msolutions.elemez.android.services.ApiService");

        ctx.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    private void unbind() {
        Logger.D("ElemezServiceConnection", "unbind");
        Context ctx = RhodesService.getContext();
        ctx.unbindService(this);
    }
}
