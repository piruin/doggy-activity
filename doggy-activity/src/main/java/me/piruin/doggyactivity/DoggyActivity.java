package me.piruin.doggyactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class DoggyActivity extends AppCompatActivity implements DoggyCallback {

  public static final String TAG = "Doggy-Activity";

  protected static final String USER_LEAVE = "isUserLeave";

  private boolean screenReceiverRegisted = false;
  private ScreenActionReceiver screenReceiver;
  private Bundle saveState;
  private Doggy doggy = new Doggy(this);

  @Override protected void onCreate(Bundle savedInstanceState) {
    //Log.d(TAG, "OnCreate()");
    super.onCreate(savedInstanceState);
    if (savedInstanceState != null) {
      doggy.alone = savedInstanceState.getBoolean(USER_LEAVE);
    }
  }

  @Override protected void onStart() {
    //Log.d(TAG, "Onstart()");
    if (!screenReceiverRegisted) {
      screenReceiverRegisted = true;
      screenReceiver = new ScreenActionReceiver(doggy);
      registerReceiver(screenReceiver, screenReceiver.filter());
    }
    super.onStart();
  }

  @Override protected void onResume() {
    //Log.d(TAG, "OnResume()");
    doggy.refresh();
    super.onResume();
  }

  @Override public void onWindowFocusChanged(boolean hasFocus) {
    //Log.d(TAG, "onWindowFocusChanged()");
    if (hasFocus && doggy.alone) {
      doggy.theyBack();
    }
    super.onWindowFocusChanged(hasFocus);
  }

  @Override protected void onUserLeaveHint() {
    Log.d(TAG, "OnUserLeaveHint()");
    doggy.someoneRingTheBell = true;
    super.onUserLeaveHint();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    //Log.d(TAG, "onSaveInstanceState()");

    super.onSaveInstanceState(outState);
    saveState = outState;
  }

  @Override public CharSequence onCreateDescription() {
    Log.d(TAG, "onCreateDescription()");
    doggy.lockingTheDoor = true;
    return super.onCreateDescription();
  }

  @Override protected void onStop() {
    //Log.d(TAG, "OnStop()");

    if (doggy.isTheyLeaving()) {
      /*
       * User Leave this activity by somehow suck as Press Home Button,
			 * Press Notification in Notification bar Or Phone incoming
			 */
      if (saveState != null)
        saveState.putBoolean(USER_LEAVE, true);
      doggy.userLeave();
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
    super.onDestroy();
  }

  @Override public void startActivity(Intent intent) {
    doggy.gotoNextRoom = true;
    super.startActivity(intent);
  }

  @Override public void startActivityForResult(Intent intent, int requestCode) {
    doggy.gotoNextRoom = true;
    super.startActivityForResult(intent, requestCode);
  }

  @Override public void startActivityFromChild(
    Activity child, Intent intent, int requestCode)
  {
    doggy.gotoNextRoom = true;
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
}
