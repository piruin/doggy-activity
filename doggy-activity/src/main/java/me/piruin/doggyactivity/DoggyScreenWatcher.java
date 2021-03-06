/*
 * Copyright (c) 2016 Piruin Panichphol
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.piruin.doggyactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;

class DoggyScreenWatcher extends BroadcastReceiver {

  private Doggy doggy;

  public DoggyScreenWatcher(Doggy doggy) {
    this.doggy = doggy;
  }

  @NonNull public static IntentFilter filter() {
    IntentFilter screenActionFilter = new IntentFilter();
    screenActionFilter.addAction(Intent.ACTION_SCREEN_ON);
    screenActionFilter.addAction(Intent.ACTION_SCREEN_OFF);
    return screenActionFilter;
  }

  @Override public void onReceive(Context context, Intent intent) {
    if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
      doggy.screenOff();
    else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
      doggy.screenOn();
  }
}
