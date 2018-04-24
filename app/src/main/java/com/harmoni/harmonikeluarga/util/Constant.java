package com.harmoni.harmonikeluarga.util;

import android.content.Context;

import com.harmoni.harmonikeluarga.model.User;

/**
 * Created by akbar on 19/10/17.
 */

public class Constant {
    public static final String BASE_URL = "https://sahabatkeluarga.kemdikbud.go.id/harmoni/api/";

    public static final String USER_SESSION = "user_session";

    public static final String IMAGE_USER_URL = "https://sahabatkeluarga.kemdikbud.go.id/harmoni/media/image/";

    public static final String DOC_URL = "https://sahabatkeluarga.kemdikbud.go.id/harmoni/media/document/";

    public static String getCustomerId(Context context){
        User user = SessionManager.getUser(context, USER_SESSION);
        return user.getDataUser().getCustomerId();
    }
}
