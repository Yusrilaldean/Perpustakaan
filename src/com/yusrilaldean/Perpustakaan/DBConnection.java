package com.yusrilaldean.Perpustakaan;
//package db;
import com.yusrilaldean.Perpustakaan.model.Akun;
import com.yusrilaldean.Perpustakaan.model.Buku;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    // Database credentials
    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String dbUrl = "jdbc:mysql://localhost/perpustakaan";
    private static final String username = "root";
    private static final String password = "";
    private static Connection con;

    private static Buku databuku = null;
    private static ResultSet resultSet = null;
    private static PreparedStatement statement = null;

    private Akun akun;

    public Akun getAkun(){
        return  akun;
    }
    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(dbUrl, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found.");
        }
        return con;
    }

    public boolean loginAuth(String Username, String Password){
        String query = "SELECT * FROM Akun WHERE Username  = ? AND Password = ?";
        boolean result = false;

        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Username);
            ps.setString(2, Password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                akun = new Akun(rs.getInt("ID"), rs.getString("NAMA"));
                result = true;
            }else{
                JOptionPane.showMessageDialog(null , "Password Salah");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    return result;
    }
    public int insertBuku(int tahunTerbit, int jumlahBuku, String judul, String penulis, String penerbit, String kondisiBuku, String kategoriBuku) {
        String query = "insert into buku(Judul, Penulis, Penerbit, TahunTerbit, JumlahBuku, KondisiBuku, KategoriBuku) VALUES (?,?,?,?,?,?,?)";
        try {
            statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, judul);
            statement.setString(2, penulis);
            statement.setString(3, penerbit);
            statement.setInt(4, tahunTerbit);
            statement.setInt(5, jumlahBuku);
            statement.setString(6, kondisiBuku);
            statement.setString(7, kategoriBuku);

            int rowAffected = statement.executeUpdate();
            if (rowAffected == 1) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null , "Error");
        }
        return -1;
    }
    public boolean updateBuku(int tahunTerbit, int jumlahBuku, String judul, String penulis, String penerbit, String kondisiBuku, String kategoriBuku, int nomerBuku) {
        String query = "update buku set Judul =?, Penulis=?, Penerbit=?, TahunTerbit=?, JumlahBuku=?, KondisiBuku=?, KategoriBuku=? where NomerBuku = ?";
        try {
            statement = con.prepareStatement(query);
            statement.setString(1, judul);
            statement.setString(2, penulis);
            statement.setString(3, penerbit);
            statement.setInt(4, tahunTerbit);
            statement.setInt(5, jumlahBuku);
            statement.setString(6, kondisiBuku);
            statement.setString(7, kategoriBuku);
            statement.setInt(8, nomerBuku);

            int rowAffected = statement.executeUpdate();
            if (rowAffected == 1)
                return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null , "Error");
        }
        return false;
    }
    public boolean deleteBuku(int nomerBuku) {
        String query = "delete from buku where NomerBuku = ?";
        try {
            statement = con.prepareStatement(query);
            statement.setInt(1, nomerBuku);

            int rowAffected = statement.executeUpdate();
            if (rowAffected == 1)
                return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null , "Error");
        }
        return false;
    }

    public List<Buku> selectAllBuku() {
        List<Buku> dataQuery = new ArrayList<>();
        String query = "select * from buku";
        try {
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Buku data = new Buku(
                        resultSet.getInt("NomerBuku"),
                        resultSet.getInt("TahunTerbit"),
                        resultSet.getInt("JumlahBuku"),
                        resultSet.getString("Judul"),
                        resultSet.getString("Penulis"),
                        resultSet.getString("Penerbit"),
                        resultSet.getString("KondisiBuku"),
                        resultSet.getString("KategoriBuku")
                );
                dataQuery.add(data);
            }

            return dataQuery;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null , "Error");
        }
        return dataQuery;
    }

    public int daftarAkun(String nama, String username, String password, String domisili, String alamat) {
        String query = "insert into akun(Nama, Username, Password, DOMISILI, ALAMAT) VALUES (?,?,?,?,?)";
        try {
            statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, nama);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, domisili);
            statement.setString(5, alamat);

            int rowAffected = statement.executeUpdate();
            if (rowAffected == 1) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null , "Error");
        }
        return -1;
    }
}
