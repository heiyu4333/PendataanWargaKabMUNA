package com.example.pendataan;

public class Anggota {
    private long id;
    private long kkId;
    private String nik;
    private String nama;
    private String tanggalLahir;
    private String hubungan;
    private String kesehatan;
    private String pendidikan;

    public Anggota() {}

    public Anggota(long id, long kkId, String nik, String nama, String tanggalLahir, String hubungan, String kesehatan, String pendidikan) {
        this.id = id;
        this.kkId = kkId;
        this.nik = nik;
        this.nama = nama;
        this.tanggalLahir = tanggalLahir;
        this.hubungan = hubungan;
        this.kesehatan = kesehatan;
        this.pendidikan = pendidikan;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public long getKkId() { return kkId; }
    public void setKkId(long kkId) { this.kkId = kkId; }
    public String getNik() { return nik; }
    public void setNik(String nik) { this.nik = nik; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getTanggalLahir() { return tanggalLahir; }
    public void setTanggalLahir(String tanggalLahir) { this.tanggalLahir = tanggalLahir; }
    public String getHubungan() { return hubungan; }
    public void setHubungan(String hubungan) { this.hubungan = hubungan; }
    public String getKesehatan() { return kesehatan; }
    public void setKesehatan(String kesehatan) { this.kesehatan = kesehatan; }
    public String getPendidikan() { return pendidikan; }
    public void setPendidikan(String pendidikan) { this.pendidikan = pendidikan; }
}
