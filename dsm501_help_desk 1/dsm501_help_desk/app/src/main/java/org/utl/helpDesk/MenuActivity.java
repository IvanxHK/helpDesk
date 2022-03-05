package org.utl.helpDesk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import org.utl.helpDesk.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    private ActivityMenuBinding binding;
    private int employeeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        employeeId = getIntent().getIntExtra("id", -1);

        binding.btnAdd.setOnClickListener(v -> {
            final Intent intent = new Intent(getApplicationContext(), TicketActivity.class);
            intent.putExtra("id", employeeId);
            startActivity(intent);
        });

        binding.btnView.setOnClickListener(v -> {
            final Intent intent = new Intent(getApplicationContext(), ViewTicketsActivity.class);
            intent.putExtra("id", employeeId);
            startActivity(intent);
        });
    }
}