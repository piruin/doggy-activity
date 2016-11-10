package me.piruin.doggyactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;

class ScreenActionReceiver extends BroadcastReceiver {

  private Doggy doggy;

  public ScreenActionReceiver(Doggy doggy) {
    this.doggy = doggy;
  }

  @Override public void onReceive(Context context, Intent intent) {
    if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
      doggy.screenOff();
    else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
      doggy.screenOn();
  }

  @NonNull public IntentFilter filter() {
    IntentFilter screenActionFilter = new IntentFilter();
    screenActionFilter.addAction(Intent.ACTION_SCREEN_ON);
    screenActionFilter.addAction(Intent.ACTION_SCREEN_OFF);
    return screenActionFilter;
  }
}
