package org.utl.helpDesk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;

import org.utl.helpDesk.api.Base;
import org.utl.helpDesk.api.Service;
import org.utl.helpDesk.api.TicketApi;
import org.utl.helpDesk.databinding.ActivityViewTicketsBinding;
import org.utl.helpDesk.model.Ticket;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ViewTicketsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private ActivityViewTicketsBinding binding;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mlaLayoutManager;
    private ArrayList<Ticket> tickets;
    private int employeeId;
    private Retrofit retrofit;
    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewTicketsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        employeeId = getIntent().getIntExtra("id", -1);
        binding.swStatus.setOnCheckedChangeListener(this);
    service=new Service();
        initAdapter();
        getTickets();
    }

    private void initAdapter() {
        tickets = new ArrayList<>();
        mlaLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new RecyclerViewAdapter(tickets, getApplicationContext(), (int position) -> {
            DeletePrompt deletePrompt = new DeletePrompt(() -> {
                retrofit = service.createScalarService();
                //final Retrofit retrofit = new Retrofit.Builder().baseUrl(Base.url).addConverterFactory(ScalarsConverterFactory.create()).build();
                TicketApi ticketApi = retrofit.create(TicketApi.class);
                Call<String> call = ticketApi.delete(tickets.get(position).getId());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Toast.makeText(getApplicationContext(), response.body(), Toast.LENGTH_SHORT).show();
                        }
                        Log.d("RESPONSE", response.body());
                        getTickets();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
                        Log.e("ERROR", t.getMessage());
                    }
                });
            });
            deletePrompt.show(getSupportFragmentManager(), "eliminar");
        });
        binding.rvTickets.setLayoutManager(mlaLayoutManager);
        binding.rvTickets.setAdapter(mAdapter);
    }

    private void getTickets() {
        retrofit = service.createService();
        //final Retrofit retrofit = new Retrofit.Builder().baseUrl(Base.url).addConverterFactory(GsonConverterFactory.create()).build();
        int status = binding.swStatus.isChecked() ? 1 : 0;
        TicketApi ticketApi = retrofit.create(TicketApi.class);
        Call<ArrayList<Ticket>> call = ticketApi.getAll(employeeId, status);
        call.enqueue(new Callback<ArrayList<Ticket>>() {
            @Override
            public void onResponse(Call<ArrayList<Ticket>> call, Response<ArrayList<Ticket>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    int size = tickets.size();
                    tickets.clear();
                    mAdapter.notifyItemRangeRemoved(0, size);
                    tickets.addAll(response.body());
                    mAdapter.notifyItemRangeInserted(0, response.body().size());
                }

                Log.d("GETALL", response.toString());
                Log.d("GETALL_MESSAGE", response.message());
                Log.d("GETALL_CODE", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<ArrayList<Ticket>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        getTickets();
    }
}