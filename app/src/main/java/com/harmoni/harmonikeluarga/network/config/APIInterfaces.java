package com.harmoni.harmonikeluarga.network.config;

import com.harmoni.harmonikeluarga.model.Topic;
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

    @FormUrlEncoded
    @POST("APIuser.php")
    Call<User> signUp(
            @Field("act") String act,
            @Field("min") String min,
            @Field("pass") String pass,
            @Field("city") String city,
            @Field("handset") String handset,
            @Field("regId") String regId,
            @Field("name") String name,
            @Field("email") String email,
            @Field("province") String province,
            @Field("msisdn") String phone
    );

    @FormUrlEncoded
    @POST("APIuser.php")
    Call<User> getProfile(
            @Field("act") String act,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("APIcontent.php")
    Call<Topic> getTopics(
            @Field("act") String act,
            @Field("customerId") String customerId
    );

    @FormUrlEncoded
    @POST("APIcontent.php")
    Call<Topic> addTopics(
            @Field("act") String act,
            @Field("customerId") String customerId,
            @Field("topicId") String topicId
    );

    @FormUrlEncoded
    @POST("APIuser.php")
    Call<Topic> addChilds(
            @Field("act") String act,
            @Field("degreeId") String degreeId,
            @Field("childId") String childId,
            @Field("childGender") String childGender,
            @Field("childBirth") String childBirth,
            @Field("childName") String childName,
            @Field("customerId") String customerId,
            @Field("childNumber") String childNumber
    );

    @FormUrlEncoded
    @POST("APIuser.php")
    Call<User> updateProfile(
            @Field("act") String act,
            @Field("address") String address,
            @Field("email") String email,
            @Field("name") String name,
            @Field("province") String province,
            @Field("msisdn") String msisdn,
            @Field("customerId") String customerId,
            @Field("city") String city
    );
}
