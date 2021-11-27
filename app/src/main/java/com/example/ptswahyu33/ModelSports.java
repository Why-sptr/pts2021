package com.example.ptswahyu33;

public class ModelSports {

    private String image;
    private String nama;
    private String deskripsi;

    public ModelSports(String image, String nama, String deskripsi) {
        this.image = image;
        this.nama = nama;
        this.deskripsi = deskripsi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
