package com.zhou.lawson.openglpractice;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.content.res.Resources;
import android.os.Build;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by lawson on 16/10/28.
 */

public final class Utils {

  public static boolean supportsEs2(Context context) {
    final ActivityManager activityManager =
        (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
    return configurationInfo.reqGlEsVersion >= 0x20000 || (Build.VERSION.SDK_INT
        >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 && (Build.FINGERPRINT.startsWith("generic")
        || Build.FINGERPRINT.startsWith("unknown")
        || Build.MODEL.contains("google_sdk")
        || Build.MODEL.contains("Emulator")
        || Build.MODEL.contains("Android SDK built for x86")));
  }

  public static String readTextFileFromResource(Context context, int resourceId) {
    StringBuilder body = new StringBuilder();

    try {
      InputStream inputStream = context.getResources().openRawResource(resourceId);
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

      String nextLine;

      while ((nextLine = bufferedReader.readLine()) != null) {
        body.append(nextLine);
        body.append('\n');
      }
    } catch (IOException e) {
      throw new RuntimeException("Could not open resource: " + resourceId, e);
    } catch (Resources.NotFoundException nfe) {
      throw new RuntimeException("Resource not found: " + resourceId, nfe);
    }

    return body.toString();
  }
}
