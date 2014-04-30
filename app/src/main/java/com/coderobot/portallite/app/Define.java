package com.coderobot.portallite.app;

/**
 * Created by Administrator on 2014/4/29.
 */
public class Define {
    public static final String TAG = "Portal Lite";
    public static final String LOGIN_URL = "http://portalite.revo.so/login";
    public static final String DB_NAME = "http://portalite.revo.so/login";

    // DB contants
    public final static int DB_VERSION = 1;
    public final static String DB_USER_INFO = "UserInfo";
    public final static String DB_COURSE_TABLE = "Course";
    public final static String DB_SCHEDULE_TABLE = "Schedule";


    public final static String MSG_NO_NETWORK = "連線失敗，請檢查網路連接是否正常";
    public final static String MSG_CON_FAIL = "連線失敗，請確保網路通順";
    public final static String MSG_EXCEPTION = "程式發生錯誤，請聯絡開發者";
    public final static String MSG_WRONG_INFO = "帳號或密碼錯誤，請重新輸入";
    public final static String MSG_LOGIN_FIRST_TIME = "登入成功，第一次登入請耐心等候檔案傳輸";
    public final static String MSG_FINISH = "檔案傳輸完成";
    public final static String MSG_AUTH_FAIL = "連線憑證失敗";
}
