package com.example.pendataan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private RecyclerView recyclerView;
    private Button btnInputKK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerKK);
        btnInputKK = findViewById(R.id.btnInputKK);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadData();
        btnInputKK.setOnClickListener(v -> {
            startActivity(new Intent(this, KKFormActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        List<KK> kks = db.getAllKK();
        recyclerView.setAdapter(new KKAdapter(kks));
    }
}
