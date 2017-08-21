package ca.interfaced.dockmaster.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import ca.interfaced.dockmaster.BuildConfig;
import ca.interfaced.dockmaster.R;
import io.realm.Realm;
import io.realm.log.LogLevel;
import io.realm.log.RealmLog;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by vivianechan on 2017-07-27.
 */

public class MyApplication  extends Application {

    public static final String AUTH_URL = "http://" + "192.168.2.162"+ ":9080/auth";
    public static final String REALM_URL = "realm://" + "192.168.2.162" + ":9080/~/dockmaster";
    public static final String DEFAULT_LIST_ID = "80EB1620-165B-4600-A1B1-D97032FDD9A0";
    public static String DEFAULT_LIST_NAME = "Dockmaster";

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmLog.setLevel(LogLevel.TRACE);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }



}
