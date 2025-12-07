package com.example.pendataan;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class KKFormActivity extends AppCompatActivity {
    private EditText etNomorKK;
    private EditText etAlamat;
    private Spinner spKondisi;
    private EditText etKelurahan;
    private EditText etKecamatan;
    private TextView tvJumlahAnggota;
    private Button btnTambahAnggota;
    private Button btnSimpanExport;

    private DatabaseHelper db;
    private List<Anggota> anggotaSementara = new ArrayList<>();

    private ActivityResultLauncher<Intent> anggotaLauncher;
    private ActivityResultLauncher<String> permissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kk_form);
        db = new DatabaseHelper(this);

        etNomorKK = findViewById(R.id.etNomorKK);
        etAlamat = findViewById(R.id.etAlamat);
        spKondisi = findViewById(R.id.spKondisiRumah);
        etKelurahan = findViewById(R.id.etKelurahan);
        etKecamatan = findViewById(R.id.etKecamatan);
        tvJumlahAnggota = findViewById(R.id.tvJumlahAnggota);
        btnTambahAnggota = findViewById(R.id.btnTambahAnggota);
        btnSimpanExport = findViewById(R.id.btnSimpanExport);

        ArrayAdapter<String> kondisiAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{getString(R.string.layak_huni), getString(R.string.tidak_layak_huni)});
        kondisiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKondisi.setAdapter(kondisiAdapter);

        anggotaLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Intent data = result.getData();
                Anggota a = new Anggota();
                a.setNik(data.getStringExtra("nik"));
                a.setNama(data.getStringExtra("nama"));
                a.setTanggalLahir(data.getStringExtra("tanggal"));
                a.setHubungan(data.getStringExtra("hubungan"));
                a.setKesehatan(data.getStringExtra("kesehatan"));
                a.setPendidikan(data.getStringExtra("pendidikan"));
                anggotaSementara.add(a);
                updateJumlah();
            }
        });

        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                simpanDanExport();
            } else {
                Toast.makeText(this, "Izin penyimpanan ditolak", Toast.LENGTH_SHORT).show();
            }
        });

        btnTambahAnggota.setOnClickListener(v -> {
            Intent i = new Intent(this, MemberFormActivity.class);
            anggotaLauncher.launch(i);
        });

        btnSimpanExport.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (!android.os.Environment.isExternalStorageManager()) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    startActivity(intent);
                    Toast.makeText(this, "Aktifkan akses semua file", Toast.LENGTH_LONG).show();
                    return;
                }
                simpanDanExport();
            } else {
                permissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        });
    }

    private void updateJumlah() {
        tvJumlahAnggota.setText(getString(R.string.jumlah_anggota) + ": " + anggotaSementara.size());
    }

    private void simpanDanExport() {
        String nomorKK = etNomorKK.getText().toString().trim();
        String alamat = etAlamat.getText().toString().trim();
        String kondisi = spKondisi.getSelectedItem().toString();
        String kelurahan = etKelurahan.getText().toString().trim();
        String kecamatan = etKecamatan.getText().toString().trim();

        if (nomorKK.isEmpty() || alamat.isEmpty() || kelurahan.isEmpty() || kecamatan.isEmpty()) {
            Toast.makeText(this, "Lengkapi data KK", Toast.LENGTH_SHORT).show();
            return;
        }

        KK kk = new KK(0, nomorKK, alamat, kondisi, kelurahan, kecamatan);
        long kkId = db.insertKK(kk);
        for (Anggota a : anggotaSementara) {
            a.setKkId(kkId);
            db.insertAnggota(a);
        }

        boolean ok = ExcelExporter.exportAllDataToExcel(this, db);
        if (ok) {
            Toast.makeText(this, "Disimpan dan diekspor ke " + ExcelExporter.exportPath(), Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Gagal ekspor", Toast.LENGTH_LONG).show();
        }
    }
}
