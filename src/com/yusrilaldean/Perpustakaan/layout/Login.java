package com.yusrilaldean.Perpustakaan.layout;

import com.yusrilaldean.Perpustakaan.Captcha;

import javax.swing.*;

public class Login {
    private JPanel LoginUtama;
    private JLabel Login;
    private JTextField textFieldUsername;
    private JButton masukButton1;
    private JButton daftarButton;
    private JPasswordField passwordField1;
    private JLabel labelCaptcha;
    private JTextField captchaJawab;

    int jawabanChaptcha = 0;
    int captchaA = 0;
    int captchaB = 0;

    public int getJawabanChaptcha() {
        return jawabanChaptcha;
    }

    public JTextField getCaptchaJawab() {
        return captchaJawab;
    }

    public Login() {
        captchaA = (int) (Math.random()*((100-10)+1))+10;
        captchaB = (int) (Math.random()*((100-10)+1))+10;
        jawabanChaptcha = new Captcha(captchaA, captchaB).hitungPlus();

        labelCaptcha.setText(captchaA + " + " + captchaB);
    }

    public JPanel getLoginUtama() {
        return LoginUtama;
    }

    public void setLoginUtama(JPanel loginUtama) {
        LoginUtama = loginUtama;
    }

    public JLabel getLogin() {
        return Login;
    }

    public void setLogin(JLabel login) {
        Login = login;
    }

    public JTextField getTextFieldUsername() {
        return textFieldUsername;
    }

    public void setTextFieldUsername(JTextField textFieldUsername) {
        this.textFieldUsername = textFieldUsername;
    }

    public JButton getMasukButton1() {
        return masukButton1;
    }

    public void setMasukButton1(JButton masukButton1) {
        this.masukButton1 = masukButton1;
    }

    public JButton getDaftarButton() {
        return daftarButton;
    }

    public void setDaftarButton(JButton daftarButton) {
        this.daftarButton = daftarButton;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public void setPasswordField1(JPasswordField passwordField1) {
        this.passwordField1 = passwordField1;
    }
}
