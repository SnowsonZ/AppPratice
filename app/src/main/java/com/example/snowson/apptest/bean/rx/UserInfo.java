package com.example.snowson.apptest.bean.rx;

/**
 * Created by snowson on 18-1-19.
 */

public class UserInfo {
    public BaseUserInfo bui;
    public ExtraUserInfo eui;

    public UserInfo(BaseUserInfo bui, ExtraUserInfo eui) {
        this.bui = bui;
        this.eui = eui;
    }
}
