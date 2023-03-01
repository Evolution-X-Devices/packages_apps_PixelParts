/*
 * Copyright (C) 2018-2022 crDroid Android Project
 *               2023 The Evolution X Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.evolution.pixel.PixelParts;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import org.evolution.pixel.PixelParts.kcal.Kcal;

public class Startup extends BroadcastReceiver {

    private static final String TAG = Startup.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
            // Main
            PixelParts.restorePowerEfficientWorkqueueSetting(context);
            PixelParts.restoreFSyncSetting(context);
            PixelParts.restoreFastChargeSetting(context);
            PixelParts.restoreMicGainSetting(context);
            PixelParts.restoreSpeakerGainSetting(context);
            // Kcal
            Kcal.restoreRedSetting(context);
            Kcal.restoreGreenSetting(context);
            Kcal.restoreBlueSetting(context);
            Kcal.restoreSaturationSetting(context);
            Kcal.restoreContrastSetting(context);
            Kcal.restoreHueSetting(context);
            Kcal.restoreValueSetting(context);
    }
}
