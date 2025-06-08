package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.BorrowingController;
import model.Book;

public class BookCard extends JPanel {
    private Book book;
    private String userId;
    private UserDashboard dashboard;
    private JButton pinjamButton;

    public BookCard(Book book, String userId, UserDashboard dashboard) {
        this.book = book;
        this.userId = userId;
        this.dashboard = dashboard;

        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(400, 260)); 
        setBackground(Color.WHITE);
        setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(12, 12, 12, 12)
        ));

        JPanel header = new JPanel(new GridLayout(2, 1));
        header.setOpaque(false);
        JLabel titleLabel = new JLabel(book.getTitle());
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        JLabel authorLabel = new JLabel(" " + book.getAuthor());
        authorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        authorLabel.setForeground(new Color(60, 60, 60));
        header.add(titleLabel);
        header.add(authorLabel);
        add(header, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.TOP);

        if (book.getImagePath() != null && !book.getImagePath().trim().isEmpty()) {
            File imgFile = new File(book.getImagePath());
            if (imgFile.exists()) {
                try {
                    ImageIcon icon = new ImageIcon(book.getImagePath());
                    int maxWidth = 110;
                    int maxHeight = 300;
                    Image scaledImg = getScaledImagePreserveRatio(icon.getImage(), maxWidth, maxHeight);
                    imageLabel.setIcon(new ImageIcon(scaledImg));
                } catch (Exception e) {
                    imageLabel.setText("⚠ Gagal memuat gambar");
                }
            } else {
                imageLabel.setText("Gambar tidak ditemukan");
            }
        } else {
            imageLabel.setText("Tidak ada gambar");
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 12);
        centerPanel.add(imageLabel, gbc);

        String sinopsis = (book.getSynopsis() != null) ? book.getSynopsis() : "Tidak ada sinopsis.";
        JTextArea synopsisArea = new JTextArea(sinopsis);
        synopsisArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        synopsisArea.setForeground(new Color(60, 60, 60));
        synopsisArea.setBackground(null);
        synopsisArea.setLineWrap(true);
        synopsisArea.setWrapStyleWord(true);
        synopsisArea.setEditable(false);
        synopsisArea.setFocusable(false);
        synopsisArea.setOpaque(false);
        synopsisArea.setBorder(null);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(synopsisArea, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Tombol Pinjam
        pinjamButton = new JButton(" Pinjam");
        pinjamButton.setBackground(new Color(0, 153, 0));
        pinjamButton.setForeground(Color.WHITE);
        pinjamButton.setFocusPainted(false);
        pinjamButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        pinjamButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pinjamButton.setPreferredSize(new Dimension(100, 32));
        pinjamButton.addActionListener(e -> pinjamBuku());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(pinjamButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private Image getScaledImagePreserveRatio(Image srcImg, int maxW, int maxH) {
        int srcWidth = srcImg.getWidth(null);
        int srcHeight = srcImg.getHeight(null);

        if (srcWidth <= 0 || srcHeight <= 0) return srcImg;

        double scale = Math.min((double) maxW / srcWidth, (double) maxH / srcHeight);
        int newW = (int) (srcWidth * scale);
        int newH = (int) (srcHeight * scale);

        return srcImg.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    }

    private void pinjamBuku() {
        int result = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin meminjam buku:\n" + book.getTitle() + "?",
                "Konfirmasi Peminjaman", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            boolean success = BorrowingController.pinjamBuku(userId, book.getIdBuku());
            if (success) {
                JOptionPane.showMessageDialog(this, " Berhasil meminjam buku!");
                pinjamButton.setEnabled(false);
                dashboard.refreshBooks();
            } else {
                JOptionPane.showMessageDialog(this, " Gagal meminjam buku.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}