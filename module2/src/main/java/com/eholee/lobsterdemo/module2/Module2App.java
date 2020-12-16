package com.eholee.lobsterdemo.module2;


import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.eholee.lobster.annotation.AppInstance;
import com.eholee.lobster.annotation.ModuleApp;

/**
 * Author:Jeffer
 * Time:2020/12/16  11:20 PM  Wednesday
 * Description: 模块2Application
 */
@ModuleApp(priority = 2)
public class Module2App extends Application {
    private static final String TAG = "Lobster";
    @AppInstance
    public static Application mApplication2;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG , "Module2App->onCreate");
        Toast.makeText(mApplication2, "I come from Module2App", Toast.LENGTH_SHORT).show();
    }
}
