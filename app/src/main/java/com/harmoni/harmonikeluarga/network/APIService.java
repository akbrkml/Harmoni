package com.harmoni.harmonikeluarga.network;

import com.harmoni.harmonikeluarga.network.config.APIClient;
import com.harmoni.harmonikeluarga.network.config.APIInterfaces;

import retrofit2.Callback;

/**
 * Created by akbar on 19/10/17.
 */

public class APIService {
    private APIInterfaces apiInterface;

    public APIService() {
        apiInterface = APIClient.builder()
                .create(APIInterfaces.class);
    }

    public void doLogin(String act, String email, String pass, Callback callback) {
        apiInterface.signIn(act, email, pass).enqueue(callback);
    }
}
