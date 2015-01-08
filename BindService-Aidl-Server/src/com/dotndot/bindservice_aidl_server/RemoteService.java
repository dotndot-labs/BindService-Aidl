package com.dotndot.bindservice_aidl_server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class RemoteService extends Service {

	@Override
	public void onCreate() {
		Log.e("RemoteService", "onCreate");
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.e("RemoteService", "onBind");
		return mRemoteBinder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.e("RemoteService", "onUnbind");
		return super.onUnbind(intent);
	}

	private final IRemoteService.Stub mRemoteBinder = new IRemoteService.Stub() {

		@Override
		public boolean isConnect() throws RemoteException {
			Log.e("RemoteService", "isConnect");
			return true;
		}

		@Override
		public int disconnect() throws RemoteException {
			Log.e("RemoteService", "disconnect");
			return -1;
		}

		@Override
		public int connect() throws RemoteException {
			Log.e("RemoteService", "connect");
			return 1;
		}
	};
}
