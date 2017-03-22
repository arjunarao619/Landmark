package landmark.arjunarao.arjunrao.com.landmark;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by Arjun Rao on 3/22/2017.
 */

public class Landmark extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}