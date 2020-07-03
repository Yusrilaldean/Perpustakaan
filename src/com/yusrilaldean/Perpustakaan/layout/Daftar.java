package com.yusrilaldean.Perpustakaan.layout;

import com.yusrilaldean.Perpustakaan.DBConnection;
import com.yusrilaldean.Perpustakaan.DaftarMetode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Daftar implements DaftarMetode {
    private JPanel panelDaftar;
    private JTextField DaftarNama;
    private JTextField DaftarUsername;
    private JTextField DaftarPassword;
    private JTextField DaftarTTL;
    private JButton DAFTARButton;
    private JTextArea DaftarAlamat;

    private static DBConnection dbcon;
    public Daftar() {
        dbcon = new DBConnection();
        DAFTARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Nama = DaftarNama.getText();
                String Username = DaftarUsername.getText();
                String Password = DaftarPassword.getText();
                String Domisili = DaftarTTL.getText();
                String Alamat = DaftarAlamat.getText();


                if (!Nama.isEmpty() && !Username.isEmpty() && !Password.isEmpty() && !Domisili.isEmpty() && !Alamat.isEmpty()) {
                    int akun = dbcon.daftarAkun(Nama, Username, Password, Domisili, Alamat);
                    if (akun != -1) {
                        clearTextField();
                        JOptionPane.showMessageDialog(null , "Success");
                    }
                }
            }
        });
    }

    public JPanel getPanelDaftar() {
        return panelDaftar;
    }

    @Override
    public void clearTextField() {
        DaftarNama.setText("");
        DaftarUsername.setText("");
        DaftarPassword.setText("");
        DaftarTTL.setText("");
        DaftarAlamat.setText("");
    }
}
