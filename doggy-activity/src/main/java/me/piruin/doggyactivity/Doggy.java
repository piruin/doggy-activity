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

import android.support.annotation.NonNull;

class Doggy {
  boolean alone = false;
  boolean leaveWithPlan = false;
  boolean gotoNextRoom = false;
  boolean lockingTheDoor = false;
  private DoggyCallback doggyCallback;

  Doggy(@NonNull DoggyCallback doggyCallback) {
    this.doggyCallback = doggyCallback;
  }

  void refresh() {
    gotoNextRoom = false;
    leaveWithPlan = false;
    lockingTheDoor = false;
  }

  void screenOff() {
    doggyCallback.onScreenOff();
    userLeave();
  }

  void userLeave() {
    alone = true;
    boolean systemInterrupt = !leaveWithPlan;
     /*
      * If user leave activity by Call-in or Something that was
      * give change to user to choose. It is systemInterrupt
      */
    doggyCallback.onUserLeave(systemInterrupt);
  }

  void screenOn() {
    doggyCallback.onScreenOn();
  }

  void theyBack() {
    doggyCallback.onUserBack();
    alone = false;
  }

  boolean isTheyLeaving() {
    return !gotoNextRoom && lockingTheDoor;
  }
}
