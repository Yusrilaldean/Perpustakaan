package com.yusrilaldean.Perpustakaan;

import com.yusrilaldean.Perpustakaan.layout.Daftar;
import com.yusrilaldean.Perpustakaan.layout.Login;
import com.yusrilaldean.Perpustakaan.layout.Menu;

import javax.swing.*;

public class Main {
    private static Login loginForm = new Login();
    private static Daftar daftarForm = new Daftar();
    private static Menu menuForm;
    private static DBConnection dbcon = new DBConnection();

    public static void main(String[] args) {
        dbcon.getConnection();
        menuForm = new Menu();

        //Halaman Login
        initLogin();

        //Halaman Daftar
        loginForm.getDaftarButton().addActionListener(e->{
            initDaftar();
        });
        loginForm.getMasukButton1().addActionListener(e->{
            String Username = loginForm.getTextFieldUsername().getText();
            String Password = String.valueOf(loginForm.getPasswordField1().getPassword());
            try {
                int jawabanCPt = Integer.parseInt(loginForm.getCaptchaJawab().getText());

                if (jawabanCPt == loginForm.getJawabanChaptcha()) {
                    if(!(Username.isEmpty() && Password.isEmpty())){
                        if(dbcon.loginAuth(Username,Password)){
                            System.out.println("Hey, " + dbcon.getAkun().getNama());
                            initMenu();
                        }
                    }else{
                        System.out.println("Masukan form");
                    }
                } else {
                    JOptionPane.showMessageDialog(null , "Jawaban salah");
                }
            } catch (Exception bb) {
                System.out.println(bb.getMessage());
                JOptionPane.showMessageDialog(null , "Masukan Angka");
            }
        });
    }

    private static void initLogin(){
        JFrame frame = new JFrame("Data Buku Perpustakaan");
        frame.setContentPane(loginForm.getLoginUtama());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private static void initDaftar(){
        JFrame frame = new JFrame("Daftar Member");
        frame.setContentPane(daftarForm.getPanelDaftar());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private static void initMenu(){
        JFrame frame = new JFrame("Perpustakaan");
        frame.setContentPane(menuForm.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
