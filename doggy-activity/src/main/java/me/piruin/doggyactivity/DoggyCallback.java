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

interface DoggyCallback {

  /**
   * Screen is on again but that not mean user is back to activity.
   */
  void onScreenOn();

  /**
   * When screen is off, Beware to do UI Action here. Run background job or service is better.
   */
  void onScreenOff();

  /**
   * <p> Call when user leave activity, UI Action may not work here if Screen off was called before.
   * Run background job or service is better. </p>
   *
   * <p>Doggy not track back press action.</p>
   *
   * @param systemInterrupt is user was interrupted by system such as Phone call-in
   */
  void onUserLeave(boolean systemInterrupt);

  /**
   * Call when user back to activity.
   */
  void onUserBack();
}
