package kr.co.tjeit.sociallogin2.util;

import android.content.Context;
import android.content.SharedPreferences;

import kr.co.tjeit.sociallogin2.data.User;

/**
 * Created by user on 2017-08-30.
 */

public class ContextUtil {


    private static User loginUser = null;

    private static final String prefName = "tempPref";

    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_PROFILE_URL = "USER_PROFILE_URL";

    public static void login(Context context, String id, String name, String url) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putString(USER_ID, id).apply();
        pref.edit().putString(USER_NAME, name).apply();
        pref.edit().putString(USER_PROFILE_URL, url).apply();

    }

    public static void logout(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putString(USER_ID, "").apply();
        pref.edit().putString(USER_NAME, "").apply();
        pref.edit().putString(USER_PROFILE_URL, "").apply();

    }

    public static User getLoginUserData(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (pref.getString(USER_ID, "").equals("")) {
//            기록된 아이디가 빈칸 : 아이디 X : 로그인 X
            loginUser = null;
        }
        else {
            loginUser = new User();
            loginUser.setUserId(pref.getString(USER_ID, ""));
            loginUser.setName(pref.getString(USER_NAME, ""));
            loginUser.setProfileURL(pref.getString(USER_PROFILE_URL, ""));
        }


        return  loginUser;

    }

}
