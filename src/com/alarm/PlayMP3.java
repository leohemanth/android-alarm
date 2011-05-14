/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alarm;


import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class PlayMP3 extends Activity {

    private static final String TAG = "MediaPlayerDemo";
    private MediaPlayer mMediaPlayer;
   
    private TextView textView;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        textView = new TextView(this);
        setContentView(textView);
        try {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.test_cbr);
                    mMediaPlayer.start();
                    textView.setText("Time up!");
          
        } catch (Exception e) {
            Log.e(TAG, "error: " + e.getMessage(), e);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TODO Auto-generated method stub
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

    }
}
