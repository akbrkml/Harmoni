package com.harmoni.harmonikeluarga.network.config;

import com.harmoni.harmonikeluarga.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by akbar on 19/10/17.
 */

public interface APIInterfaces {

    @FormUrlEncoded
    @POST("APIuser.php")
    Call<User> signIn(
            @Field("act") String act,
            @Field("email") String username,
            @Field("pass") String password
    );
}
