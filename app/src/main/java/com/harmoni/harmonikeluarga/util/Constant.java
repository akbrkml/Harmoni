package com.harmoni.harmonikeluarga.util;

import android.content.Context;

import com.harmoni.harmonikeluarga.model.User;

/**
 * Created by akbar on 19/10/17.
 */

public class Constant {
    public static final String BASE_URL = "https://sahabatkeluarga.kemdikbud.go.id/harmoni/";

    public static final String BASE_URL_API = BASE_URL + "api/";

    public static final String BASE_URL_DOC = BASE_URL + "media/document/";

    public static final String USER_SESSION = "user_session";

    public static String getCustomerId(Context context){
        User user = SessionManager.getUser(context, USER_SESSION);
        return user.getDataUser().getCustomerId();
    }
}
