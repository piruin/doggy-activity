package me.piruin.doggyactivity;

interface DoggyCallback {

  void onScreenOn();

  void onScreenOff();

  void onUserLeave(boolean systemInterrupt);

  void onSystemInterrupt();

  void onUserBack();
}
