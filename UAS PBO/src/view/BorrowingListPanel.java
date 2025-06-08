package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.BorrowingController;
import model.Borrowing;

public class BorrowingListPanel extends JPanel {
    private JTable peminjamanTable;
    private DefaultTableModel model;
    private List<Borrowing> daftarPinjam;

    public BorrowingListPanel() {
        this(""); 
    }

    public BorrowingListPanel(String userId) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Daftar Peminjaman"));

        loadBorrowings(userId);
    }

    public void loadBorrowings(String userId) {
        daftarPinjam = BorrowingController.getRiwayat(userId);

        String[] kolom = {"ID Peminjaman", "User ID", "Book ID", "Tanggal Pinjam", "Jatuh Tempo", "Status"};
        Object[][] data = new Object[daftarPinjam.size()][kolom.length];

        for (int i = 0; i < daftarPinjam.size(); i++) {
            Borrowing b = daftarPinjam.get(i);
            data[i][0] = b.getIdPeminjaman();
            data[i][1] = b.getUserId();
            data[i][2] = b.getBookId();
            data[i][3] = b.getTanggalPinjam();
            data[i][4] = b.getTanggalJatuhTempo();
            data[i][5] = b.getStatus();
        }

        model = new DefaultTableModel(data, kolom) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        if (peminjamanTable == null) {
            peminjamanTable = new JTable(model);
            peminjamanTable.setFont(new Font("Arial", Font.PLAIN, 14));
            peminjamanTable.setRowHeight(40);

            peminjamanTable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, 
                                                              boolean isSelected, boolean hasFocus, 
                                                              int row, int column) {
                    JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    label.setBackground(new Color(64, 224, 208));
                    label.setForeground(Color.BLACK);
                    label.setFont(label.getFont().deriveFont(Font.BOLD));
                    label.setHorizontalAlignment(CENTER);
                    label.setOpaque(true);
                    return label;
                }
            });

            JScrollPane scrollPane = new JScrollPane(peminjamanTable);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);

            removeAll();
            add(scrollPane, BorderLayout.CENTER);
        } else {
            peminjamanTable.setModel(model);
        }

        revalidate();
        repaint();
    }

    public int getSelectedRow() {
        if (peminjamanTable != null) {
            return peminjamanTable.getSelectedRow();
        }
        return -1;
    }

    public Borrowing getSelectedBorrowing() {
        int row = getSelectedRow();
        if (row == -1 || daftarPinjam == null || daftarPinjam.size() <= row) {
            return null;
        }
        return daftarPinjam.get(row);
    }

    public void reloadData() {
        loadBorrowings("");
    }
}
