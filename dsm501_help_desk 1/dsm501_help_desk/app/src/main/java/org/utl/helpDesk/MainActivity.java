package org.utl.helpDesk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.utl.helpDesk.api.Base;
import org.utl.helpDesk.api.Login;
import org.utl.helpDesk.api.Service;
import org.utl.helpDesk.databinding.ActivityMainBinding;
import org.utl.helpDesk.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Retrofit retrofit = null;
    Service service = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        service = new Service();
        retrofit = service.createService();
        binding.btnLogin.setOnClickListener(v -> {
            login();
        });
    }

    private void login() {
        if (inputsFilled()) {
            final String username = binding.txtUsername.getText().toString();
            final String password = binding.txtPassword.getText().toString();


            Login loginApi = retrofit.create(Login.class);
            Call<User> call = loginApi.login(username, password);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful() && response.code() == 200) {
                        if (response.body() != null) {
                            Toast.makeText(getApplicationContext(), "Datos correctos", Toast.LENGTH_SHORT).show();
                            final Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                            intent.putExtra("id", response.body().getEmployee().getId());
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Error interno del servidor", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("LOGIN", response.toString());
                    Log.d("MESSAGE", response.message());
                    Log.d("LOGIN", String.valueOf(response.code()));
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
                    Log.e("ERROR", t.getMessage());
                }
            });
        } else {
            Toast.makeText(this, "No has llenado todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean inputsFilled() {
        return binding.txtUsername.length() > 0 &&
                binding.txtPassword.length() > 0;
    }

}