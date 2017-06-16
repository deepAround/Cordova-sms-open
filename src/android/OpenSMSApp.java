package com.deepAround.opensmsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenSMSApp extends CordovaPlugin {

	private static final String LOG_TAG = "OpenSMSApp";
	private static volatile Activity currentActivity;
	/**
	 * Initialize cordova plugin kakaotalk
	 * @param cordova
	 * @param webView
	 */
	public void initialize(CordovaInterface cordova, CordovaWebView webView)
	{
		Log.v(LOG_TAG, "OpenSMSApp : initialize");
		super.initialize(cordova, webView);
		currentActivity = this.cordova.getActivity();
	}

	/**
	 * Execute plugin
	 * @param action
	 * @param options
	 * @param callbackContext
	 */
	public boolean execute(final String action, JSONArray options, final CallbackContext callbackContext) throws JSONException
	{
		Log.v(LOG_TAG, "OpenSMSApp : execute " + action);
		cordova.setActivityResultCallback(this);

		if (action.equals("share")) {

			try {
				this.share(options, callbackContext);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	private void share(JSONArray options, final CallbackContext callbackContext){
		try {
			final JSONObject parameters = options.getJSONObject(0);
			
			Intent retunIntent = new Intent(Intent.ACTION_VIEW);
			retunIntent.putExtra("sms_body", parameters.getString("sms_body"));
			retunIntent.setType("vnd.android-dir/mms-sms");
			this.cordova.getActivity().startActivity(retunIntent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
