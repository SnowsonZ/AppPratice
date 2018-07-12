package com.example.snowson.apptest.bugly;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

public class MainApplication extends TinkerApplication {


    protected MainApplication(int tinkerFlags) {
        super(ShareConstants.TINKER_ENABLE_ALL,
                "com.example.snowson.apptest.bugly.MainApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
