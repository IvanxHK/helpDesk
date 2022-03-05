package org.utl.helpDesk.api;

import org.utl.helpDesk.model.Ticket;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TicketApi {
    @FormUrlEncoded
    @POST("ticket/insert")
    Call<String> insert(@Field("ticket") String ticket);

    @GET("ticket/getAll")
    Call<ArrayList<Ticket>> getAll(@Query("employeeId") int employeeId, @Query("status") int status);

    @DELETE("ticket/delete")
    Call<String> delete(@Query("id") int id);
}
