/*
 * Copyright (C) 2023-2024 The Evolution X Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.evolution.pixelparts.fastcharge;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.android.settingslib.widget.MainSwitchPreference;

import org.evolution.pixelparts.Constants;
import org.evolution.pixelparts.R;
import org.evolution.pixelparts.utils.FileUtils;
import org.evolution.pixelparts.utils.TileUtils;

public class FastChargeFragment extends PreferenceFragmentCompat
        implements OnCheckedChangeListener {

    private MainSwitchPreference mFastChargeSwitch;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.fast_charge, rootKey);
        setHasOptionsMenu(true);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());

        mFastChargeSwitch = findPreference(Constants.KEY_FAST_CHARGE);
        mFastChargeSwitch.setChecked(sharedPrefs.getBoolean(Constants.KEY_FAST_CHARGE, false));
        mFastChargeSwitch.addOnSwitchChangeListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fast_charge_menu, menu);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SharedPreferences.Editor prefChange = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
        prefChange.putBoolean(Constants.KEY_FAST_CHARGE, isChecked).apply();
        if (FileUtils.isFileWritable(Constants.NODE_FAST_CHARGE)) {
            FileUtils.writeValue(Constants.NODE_FAST_CHARGE, isChecked ? "1" : "0");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_tile) {
            TileUtils.requestAddTileService(
                    getContext(),
                    FastChargeTileService.class,
                    R.string.fast_charge_title,
                    R.drawable.ic_fast_charge_tile
            );
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public static void restoreFastChargeSetting(Context context) {
        if (FileUtils.isFileWritable(Constants.NODE_FAST_CHARGE)) {
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
            boolean value = sharedPrefs.getBoolean(Constants.KEY_FAST_CHARGE, false);
            FileUtils.writeValue(Constants.NODE_FAST_CHARGE, value ? "1" : "0");
        }
    }
}
