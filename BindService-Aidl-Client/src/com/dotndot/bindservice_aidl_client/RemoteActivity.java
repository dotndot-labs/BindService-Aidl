package com.dotndot.bindservice_aidl_client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dotndot.bindservice_aidl_server.IRemoteService;

public class RemoteActivity extends ActionBarActivity {

	private IRemoteService mRemoteBinder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remote);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.remote, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();
		registerService();
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterService();
	}

	private void registerService() {
		Log.e("RemoteClient", "registerService");
		Intent intent = new Intent();
		intent.setAction("com.dotndot.bindservice.REMOTE");
		bindService(intent, mServiceConn, BIND_AUTO_CREATE);
	}

	private void unregisterService() {
		Log.e("RemoteClient", "unregisterService");
		unbindService(mServiceConn);
	}

	private final ServiceConnection mServiceConn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.e("RemoteClient", "onServiceDisconnected");
			mRemoteBinder = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.e("RemoteClient", "onServiceConnected");
			mRemoteBinder = IRemoteService.Stub.asInterface(service);

			try {
				mRemoteBinder.connect();
				mRemoteBinder.disconnect();
				mRemoteBinder.isConnect();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	};

}
