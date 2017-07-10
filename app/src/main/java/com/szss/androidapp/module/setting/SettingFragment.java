package com.szss.androidapp.module.setting;

import android.app.Activity;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;

import com.szss.androidapp.R;
import com.szss.androidapp.util.Utils;

/**
 * Created by wuwei on 2017/7/10.
 */

public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

	public static final String POLICY = "prefernece.policy";
	public static final String LICENSE = "prefernece.license";
	public static final String SETTINGS_VERSION = "prefernece.version";

	private PreferenceScreen mPreferenceScreen;
	private Activity mActivity;

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mActivity = getActivity();
		getView().setBackgroundResource(R.color.all_white);
		setPreferenceScreen(createPreferenceHierarchy());
	}

	private PreferenceScreen createPreferenceHierarchy() {
		// Root
		mPreferenceScreen = this.getPreferenceManager().createPreferenceScreen(mActivity);
		addAbout();
		return mPreferenceScreen;
	}

	private void addAbout() {
		PreferenceCategory aboutGroup = new PreferenceCategory(mActivity);
		aboutGroup.setTitle(R.string.settings_about);
		mPreferenceScreen.addPreference(aboutGroup);

		final Preference policyFlag = new Preference(mActivity);
		policyFlag.setTitle(R.string.settings_policy);
		policyFlag.setKey(POLICY);
		policyFlag.setOnPreferenceClickListener(this);
		aboutGroup.addPreference(policyFlag);

		final Preference licenseFlag = new Preference(mActivity);
		licenseFlag.setTitle(R.string.settings_license);
		licenseFlag.setKey(LICENSE);
		licenseFlag.setOnPreferenceClickListener(this);
		aboutGroup.addPreference(licenseFlag);

		final Preference versionPreference = new Preference(mActivity);
		versionPreference.setTitle(R.string.settings_version);
		versionPreference.setKey(SETTINGS_VERSION);
		versionPreference.setSummary(Utils.getAppVersionString(mActivity));
		versionPreference.setOnPreferenceClickListener(this);
		aboutGroup.addPreference(versionPreference);
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		return false;
	}

}
