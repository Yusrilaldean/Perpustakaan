package com.yusrilaldean.Perpustakaan.model;

public class Akun {
    private int id;
    private String Nama;
    private String Username;
    private String Password;
    private String Domisili;
    private String Alamat;

    public Akun(int id, String nama) {
        this.id = id;
        this.Nama = nama;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return Nama;
    }

    public Akun(String nama, String username, String password, String domisili, String alamat) {
        Nama = nama;
        Username = username;
        Password = password;
        Domisili = domisili;
        Alamat = alamat;
    }
}
