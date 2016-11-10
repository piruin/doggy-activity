# :dog: Doggy Activity [![Build Status](https://travis-ci.org/piruin/doggy-activity.svg?branch=master)](https://travis-ci.org/piruin/doggy-activity)

Doggy is watchdog for your activity, Let you know when user is leave your activity and when they back (or never come back again :cry:)

## Get Started

### Installation

#### [JCenter]

- **Step 1** - set JCenter repository (This step not require for modern android project)
- **Step 2** - Add dependencies on app module

```groovy
dependencies {
  compile 'me.piruin:doggy-activity:LATEST_VERSION'
}
```
Change `LATEST_VERSION` to latest version name

#### [JitPack]

- **Step 1** - Set JitPack repository
```groovy
allprojects {
  repositories {
      ...
      maven { url "https://jitpack.io" }
    }
  }
```
- **Step 2** - Add dependencies on app module

```groovy
dependencies {
  compile 'com.github.piruin:doggy-activity:LATEST_VERSION'
}
```

Change `LATEST_VERSION` to latest version name


### How to use

Just simple on extend `DoggyActivity` and `@override` method of doggy callback you want

- `onUserLeave(boolean systemInterrupt)` - call when user leave activity
- `onUserBack()` - when user back to activity
- `onScreenOff()` - Screen if off by display timeout or user press power button
- `onScreenOn()` - Screen is on. but beware, That not mean user is back to activity

See [SampleActivity] for more information

## Dependency

For now `DoggyActivity` is extend from `AppCompatActivity` so it depend on
`com.android.support:appcompat-v7:+` because mostly app use it.

Should I make it for more flexible on next version?

## [License]

    Copyright 2016 Piruin Panichphol

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[JCenter]: https://bintray.com/bintray/jcenter
[JitPack]: https://jitpack.io/
[SampleActivity]: https://github.com/piruin/doggy-activity/blob/master/doggy-activity-sample/src/main/java/me/piruin/doggyactivity/sample/SampleActivity.java
[License]: http://www.apache.org/licenses/LICENSE-2.0
