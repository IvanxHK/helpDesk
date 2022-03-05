package org.utl.helpDesk.api;

import org.utl.helpDesk.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Login {
    @FormUrlEncoded
    @POST("login/login")
    Call<User> login(
            @Field("userName") String userName,
            @Field("password") String password
    );
}
