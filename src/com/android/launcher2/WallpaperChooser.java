/*
 * Copyright (C) 2008 The Android Open Source Project
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

package com.android.launcher2;
import android.os.Build;
import com.android.launcher.R;
import android.content.pm.ActivityInfo;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;

public class WallpaperChooser extends Activity {
    @SuppressWarnings("unused")
    private static final String TAG = "Launcher.WallpaperChooser";
    private static final String INTENT_EXTRA = "packagename";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Intent intent = getIntent();
        String packageName = null;
        if (intent != null) {
            packageName = intent.getStringExtra(INTENT_EXTRA);
        }
        WallpaperChooserDialogFragment.setExtraWallpaperPackageName(packageName);

        if(!Build.PWV_CUSTOM_CUSTOM.equals("SYB")){
            setContentView(R.layout.wallpaper_chooser_base);
        }

        if(Build.PROJECT.equals("SQ46"))
             if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
                 setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
              }
        Fragment fragmentView =
                getFragmentManager().findFragmentById(R.id.wallpaper_chooser_fragment);
        // TODO: The following code is currently not exercised. Leaving it here in case it
        // needs to be revived again.
        if (fragmentView == null) {
            /* When the screen is XLarge, the fragment is not included in the layout, so show it
             * as a dialog
             */
            DialogFragment fragment = WallpaperChooserDialogFragment.newInstance();
            fragment.show(getFragmentManager(), "dialog");
        }
    }
}
