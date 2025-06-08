package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.BookController;
import model.Book;

public class TambahBukuForm extends JPanel {
    private JTextField tfTitle, tfAuthor, tfYear, tfImagePath, tfStock;
    private JTextArea taSynopsis;
    private JComboBox<String> cbPublisher;
    private JButton btnUploadImage, btnSave, btnCancel;
    private AdminDashboard dashboard;

    public TambahBukuForm(AdminDashboard dashboard) {
        this.dashboard = dashboard;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        setBackground(new Color(245, 248, 250));

        JLabel lblHeader = new JLabel("Tambah Buku Baru");
        lblHeader.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblHeader, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(getBackground());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        tfTitle = new JTextField(20);
        tfAuthor = new JTextField(20);
        tfYear = new JTextField(20);
        tfImagePath = new JTextField(15);
        tfImagePath.setEditable(false);
        tfStock = new JTextField(20);

        taSynopsis = new JTextArea(4, 20);
        taSynopsis.setLineWrap(true);
        taSynopsis.setWrapStyleWord(true);
        JScrollPane synopsisScroll = new JScrollPane(taSynopsis);

        cbPublisher = new JComboBox<>(new String[]{
            "PEN001 - Gramedia",
            "PEN002 - Erlangga",
            "PEN003 - Tiga Serangkai",
            "PEN004 - Mizan",
            "PEN005 - Andi Publisher"
        });

        btnUploadImage = new JButton("Pilih Gambar");
        btnUploadImage.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String fileName = selectedFile.getName();
                File destDir = new File("images");

                if (!destDir.exists()) {
                    destDir.mkdirs();
                }

                File destFile = new File(destDir, fileName);
                try {
                    Files.copy(
                        selectedFile.toPath(),
                        destFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING
                    );
                    tfImagePath.setText("images/" + fileName); // relative path
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Gagal menyimpan gambar: " + ex.getMessage());
                }
            }
        });

        addField(formPanel, gbc, 0, "Judul:", tfTitle);
        addField(formPanel, gbc, 1, "Pengarang:", tfAuthor);
        addField(formPanel, gbc, 2, "Tahun Terbit:", tfYear);
        addField(formPanel, gbc, 3, "Penerbit:", cbPublisher);
        addField(formPanel, gbc, 4, "Sinopsis:", synopsisScroll);
        addFieldWithButton(formPanel, gbc, 5, "Path Foto:", tfImagePath, btnUploadImage);
        addField(formPanel, gbc, 6, "Stok:", tfStock);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(getBackground());
        btnSave = new JButton("Simpan");
        btnCancel = new JButton("Batal");

        btnSave.setPreferredSize(new Dimension(120, 30));
        btnCancel.setPreferredSize(new Dimension(120, 30));

        btnSave.addActionListener(e -> saveBook());
        btnCancel.addActionListener(e -> {
            clearForm();
            dashboard.refreshMainPanel();
        });

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void saveBook() {
        String title = tfTitle.getText().trim();
        String author = tfAuthor.getText().trim();
        String tahunTerbitStr = tfYear.getText().trim();
        String synopsis = taSynopsis.getText().trim();
        String imagePath = tfImagePath.getText().trim();
        String stokStr = tfStock.getText().trim();

        String publisherItem = (String) cbPublisher.getSelectedItem();
        String publisher = publisherItem.split(" - ")[0];

        if (title.isEmpty() || author.isEmpty() || tahunTerbitStr.isEmpty() || publisher.isEmpty()
                || synopsis.isEmpty() || imagePath.isEmpty() || stokStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int tahunTerbit, stok;
        try {
            tahunTerbit = Integer.parseInt(tahunTerbitStr);
            stok = Integer.parseInt(stokStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tahun Terbit dan Stok harus angka!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Book newBook = new Book();
        newBook.setIdBuku(generateNewId());
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setPublisher(publisher);
        newBook.setTahunTerbit(tahunTerbit);
        newBook.setSynopsis(synopsis);
        newBook.setImagePath(imagePath); 
        newBook.setStok(stok);

        if (BookController.insertBook(newBook)) {
            JOptionPane.showMessageDialog(this, "Buku berhasil ditambahkan!");
            clearForm();
            dashboard.refreshMainPanel();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan buku.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        tfTitle.setText("");
        tfAuthor.setText("");
        tfYear.setText("");
        cbPublisher.setSelectedIndex(0);
        taSynopsis.setText("");
        tfImagePath.setText("");
        tfStock.setText("");
    }

    private String generateNewId() {
        String lastId = BookController.getLastIdBuku(); 
        if (lastId == null) return "BK001";
        try {
            int num = Integer.parseInt(lastId.substring(2)) + 1;
            return String.format("BK%03d", num);
        } catch (NumberFormatException e) {
            return "BK001";
        }
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int row, String label, Component field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void addFieldWithButton(JPanel panel, GridBagConstraints gbc, int row, String label, Component field, JButton button) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
        gbc.gridx = 2;
        panel.add(button, gbc);
    }
}