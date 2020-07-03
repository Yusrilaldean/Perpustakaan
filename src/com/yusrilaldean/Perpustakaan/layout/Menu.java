package com.yusrilaldean.Perpustakaan.layout;

import com.yusrilaldean.Perpustakaan.DBConnection;
import com.yusrilaldean.Perpustakaan.model.Buku;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Menu {
    private JPanel MainPanel;
    private JPanel Judul;
    private JTextField textFieldNomor;
    private JTextField textFieldJudul;
    private JTextField textFieldPenulis;
    private JTextField textFieldPenerbit;
    private JTextField textFieldJumlah;
    private JTextField textFieldTahun;
    private JTextField textFieldKategori;
    private JTextField textFieldKondisi;
    private JButton TAMBAHButton;
    private JButton UBAHButton;
    private JButton HAPUSButton;
    private JTable TableBuku;
    private JPanel Nomor;
    private JLabel JUDUL;
    private JPanel Kategori;
    private JPanel Kondisi;
    private JPanel Tahun;
    private JPanel Penerbit;
    private JPanel Penulis;
    private JPanel JudulBuku;
    private JPanel Tombol;
    private JButton REFRESHButton;

    private static DBConnection dbcon;
    private DefaultTableModel bukuTable = new DefaultTableModel();

    public Menu() {
        dbcon = new DBConnection();
        initBukuTable();

        TAMBAHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Judul = textFieldJudul.getText();
                    String Penulis = textFieldPenulis.getText();
                    String Penerbit = textFieldPenerbit.getText();
                    int Tahun = Integer.parseInt(textFieldTahun.getText());
                    int Jumlah = Integer.parseInt(textFieldJumlah.getText());
                    String Kategori = textFieldKategori.getText();
                    String Kondisi = textFieldKondisi.getText();

                    if (!Judul.isEmpty() && !Penulis.isEmpty() && !Penerbit.isEmpty() && !Kategori.isEmpty() && !Kondisi.isEmpty()) {
                        int buku = dbcon.insertBuku(Tahun, Jumlah, Judul, Penulis, Penerbit, Kondisi, Kategori);
                        if (buku != -1) {
                            clearTextField();
                            loadDataTable();
                            JOptionPane.showMessageDialog(null , "Success");
                        }
                    }
                } catch(Exception et){
                    System.out.println(et.getMessage());
                }
            }
        });
        UBAHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Judul = textFieldJudul.getText();
                String Penulis = textFieldPenulis.getText();
                String Penerbit = textFieldPenerbit.getText();
                int Tahun = Integer.parseInt(textFieldTahun.getText());
                int Jumlah = Integer.parseInt(textFieldJumlah.getText());
                String Kategori = textFieldKategori.getText();
                String Kondisi = textFieldKondisi.getText();

                if (!Judul.isEmpty() && !Penulis.isEmpty() && !Penerbit.isEmpty() && !Kategori.isEmpty() && !Kondisi.isEmpty()) {
                    int nomerBuku = Integer.parseInt(textFieldNomor.getText());
                    boolean buku = dbcon.updateBuku(Tahun, Jumlah, Judul, Penulis, Penerbit, Kondisi, Kategori,nomerBuku);
                    if (buku) {
                        clearTextField();
                        loadDataTable();
                        JOptionPane.showMessageDialog(null , "Success");
                    }
                }
            }
        });
        HAPUSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nomerBuku = Integer.parseInt(textFieldNomor.getText());
                boolean buku = dbcon.deleteBuku(nomerBuku);

                loadDataTable();
                clearTextField();
                JOptionPane.showMessageDialog(null , "Success");
            }
        });
        REFRESHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataTable();
            }
        });
    }

    private void clearTextField() {
        textFieldNomor.setText("");
        textFieldJudul.setText("");
        textFieldPenulis.setText("");
        textFieldPenerbit.setText("");;
        textFieldTahun.setText("");;
        textFieldJumlah.setText("");
        textFieldKategori.setText("");
        textFieldKondisi.setText("");
    }

    private void initBukuTable() {
        bukuTable.addColumn("Nomer Buku");
        bukuTable.addColumn("Judul");
        bukuTable.addColumn("Penulis");
        bukuTable.addColumn("Penerbit");
        bukuTable.addColumn("Tahun");
        bukuTable.addColumn("Jumlah");
        bukuTable.addColumn("Kategori");
        bukuTable.addColumn("Kondisi");

        loadDataTable();
        getProductTable().setModel(bukuTable);

        TableBuku.setCellSelectionEnabled(true);
        TableBuku.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (TableBuku.getSelectedRow() != -1) {
                    int selectedRow = TableBuku.getSelectedRow();
                    textFieldNomor.setText(String.valueOf(TableBuku.getValueAt(selectedRow, 0)));
                    textFieldJudul.setText(String.valueOf(TableBuku.getValueAt(selectedRow, 1)));
                    textFieldPenulis.setText(String.valueOf(TableBuku.getValueAt(selectedRow, 2)));
                    textFieldPenerbit.setText(String.valueOf(TableBuku.getValueAt(selectedRow, 3)));;
                    textFieldTahun.setText(String.valueOf(TableBuku.getValueAt(selectedRow, 4)));;
                    textFieldJumlah.setText(String.valueOf(TableBuku.getValueAt(selectedRow, 5)));
                    textFieldKategori.setText(String.valueOf(TableBuku.getValueAt(selectedRow, 6)));
                    textFieldKondisi.setText(String.valueOf(TableBuku.getValueAt(selectedRow, 7)));
                }
            }
        });
    }

    private void loadDataTable() {
        clearProductTable();
        List<Buku> bukuList = dbcon.selectAllBuku();

        for (Buku buku: bukuList) {
            bukuTable.addRow(new String[] {
                    String.valueOf(buku.getNomerBuku()),
                    buku.getJudul(),
                    buku.getPenulis(),
                    buku.getPenerbit(),
                    String.valueOf(buku.getTahunTerbit()),
                    String.valueOf(buku.getJumlahBuku()),
                    buku.getKategoriBuku(),
                    buku.getKondisiBuku(),
                    });
        }

        getProductTable().setModel(bukuTable);
    }

    private void clearProductTable() {
        bukuTable.setRowCount(0);
        getProductTable().setModel(bukuTable);
    }

    private JTable getProductTable() {
        return TableBuku;
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        MainPanel = mainPanel;
    }

    public JPanel getJudul() {
        return Judul;
    }

    public void setJudul(JPanel judul) {
        Judul = judul;
    }

    public JTextField getTextFieldNomor() {
        return textFieldNomor;
    }

    public void setTextFieldNomor(JTextField textFieldNomor) {
        this.textFieldNomor = textFieldNomor;
    }

    public JTextField getTextFieldJudul() {
        return textFieldJudul;
    }

    public void setTextFieldJudul(JTextField textFieldJudul) {
        this.textFieldJudul = textFieldJudul;
    }

    public JTextField getTextFieldPenulis() {
        return textFieldPenulis;
    }

    public void setTextFieldPenulis(JTextField textFieldPenulis) {
        this.textFieldPenulis = textFieldPenulis;
    }

    public JTextField getTextFieldPenerbit() {
        return textFieldPenerbit;
    }

    public void setTextFieldPenerbit(JTextField textFieldPenerbit) {
        this.textFieldPenerbit = textFieldPenerbit;
    }

    public JTextField getTextFieldJumlah() {
        return textFieldJumlah;
    }

    public void setTextFieldJumlah(JTextField textFieldJumlah) {
        this.textFieldJumlah = textFieldJumlah;
    }

    public JTextField getTextFieldTahun() {
        return textFieldTahun;
    }

    public void setTextFieldTahun(JTextField textFieldTahun) {
        this.textFieldTahun = textFieldTahun;
    }

    public JTextField getTextFieldKategori() {
        return textFieldKategori;
    }

    public void setTextFieldKategori(JTextField textFieldKategori) {
        this.textFieldKategori = textFieldKategori;
    }

    public JTextField getTextFieldKondisi() {
        return textFieldKondisi;
    }

    public void setTextFieldKondisi(JTextField textFieldKondisi) {
        this.textFieldKondisi = textFieldKondisi;
    }

    public JButton getTAMBAHButton() {
        return TAMBAHButton;
    }

    public void setTAMBAHButton(JButton TAMBAHButton) {
        this.TAMBAHButton = TAMBAHButton;
    }

    public JButton getUBAHButton() {
        return UBAHButton;
    }

    public void setUBAHButton(JButton UBAHButton) {
        this.UBAHButton = UBAHButton;
    }

    public JButton getHAPUSButton() {
        return HAPUSButton;
    }

    public void setHAPUSButton(JButton HAPUSButton) {
        this.HAPUSButton = HAPUSButton;
    }

    public JTable getTableBuku() {
        return TableBuku;
    }

    public void setTableBuku(JTable tableBuku) {
        TableBuku = tableBuku;
    }

    public JPanel getNomor() {
        return Nomor;
    }

    public void setNomor(JPanel nomor) {
        Nomor = nomor;
    }

    public JLabel getJUDUL() {
        return JUDUL;
    }

    public void setJUDUL(JLabel JUDUL) {
        this.JUDUL = JUDUL;
    }

    public JPanel getKategori() {
        return Kategori;
    }

    public void setKategori(JPanel kategori) {
        Kategori = kategori;
    }

    public JPanel getKondisi() {
        return Kondisi;
    }

    public void setKondisi(JPanel kondisi) {
        Kondisi = kondisi;
    }

    public JPanel getTahun() {
        return Tahun;
    }

    public void setTahun(JPanel tahun) {
        Tahun = tahun;
    }

    public JPanel getPenerbit() {
        return Penerbit;
    }

    public void setPenerbit(JPanel penerbit) {
        Penerbit = penerbit;
    }

    public JPanel getPenulis() {
        return Penulis;
    }

    public void setPenulis(JPanel penulis) {
        Penulis = penulis;
    }

    public JPanel getJudulBuku() {
        return JudulBuku;
    }

    public void setJudulBuku(JPanel judulBuku) {
        JudulBuku = judulBuku;
    }

    public JPanel getTombol() {
        return Tombol;
    }

    public void setTombol(JPanel tombol) {
        Tombol = tombol;
    }

    public JButton getREFRESHButton() {
        return REFRESHButton;
    }

    public void setREFRESHButton(JButton REFRESHButton) {
        this.REFRESHButton = REFRESHButton;
    }
}
