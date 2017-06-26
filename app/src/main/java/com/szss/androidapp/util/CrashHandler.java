package com.szss.androidapp.util;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.ref.WeakReference;

public class CrashHandler implements UncaughtExceptionHandler {

	private WeakReference<Activity> mActivityWeakReference = null;

	public CrashHandler(Activity activity) {
		this.mActivityWeakReference = new WeakReference<>(activity);
	}

	@Override
	public void uncaughtException(Thread arg0, Throwable throwable) {
		Activity activity = mActivityWeakReference.get();
		if (activity != null) {
			try {
				Writer writer = new StringWriter();
				PrintWriter printWriter = new PrintWriter(writer);
				throwable.printStackTrace(printWriter);
				String strEmailBody = writer.toString();

				int versionCode = 0;
				try {
					PackageInfo info;
					info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);

					String versionName = info.versionName;
					strEmailBody += "\n" + "versionName: " + versionName + "\n";

					versionCode = info.versionCode;
					strEmailBody += "versionCode: " + versionCode + "\n";
					String devicename = Utils.getDeviceType(activity);
					strEmailBody += "deviceType: " + devicename + "\n";

				} catch (Exception e) {
					e.printStackTrace();
				}

				String emailTitle;
				String[] emailReceiver;
				emailTitle = "tapatalk crashed " + versionCode + "  "
						+ (int) (Math.random() * 100000);
				emailReceiver = new String[]{"810724908@qq.com"};
				Intent intent = new Intent(android.content.Intent.ACTION_SEND);
				intent.setType("plain/text");
				intent.putExtra(android.content.Intent.EXTRA_EMAIL, emailReceiver);
				intent.putExtra(Intent.EXTRA_SUBJECT, emailTitle);
				intent.putExtra(android.content.Intent.EXTRA_TEXT, strEmailBody);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				activity.startActivity(Intent.createChooser(intent, "send crash report"));
				activity.finish();
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}