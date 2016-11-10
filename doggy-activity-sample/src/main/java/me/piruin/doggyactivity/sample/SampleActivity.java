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

package me.piruin.doggyactivity.sample;

import android.os.Bundle;
import android.widget.Toast;
import java.util.Calendar;
import me.piruin.doggyactivity.DoggyActivity;

public class SampleActivity extends DoggyActivity {

  private long leaveTime;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample);
  }

  @Override public void onScreenOff() {
    Toast.makeText(this, "Who turn of the light", Toast.LENGTH_SHORT).show();
  }

  @Override public void onScreenOn() {
    Toast.makeText(this, "I can see again", Toast.LENGTH_SHORT).show();
  }

  @Override public void onUserLeave(boolean systemInterrupt) {
    leaveTime = Calendar.getInstance().getTimeInMillis();
    if (systemInterrupt)
      Toast.makeText(this, "Will you come back?", Toast.LENGTH_SHORT).show();
    else
      Toast.makeText(this, "Bye bye", Toast.LENGTH_SHORT).show();
  }

  @Override public void onUserBack() {
    leaveTime = Calendar.getInstance().getTimeInMillis()-leaveTime;
    if (leaveTime < 1000*5)
      Toast.makeText(this, "You back!!", Toast.LENGTH_SHORT).show();
    else
      Toast.makeText(this, "Why you leave me alone so long?", Toast.LENGTH_SHORT).show();
  }

}
