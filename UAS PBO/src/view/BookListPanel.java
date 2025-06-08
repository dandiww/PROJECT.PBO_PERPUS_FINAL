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

import controller.BookController;
import model.Book;

public class BookListPanel extends JPanel {
    private JTable bookTable;
    private DefaultTableModel model;
    private List<Book> daftarBuku;  // Simpan list buku untuk akses objek

    public BookListPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Daftar Buku"));

        loadBooks();
    }

    public void loadBooks() {
        daftarBuku = BookController.getAllBooks();

        String[] kolom = {"ID Buku", "Judul", "Pengarang", "Penerbit", "Tahun Terbit", "Stok"};
        Object[][] data = new Object[daftarBuku.size()][kolom.length];

        for (int i = 0; i < daftarBuku.size(); i++) {
            Book b = daftarBuku.get(i);
            data[i][0] = b.getIdBuku();
            data[i][1] = b.getTitle();
            data[i][2] = b.getAuthor();
            data[i][3] = b.getPublisher();
            data[i][4] = b.getTahunTerbit();
            data[i][5] = b.getStok();
        }

        model = new DefaultTableModel(data, kolom) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Semua kolom tidak bisa diedit
            }
        };

        if (bookTable == null) {
            bookTable = new JTable(model);
            bookTable.setFont(new Font("Arial", Font.PLAIN, 14));
            bookTable.setRowHeight(40);


            bookTable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus,
                                                               int row, int column) {
                    JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    label.setBackground(new Color(64, 224, 208)); // Warna hijau toska
                    label.setForeground(Color.BLACK);
                    label.setFont(label.getFont().deriveFont(Font.BOLD));
                    label.setHorizontalAlignment(CENTER);
                    label.setOpaque(true);
                    return label;
                }
            });

            JScrollPane scrollPane = new JScrollPane(bookTable);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);

            removeAll();
            add(scrollPane, BorderLayout.CENTER);
        } else {
            bookTable.setModel(model);
        }

        revalidate();
        repaint();
    }

    public int getSelectedRow() {
        if (bookTable != null) {
            return bookTable.getSelectedRow();
        }
        return -1;
    }

    public Book getSelectedBook() {
        int row = getSelectedRow();
        if (row == -1 || daftarBuku == null || daftarBuku.size() <= row) {
            return null;
        }
        return daftarBuku.get(row);
    }

    public void reloadData() {
        loadBooks();
    }
}
