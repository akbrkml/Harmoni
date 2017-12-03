package com.harmoni.harmonikeluarga.network.config;

import com.harmoni.harmonikeluarga.model.Book;
import com.harmoni.harmonikeluarga.model.Child;
import com.harmoni.harmonikeluarga.model.Consultation;
import com.harmoni.harmonikeluarga.model.ContentChild;
import com.harmoni.harmonikeluarga.model.EventJounalism;
import com.harmoni.harmonikeluarga.model.Topic;
import com.harmoni.harmonikeluarga.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

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
    @POST("APIuser.php")
    Call<Child> getChilds(
            @Field("act") String act,
            @Field("customerId") String customerId
    );

    @FormUrlEncoded
    @POST("APIcontent.php")
    Call<ContentChild> getListByDegree(
            @Field("act") String act,
            @Field("degreeId") String degreeId,
            @Field("customerId") String customerId
    );

    @FormUrlEncoded
    @POST("APIconsultation.php")
    Call<Consultation> getListConsultation(
            @Field("act") String act,
            @Field("customerId") String customerId,
            @Field("page") String page
    );

    @FormUrlEncoded
    @POST("APIbook.php")
    Call<Book> getBookCategory(
            @Field("act") String act,
            @Field("sectionName") String sectionName
    );

    @FormUrlEncoded
    @POST("APIconsultation.php")
    Call<Consultation> addConsultation(
            @Field("act") String act,
            @Field("customerId") String customerId,
            @Field("childId") String childId,
            @Field("consultTitle") String consultTitle,
            @Field("consultQuestion") String consultQuestion
    );

    @FormUrlEncoded
    @POST("APIpesan.php")
    Call<User> addSaran(
            @Field("act") String act,
            @Field("customerId") String customerId,
            @Field("saranText") String saranText,
            @Field("saranTitle") String saranTitle
    );

    @FormUrlEncoded
    @POST("APIcontent.php")
    Call<EventJounalism> getListJournalism(
            @Field("act") String act
    );

    @FormUrlEncoded
    @POST("APIevent.php")
    Call<EventJounalism> getListEvent(
            @Field("act") String act
    );

    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);

    @FormUrlEncoded
    @POST("APIevent.php")
    Call<EventJounalism> getListEventParticipant(
            @Field("act") String act,
            @Field("eventId") String eventId
    );

    @FormUrlEncoded
    @POST("APIevent.php")
    Call<EventJounalism> getListEventParticipantWinner(
            @Field("act") String act,
            @Field("eventId") String eventId
    );

    @FormUrlEncoded
    @POST("APIbook.php")
    Call<Book> getListBookByCat(
            @Field("act") String act,
            @Field("catId") String catId
    );


    @FormUrlEncoded
    @POST("APIuser.php")
    Call<Child> deleteChild(
            @Field("act") String act,
            @Field("childId") String childId
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
    Call<Child> addChilds(
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
