package org.utl.helpDesk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.utl.helpDesk.api.Base;
import org.utl.helpDesk.api.Service;
import org.utl.helpDesk.api.TicketApi;
import org.utl.helpDesk.databinding.ActivityTicketBinding;
import org.utl.helpDesk.model.Employee;
import org.utl.helpDesk.model.Ticket;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class TicketActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityTicketBinding binding;
    private int employeeId;
    private String imageResName;
    private Retrofit retrofit;
    private Service service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTicketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.spDevices.setOnItemSelectedListener(this);
        employeeId = getIntent().getIntExtra("id", -1);
        binding.btnRegister.setOnClickListener(v -> register());
        binding.txtTime.setOnClickListener(v -> pickTime());
        binding.txtDate.setOnClickListener(v -> pickDate());

        service=new Service();
        retrofit=service.createScalarService();
    }

    private void pickDate() {
        DialogFragment timePicker = new DatePickerFragment(binding.txtDate);
        timePicker.show(getSupportFragmentManager(), "PickDate");
    }

    private void pickTime() {
        DialogFragment timePicker = new TimePickerFragment(binding.txtTime);
        timePicker.show(getSupportFragmentManager(), "PickTime");
    }

    private void register() {
        if (inputsFilled()) {
            final Gson gson = new Gson();
            final String ticket = gson.toJson(getTicket(), Ticket.class);

            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Base.url)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
            TicketApi ticketApi = retrofit.create(TicketApi.class);
            Call<String> call = ticketApi.insert(ticket);
            Log.d("BODY", ticket);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(getApplicationContext(), response.body(), Toast.LENGTH_SHORT).show();
                    }
                    Log.d("RESPONSE", response.body());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
                    Log.e("ERROR", t.getMessage());
                }
            });
        } else {
            Toast.makeText(this, "No has llenado todos los campos", Toast.LENGTH_SHORT).show();
        }
        clearInputs();
    }

    private boolean inputsFilled() {
        return binding.txtDate.length() > 0 &&
                binding.txtTime.length() > 0 &&
                binding.txtDescription.length() > 0;
    }

    private void clearInputs() {
        binding.txtDate.setText("");
        binding.txtTime.setText("");
        binding.txtDescription.setText("");
    }

    private Ticket getTicket() {
        final Ticket ticket = new Ticket();
        final Employee employee = new Employee();

        employee.setId(employeeId);
        final String device = binding.spDevices.getSelectedItem().toString();
        final String error = binding.spErrors.getSelectedItem().toString();
        final String date = binding.txtDate.getText().toString();
        final String time = binding.txtTime.getText().toString();
        final String description = binding.txtDescription.getText().toString();

        ticket.setDevice(device);
        ticket.setImageResName(imageResName);
        ticket.setType(error);
        ticket.setDate(date);
        ticket.setTimeOf(time);
        ticket.setDescription(description);
        ticket.setEmployee(employee);
        ticket.setStatus(getStatus());

        Log.d("TICKET", ticket.toString());

        return ticket;
    }

    private int getStatus() {
        return (int) binding.spStatus.getSelectedItemId();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int imageResId;
        switch (i) {
            case 0:
                imageResId = R.drawable.ic_desktop;
                break;
            case 1:
                imageResId = R.drawable.ic_phone;
                break;
            case 2:
                imageResId = R.drawable.ic_laptop;
                break;
            case 3:
                imageResId = R.drawable.ic_tablet;
                break;
            case 4:
                imageResId = R.drawable.ic_keyboard;
                break;
            default:
                imageResId = -1;
        }
        binding.deviceImage.setImageResource(imageResId);
        imageResName = getResources().getResourceEntryName(imageResId);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}