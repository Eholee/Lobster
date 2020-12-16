package com.eholee.lobsterdemo.module1;


import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.eholee.lobster.annotation.AppInstance;
import com.eholee.lobster.annotation.ModuleApp;

/**
 * Author:Jeffer
 * Time:2020/12/16  11:20 PM  Wednesday
 * Description: 模块1Application
 */
@ModuleApp(priority = 1)
public class Module1App extends Application {
    private static final String TAG = "Lobster";

    @AppInstance
    public static Application mApplication1;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG , "Module1App->onCreate");
    }
}
