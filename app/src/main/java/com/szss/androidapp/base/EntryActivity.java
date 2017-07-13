package com.szss.androidapp.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.Toast;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.szss.androidapp.R;
import com.szss.androidapp.application.SzssApp;
import com.szss.androidapp.module.barcode.activity.CaptureActivity;
import com.szss.androidapp.module.guide.LauncherActivity;
import com.szss.androidapp.module.haojia.HaojiaFragment;
import com.szss.androidapp.module.haowen.HaowenFragment;
import com.szss.androidapp.module.home.fragment.HomeChatFragment;
import com.szss.androidapp.module.home.fragment.HomeFragment;
import com.szss.androidapp.module.home.fragment.HomeNewsFragment;
import com.szss.androidapp.module.imui.NotificationInitSampleHelper;
import com.szss.androidapp.module.profile.fragment.ProfileFragment;
import com.szss.androidapp.util.IMUtil;
import com.szss.androidapp.util.PrefsUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class EntryActivity extends BaseActivity {

	public static final int NAVIGATION_TAB_HOME = 0;
	public static final int NAVIGATION_TAB_HAOJIA = 1;
	public static final int NAVIGATION_TAB_HAOWU = 2;
	public static final int NAVIGATION_TAB_HAOWEN = 3;
	public static final int NAVIGATION_TAB_PROFILE = 4;

	/**
	 * 扫描跳转Activity RequestCode
	 */
	public static final int REQUEST_CODE = 111;
	public int currentTab = 0;

	private ArrayList<Fragment> mFragments;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener() {

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			switch (item.getItemId()) {
				case R.id.navigation_home:
					showFragment(NAVIGATION_TAB_HOME);
					return true;
				case R.id.navigation_haojia:
					showFragment(NAVIGATION_TAB_HAOJIA);
					return true;
				case R.id.navigation_haowu:
					showFragment(NAVIGATION_TAB_HAOWU);
					return true;
				case R.id.navigation_haowen:
					showFragment(NAVIGATION_TAB_HAOWEN);
					return true;
				case R.id.navigation_profile:
					showFragment(NAVIGATION_TAB_PROFILE);
					return true;
			}
			return false;
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		enterApp();
	}

	private void enterApp() {
//		SharedPreferences sharedPreferences = PrefsUtil.getInstance();
//		if (sharedPreferences.getBoolean(PrefsUtil.FIRST_OPEN_APP, true)) {
//			sharedPreferences.edit().putBoolean(PrefsUtil.FIRST_OPEN_APP, false).apply();
//			Intent intent = new Intent(this, LauncherActivity.class);
//			startActivity(intent);
//			finish();
//		} else {
		Observable.timer(0, TimeUnit.SECONDS)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<Long>() {
					@Override
					public void accept(@io.reactivex.annotations.NonNull Long aLong) throws Exception {
						setContentView(R.layout.activity_main);
//						initIM();
						initSystemBarTint();
						initFragments();
						BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
						disableShiftMode(navigation);
						navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
						navigation.setSelectedItemId(getLastVisitNavigationId());
					}
				});
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}

	private void initIM() {
		//此实现不一定要放在Application onCreate中
		//此对象获取到后，保存为全局对象，供APP使用
		//此对象跟用户相关，如果切换了用户，需要重新获取
		IMUtil.mIMKit = YWAPI.getIMKitInstance(IMUtil.USERID, IMUtil.IM_APP_KEY);
		IYWLoginService loginService = IMUtil.mIMKit.getLoginService();
		YWLoginParam loginParam = YWLoginParam.createLoginParam(IMUtil.USERID, IMUtil.PASSWORD);
		loginService.login(loginParam, new IWxCallback() {

			@Override
			public void onSuccess(Object... arg0) {
				NotificationInitSampleHelper.init();

			}

			@Override
			public void onProgress(int arg0) {
				// TODO Auto-generated method stub
//				Toast.makeText(EntryActivity.this, arg0 + "", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onError(int errCode, String description) {
				//如果登录失败，errCode为错误码,description是错误的具体描述信息
//				Toast.makeText(EntryActivity.this, description + "", Toast.LENGTH_SHORT).show();
			}
		});
	}


	private int getLastVisitNavigationId() {
		int lastvisit = PrefsUtil.getInstance().getInt(PrefsUtil.ENTERPAGE_LASTVISIT, NAVIGATION_TAB_HOME);
		if (lastvisit == NAVIGATION_TAB_HOME) {
			return R.id.navigation_home;
		}
		if (lastvisit == NAVIGATION_TAB_HAOJIA) {
			return R.id.navigation_haojia;
		}
		if (lastvisit == NAVIGATION_TAB_HAOWU) {
			return R.id.navigation_haowu;
		}
		if (lastvisit == NAVIGATION_TAB_HAOWEN) {
			return R.id.navigation_haowen;
		}
		return R.id.navigation_profile;
	}

	private void initFragments() {
		mFragments = new ArrayList<>();
		mFragments.add(NAVIGATION_TAB_HOME, new HomeFragment());
		mFragments.add(NAVIGATION_TAB_HAOJIA, new HaojiaFragment());
		mFragments.add(NAVIGATION_TAB_HAOWU, IMUtil.mIMKit.getConversationFragment());
		mFragments.add(NAVIGATION_TAB_HAOWEN, new HomeNewsFragment());
		mFragments.add(NAVIGATION_TAB_PROFILE, new ProfileFragment());
	}

	private void showFragment(int index) {
		currentTab = index;
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		for (int i = 0; i < mFragments.size(); i++) {
			Fragment fragment = mFragments.get(i);
			if (i == index) {
				if (fragment.isAdded()) {
					fragmentTransaction.show(fragment);
				} else {
					fragmentTransaction.add(R.id.fragment_container, fragment);
				}
			} else {
				if (fragment.isAdded()) {
					fragmentTransaction.hide(fragment);
				}
			}
		}
		fragmentTransaction.commit();
		saveLastVistIndex(index);
	}

	private void saveLastVistIndex(int index) {
		SharedPreferences sharedPreferences = PrefsUtil.getInstance();
		sharedPreferences.edit().putInt(PrefsUtil.ENTERPAGE_LASTVISIT, index).apply();
	}

	@Override
	protected boolean translucentStatusBar() {
		return false;
	}

	// 固定bottom navigationmenu
	public static void disableShiftMode(BottomNavigationView view) {
		BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
		try {
			Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
			shiftingMode.setAccessible(true);
			shiftingMode.setBoolean(menuView, false);
			shiftingMode.setAccessible(false);
			for (int i = 0; i < menuView.getChildCount(); i++) {
				BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
				//noinspection RestrictedApi
				item.setShiftingMode(false);
				// set once again checked value, so view will be updated
				//noinspection RestrictedApi
				item.setChecked(item.getItemData().isChecked());
			}
		} catch (Exception e) {
			Toast.makeText(SzssApp.getInstance().getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == ProfileFragment.REQUEST_PERMISSION_CAMERA) {
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				Intent intent = new Intent(this, CaptureActivity.class);
				startActivityForResult(intent, EntryActivity.REQUEST_CODE);
			}
		}
	}
}
