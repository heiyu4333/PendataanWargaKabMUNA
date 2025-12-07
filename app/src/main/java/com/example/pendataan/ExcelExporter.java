package com.example.pendataan;

import android.content.Context;
import android.os.Environment;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ExcelExporter {
    public static String exportPath() {
        return "/storage/emulated/0/PendataanWarga/pendataan_warga.xlsx";
    }

    public static boolean exportAllDataToExcel(Context context, DatabaseHelper dbHelper) {
        try {
            File dir = new File(Environment.getExternalStorageDirectory(), "PendataanWarga");
            if (!dir.exists()) dir.mkdirs();
            File file = new File(dir, "pendataan_warga.xlsx");

            XSSFWorkbook workbook = new XSSFWorkbook();

            Sheet sheetKK = workbook.createSheet("Data KK");
            Row headerKK = sheetKK.createRow(0);
            headerKK.createCell(0).setCellValue("id");
            headerKK.createCell(1).setCellValue("nomor_kk");
            headerKK.createCell(2).setCellValue("alamat");
            headerKK.createCell(3).setCellValue("kondisi_rumah");
            headerKK.createCell(4).setCellValue("kelurahan");
            headerKK.createCell(5).setCellValue("kecamatan");

            List<KK> kks = dbHelper.getAllKK();
            int r = 1;
            for (KK kk : kks) {
                Row row = sheetKK.createRow(r++);
                row.createCell(0).setCellValue(kk.getId());
                row.createCell(1).setCellValue(kk.getNomorKK());
                row.createCell(2).setCellValue(kk.getAlamat());
                row.createCell(3).setCellValue(kk.getKondisiRumah());
                row.createCell(4).setCellValue(kk.getKelurahan());
                row.createCell(5).setCellValue(kk.getKecamatan());
            }

            Sheet sheetAnggota = workbook.createSheet("Data Anggota Keluarga");
            Row headerA = sheetAnggota.createRow(0);
            headerA.createCell(0).setCellValue("id");
            headerA.createCell(1).setCellValue("kk_id");
            headerA.createCell(2).setCellValue("nik");
            headerA.createCell(3).setCellValue("nama");
            headerA.createCell(4).setCellValue("tanggal_lahir");
            headerA.createCell(5).setCellValue("hubungan");
            headerA.createCell(6).setCellValue("kesehatan");
            headerA.createCell(7).setCellValue("pendidikan");

            List<Anggota> anggotaList = dbHelper.getAllAnggota();
            int ra = 1;
            for (Anggota a : anggotaList) {
                Row row = sheetAnggota.createRow(ra++);
                row.createCell(0).setCellValue(a.getId());
                row.createCell(1).setCellValue(a.getKkId());
                row.createCell(2).setCellValue(a.getNik());
                row.createCell(3).setCellValue(a.getNama());
                row.createCell(4).setCellValue(a.getTanggalLahir());
                row.createCell(5).setCellValue(a.getHubungan());
                row.createCell(6).setCellValue(a.getKesehatan());
                row.createCell(7).setCellValue(a.getPendidikan());
            }

            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.flush();
            fos.close();
            workbook.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
