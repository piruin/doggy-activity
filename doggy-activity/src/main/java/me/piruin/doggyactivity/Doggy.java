package me.piruin.doggyactivity;

import android.support.annotation.NonNull;

public class Doggy {

  protected boolean alone = false;
  protected boolean someoneRingTheBell = false;
  protected boolean gotoNextRoom = false;
  protected boolean lockingTheDoor = false;
  private DoggyCallback doggyCallback;

  public Doggy(@NonNull DoggyCallback doggyCallback) {
    this.doggyCallback = doggyCallback;
  }

  protected void refresh() {
    gotoNextRoom = false;
    someoneRingTheBell = false;
    lockingTheDoor = false;
  }

  public void screenOff() {
    alone = true;
    doggyCallback.onScreenOff();
  }

  public void screenOn() {
    doggyCallback.onScreenOn();
  }

  public void theyBack() {
    doggyCallback.onUserBack();
    alone = false;
  }

  public void userLeave() {
    alone = true;
    boolean systemInterrupt = someoneRingTheBell;
     /*
      * If user leave this activity by Call in or Something that was
      * not choice of user then call onSystemInterrupt() for more
      * flexible
      */
    if (systemInterrupt)
      doggyCallback.onSystemInterrupt();
    doggyCallback.onUserLeave(systemInterrupt);
  }

  boolean isTheyLeaving() {
    return !gotoNextRoom && lockingTheDoor;
  }
}
