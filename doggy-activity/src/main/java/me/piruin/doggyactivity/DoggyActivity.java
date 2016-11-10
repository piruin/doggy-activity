package me.piruin.doggyactivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DoggyActivity extends AppCompatActivity implements DoggyCallback {

  protected static final String USER_LEAVE = "isUserLeave";

  protected boolean userLeave = false;
  protected boolean userLeaveByThemself = false;
  protected boolean startActivity = false;
  protected boolean createDescription = false;
  protected boolean screenReceiverRegisted = false;
  protected BroadcastReceiver screenReceiver;
  protected IntentFilter screenActionFilter;
  protected DoggyCallback doggyCallback = this;
  protected Bundle saveState;

  @Override protected void onCreate(Bundle savedInstanceState) {
    //Log.d(TAG, "OnCreate()");

    super.onCreate(savedInstanceState);

    if (savedInstanceState != null) {
      userLeave = savedInstanceState.getBoolean(USER_LEAVE);
    }
  }

  @Override protected void onStart() {
    //Log.d(TAG, "Onstart()");

    if (!screenReceiverRegisted) {
      screenReceiverRegisted = true;
      IntentFilter screenActionFilter = new IntentFilter();
      screenActionFilter.addAction(Intent.ACTION_SCREEN_ON);
      screenActionFilter.addAction(Intent.ACTION_SCREEN_OFF);
      screenReceiver = new ScreenActionReceiver();
      registerReceiver(screenReceiver, screenActionFilter);
    }
    super.onStart();
  }

  @Override protected void onResume() {
    //Log.d(TAG, "OnResume()");

    startActivity = false;
    userLeaveByThemself = false;
    createDescription = false;

    super.onResume();
  }

  @Override public void onWindowFocusChanged(boolean hasFocus) {
    //Log.d(TAG, "onWindowFocusChanged()");

    if (hasFocus && userLeave && (doggyCallback != null)) {
      doggyCallback.onUserBack();
      userLeave = false;
    }
    super.onWindowFocusChanged(hasFocus);
  }

  @Override protected void onUserLeaveHint() {
    //Log.d(TAG, "OnUserLeaveHint()");

    userLeaveByThemself = true;
    super.onUserLeaveHint();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    //Log.d(TAG, "onSaveInstanceState()");

    //Copy outState's Pointer for Save Instance State in other method
    super.onSaveInstanceState(outState);
    saveState = outState;
  }

  @Override public CharSequence onCreateDescription() {
    //Log.d(TAG, "onCreateDescription()");

    createDescription = true;
    return super.onCreateDescription();
  }

  @Override protected void onStop() {
    //Log.d(TAG, "OnStop()");

    if (!startActivity && createDescription) {
            /*
       * User Leave this activity by somehow suck as Press Home Button,
			 * Press Notification in Notification bar Or Phone incoming
			 */
      userLeave = true;
      if (saveState != null)
        saveState.putBoolean(USER_LEAVE, userLeave);

      if (doggyCallback != null) {
        boolean systemInterrupt = !userLeaveByThemself;
        doggyCallback.onUserLeave(systemInterrupt);
        /*
				 * If user leave this activity by Call in or Something that was
				 * not choice of user then call onSystemInterrupt() for more
				 * flexible
				 */
        if (systemInterrupt)
          doggyCallback.onSystemInterrupt();
      }
    } else {

			/*
			 * If start other Activity we must unregister ScreenReceiver before;
			 */
      unregisterReceiver(screenReceiver);
      screenReceiverRegisted = false;
      //Log.w(TAG, "UTA : Unregistered ScreenReceiver!");
    }

    super.onStop();
  }

  @Override protected void onDestroy() {
    //Log.d(TAG, "OnDestroy()");
		/*
		 * if ScreenReceiver not unregister yet, do it here.
		 */
    if (screenReceiverRegisted)
      unregisterReceiver(screenReceiver);
		/*
		 * Avoid cause of memory leak
		 */
    saveState = null;
    screenReceiver = null;
    screenActionFilter = null;
    doggyCallback = null;
    super.onDestroy();
  }

  @Override public void startActivity(Intent intent) {
    startActivity = true;
    super.startActivity(intent);
  }

  @Override public void startActivityForResult(Intent intent, int requestCode) {
    startActivity = true;
    super.startActivityForResult(intent, requestCode);
  }

  @Override public void startActivityFromChild(
    Activity child, Intent intent, int requestCode)
  {
    startActivity = true;
    super.startActivityFromChild(child, intent, requestCode);
  }

  @Override public void onScreenOn() {

  }

  @Override public void onScreenOff() {

  }

  @Override public void onUserLeave(boolean systemInterrupt) {

  }

  @Override public void onSystemInterrupt() {

  }

  @Override public void onUserBack() {

  }

  private class ScreenActionReceiver extends BroadcastReceiver {

    @Override public void onReceive(Context context, Intent intent) {
      if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
        userLeave = true;
        if (doggyCallback != null)
          doggyCallback.onScreenOff();
      } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
        if (doggyCallback != null)
          doggyCallback.onScreenOn();
      }
    }
  }
}
