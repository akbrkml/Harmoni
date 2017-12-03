package com.harmoni.harmonikeluarga.network;

import com.harmoni.harmonikeluarga.network.config.APIClient;
import com.harmoni.harmonikeluarga.network.config.APIInterfaces;

import retrofit2.Callback;
import retrofit2.http.Field;

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

    public void getChild(String act, String customerId, Callback callback){
        apiInterface.getChilds(act, customerId).enqueue(callback);
    }

    public void updateProfile(String act,
                              String address,
                              String email,
                              String name,
                              String province,
                              String msisdn,
                              String customerId,
                              String city,
                              Callback callback){
        apiInterface.updateProfile(act, address, email, name, province, msisdn, customerId, city).enqueue(callback);
    }

    public void addTopic(String act, String customerId, String topicId, Callback callback){
        apiInterface.addTopics(act, customerId, topicId).enqueue(callback);
    }

    public void deleteChild(String act, String childId, Callback callback){
        apiInterface.deleteChild(act, childId).enqueue(callback);
    }

    public void getListByDegree(String act, String degreeId, String customerId, Callback callback){
        apiInterface.getListByDegree(act, degreeId, customerId).enqueue(callback);
    }

    public void getListConsultation(String act, String customerId, String page, Callback callback){
        apiInterface.getListConsultation(act, customerId, page).enqueue(callback);
    }

    public void getListEvent(String act, Callback callback){
        apiInterface.getListEvent(act).enqueue(callback);
    }

    public void downloadFile(String url, Callback callback){
        apiInterface.downloadFile(url).enqueue(callback);
    }

    public void getListEventParticipant(String act, String eventId, Callback callback){
        apiInterface.getListEventParticipant(act, eventId).enqueue(callback);
    }

    public void getListEventParticipantWinner(String act, String eventId, Callback callback){
        apiInterface.getListEventParticipantWinner(act, eventId).enqueue(callback);
    }

    public void getBookCategory(String act, String sectionName, Callback callback){
        apiInterface.getBookCategory(act, sectionName).enqueue(callback);
    }

    public void getBookByCat(String act, String catId, Callback callback){
        apiInterface.getListBookByCat(act, catId).enqueue(callback);
    }

    public void getListJournalism(String act, Callback callback){
        apiInterface.getListJournalism(act).enqueue(callback);
    }

    public void addConsultation(String act, String customerId, String childId, String consultTitle, String consultQuestion, Callback callback){
        apiInterface.addConsultation(act, customerId, childId, consultTitle, consultQuestion).enqueue(callback);
    }

    public void addSaran(String act, String customerId, String saranText, String saranTitle, Callback callback){
        apiInterface.addSaran(act, customerId, saranText, saranTitle).enqueue(callback);
    }

    public void addChild(String act,
                         String degreeId,
                         String childId,
                         String childGender,
                         String childBirth,
                         String childName,
                         String customerId,
                         String childNumber,
                         Callback callback){
        apiInterface.addChilds(act, degreeId, childId, childGender, childBirth, childName, customerId, childNumber).enqueue(callback);
    }
}
