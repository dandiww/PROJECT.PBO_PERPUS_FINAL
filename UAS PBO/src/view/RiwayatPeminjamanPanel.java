package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URI;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import controller.BorrowingController;
import controller.BookController;
import model.Borrowing;
import model.Book;
import model.User;

public class RiwayatPeminjamanPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private User user;

    private final Color PRIMARY_COLOR = new Color(36, 52, 92); // #24345C
    private final Color BG_COLOR = new Color(245, 245, 245);

    private JLabel emptyLabel;

    public RiwayatPeminjamanPanel(User user) {
        this.user = user;
        setLayout(new BorderLayout());
        setBackground(BG_COLOR);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // Title dan Tombol Refresh
        JLabel titleLabel = new JLabel("Riwayat Peminjaman Anda");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        refreshButton.setBackground(new Color(0, 153, 0));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BG_COLOR);
        topPanel.add(titleLabel, BorderLayout.CENTER);
        topPanel.add(refreshButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Tabel
        model = new DefaultTableModel(new String[]{"ID", "ID Buku", "Nama Buku", "Tanggal Pinjam", "Tanggal Kembali", "Status", "Detail"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };

        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(PRIMARY_COLOR);
        table.getTableHeader().setForeground(Color.WHITE);

        table.getColumn("Detail").setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof JButton) {
                    return (JButton) value;
                }
                return new JButton("Lihat");
            }
        });

        table.getColumn("Detail").setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            private JButton button;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                if (value instanceof JButton) {
                    button = (JButton) value;
                    return button;
                }
                return new JButton("Lihat");
            }

            @Override
            public Object getCellEditorValue() {
                return button;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(scrollPane, BorderLayout.CENTER);

        emptyLabel = new JLabel("Belum ada riwayat peminjaman.");
        emptyLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        emptyLabel.setForeground(new Color(120, 120, 120));
        emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        emptyLabel.setVisible(false);
        add(emptyLabel, BorderLayout.SOUTH);

        loadData();
    }
    
    private void loadData() {
        model.setRowCount(0);
        List<Borrowing> list = BorrowingController.getRiwayat(user.getIdUser());

        if (list.isEmpty()) {
            emptyLabel.setVisible(true);
            table.setVisible(false);
            table.getTableHeader().setVisible(false);
        } else {
            emptyLabel.setVisible(false);
            table.setVisible(true);
            table.getTableHeader().setVisible(true);

            for (Borrowing b : list) {
                Book book = BookController.getBookById(b.getBookId());
                String namaBuku = (book != null && book.getTitle() != null) ? book.getTitle() : "(Tidak ditemukan)";
                String fileUrl = (book != null && book.getFilePath() != null) ? book.getFilePath() : null;

                boolean isExpired = b.isExpired();

                JButton detailButton = new JButton("Lihat");
                detailButton.setBackground(PRIMARY_COLOR);
                detailButton.setForeground(Color.WHITE);
                detailButton.setFocusPainted(false);
                detailButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));

                if (isExpired) {
                    detailButton.setEnabled(false);
                    detailButton.setToolTipText("E-book tidak tersedia karena peminjaman sudah kadaluarsa.");
                } else {
                    detailButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (fileUrl != null && !fileUrl.isEmpty()) {
                                try {
                                    Desktop.getDesktop().browse(new URI(fileUrl));
                                } catch (Exception ex) {
                                  JOptionPane.showMessageDialog(null, "Gagal membuka link e-book: " + ex.getMessage());
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "URL e-book belum tersedia.");
                            }
                        }
                    });
                }

                model.addRow(new Object[]{
                        b.getIdPeminjaman(),
                        b.getBookId(),
                        namaBuku,
                        b.getTanggalPinjam(),
                        b.getTanggalJatuhTempo(),
                        isExpired ? "Kadaluarsa" : "Aktif",
                        detailButton
                });
            }
        }

        revalidate();
        repaint();
    }

    public void refresh() {
        loadData();
    }
}
