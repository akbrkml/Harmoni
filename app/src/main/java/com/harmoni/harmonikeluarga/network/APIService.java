package com.harmoni.harmonikeluarga.network;

import com.harmoni.harmonikeluarga.network.config.APIClient;
import com.harmoni.harmonikeluarga.network.config.APIInterfaces;

import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.PUT;

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

    public void doRegister(String act,
                           String min,
                           String pass,
                           String city,
                           String handset,
                           String regId,
                           String name,
                           String email,
                           String province,
                           String phone,
                           Callback callback) {
        apiInterface.signUp(act, min, pass, city, handset, regId, name, email, province, phone).enqueue(callback);
    }

    public void getProfile(String act, String email, Callback callback){
        apiInterface.getProfile(act, email).enqueue(callback);
    }

    public void getTopic(String act, String customerId, Callback callback){
        apiInterface.getTopics(act, customerId).enqueue(callback);
    }
}
