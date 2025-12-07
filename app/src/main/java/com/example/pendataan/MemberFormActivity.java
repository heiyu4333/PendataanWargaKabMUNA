package com.example.pendataan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MemberFormActivity extends AppCompatActivity {
    private EditText etNik;
    private EditText etNama;
    private EditText etTanggal;
    private Spinner spHubungan;
    private Spinner spKesehatan;
    private Spinner spPendidikan;
    private Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_form);

        etNik = findViewById(R.id.etNik);
        etNama = findViewById(R.id.etNama);
        etTanggal = findViewById(R.id.etTanggalLahir);
        spHubungan = findViewById(R.id.spHubungan);
        spKesehatan = findViewById(R.id.spKesehatan);
        spPendidikan = findViewById(R.id.spPendidikan);
        btnSimpan = findViewById(R.id.btnSimpanAnggota);

        ArrayAdapter<String> hubunganAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{getString(R.string.ayah), getString(R.string.ibu), getString(R.string.anak), getString(R.string.lainnya)});
        hubunganAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spHubungan.setAdapter(hubunganAdapter);

        ArrayAdapter<String> kesehatanAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{getString(R.string.sehat), getString(R.string.disabilitas)});
        kesehatanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKesehatan.setAdapter(kesehatanAdapter);

        ArrayAdapter<String> pendidikanAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{getString(R.string.sd), getString(R.string.smp), getString(R.string.sma), getString(R.string.d3), getString(R.string.s1), getString(R.string.s2), getString(R.string.s3), getString(R.string.lainnya)});
        pendidikanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPendidikan.setAdapter(pendidikanAdapter);

        btnSimpan.setOnClickListener(v -> {
            Intent data = new Intent();
            data.putExtra("nik", etNik.getText().toString().trim());
            data.putExtra("nama", etNama.getText().toString().trim());
            data.putExtra("tanggal", etTanggal.getText().toString().trim());
            data.putExtra("hubungan", spHubungan.getSelectedItem().toString());
            data.putExtra("kesehatan", spKesehatan.getSelectedItem().toString());
            data.putExtra("pendidikan", spPendidikan.getSelectedItem().toString());
            setResult(RESULT_OK, data);
            finish();
        });
    }
}
