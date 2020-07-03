package com.yusrilaldean.Perpustakaan.model;

public class Buku {
    int NomerBuku, TahunTerbit, JumlahBuku;
    String Judul, Penulis, Penerbit, KondisiBuku, KategoriBuku;

    public Buku(int nomerBuku, int tahunTerbit, int jumlahBuku, String judul, String penulis, String penerbit, String kondisiBuku, String kategoriBuku) {
        NomerBuku = nomerBuku;
        TahunTerbit = tahunTerbit;
        JumlahBuku = jumlahBuku;
        Judul = judul;
        Penulis = penulis;
        Penerbit = penerbit;
        KondisiBuku = kondisiBuku;
        KategoriBuku = kategoriBuku;
    }

    public int getNomerBuku() {
        return NomerBuku;
    }

    public void setNomerBuku(int nomerBuku) {
        NomerBuku = nomerBuku;
    }

    public int getTahunTerbit() {
        return TahunTerbit;
    }

    public void setTahunTerbit(int tahunTerbit) {
        TahunTerbit = tahunTerbit;
    }

    public int getJumlahBuku() {
        return JumlahBuku;
    }

    public void setJumlahBuku(int jumlahBuku) {
        JumlahBuku = jumlahBuku;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String judul) {
        Judul = judul;
    }

    public String getPenulis() {
        return Penulis;
    }

    public void setPenulis(String penulis) {
        Penulis = penulis;
    }

    public String getPenerbit() {
        return Penerbit;
    }

    public void setPenerbit(String penerbit) {
        Penerbit = penerbit;
    }

    public String getKondisiBuku() {
        return KondisiBuku;
    }

    public void setKondisiBuku(String kondisiBuku) {
        KondisiBuku = kondisiBuku;
    }

    public String getKategoriBuku() {
        return KategoriBuku;
    }

    public void setKategoriBuku(String kategoriBuku) {
        KategoriBuku = kategoriBuku;
    }
}
