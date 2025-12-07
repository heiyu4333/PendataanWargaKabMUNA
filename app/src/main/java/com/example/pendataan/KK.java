package com.example.pendataan;

public class KK {
    private long id;
    private String nomorKK;
    private String alamat;
    private String kondisiRumah;
    private String kelurahan;
    private String kecamatan;

    public KK() {}

    public KK(long id, String nomorKK, String alamat, String kondisiRumah, String kelurahan, String kecamatan) {
        this.id = id;
        this.nomorKK = nomorKK;
        this.alamat = alamat;
        this.kondisiRumah = kondisiRumah;
        this.kelurahan = kelurahan;
        this.kecamatan = kecamatan;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getNomorKK() { return nomorKK; }
    public void setNomorKK(String nomorKK) { this.nomorKK = nomorKK; }
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public String getKondisiRumah() { return kondisiRumah; }
    public void setKondisiRumah(String kondisiRumah) { this.kondisiRumah = kondisiRumah; }
    public String getKelurahan() { return kelurahan; }
    public void setKelurahan(String kelurahan) { this.kelurahan = kelurahan; }
    public String getKecamatan() { return kecamatan; }
    public void setKecamatan(String kecamatan) { this.kecamatan = kecamatan; }
}
