package me.piruin.doggyactivity.sample;

import android.os.Bundle;
import android.widget.Toast;
import me.piruin.doggyactivity.DoggyActivity;

public class SampleActivity extends DoggyActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample);
  }

  @Override public void onScreenOff() {
    super.onScreenOff();
    Toast.makeText(this, "Who turn of the light", Toast.LENGTH_SHORT).show();
  }

  @Override public void onScreenOn() {
    super.onScreenOn();
    Toast.makeText(this, "Screen on Again", Toast.LENGTH_SHORT).show();
  }

  @Override public void onUserLeave(boolean systemInterrupt) {
    super.onUserLeave(systemInterrupt);
    Toast.makeText(this, "User Leave", Toast.LENGTH_SHORT).show();

  }

  @Override public void onUserBack() {
    super.onUserBack();
    Toast.makeText(this, "User is Back, ha ha", Toast.LENGTH_SHORT).show();
  }

}
