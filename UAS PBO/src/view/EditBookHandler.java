package view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import controller.BookController;
import model.Book;

public class EditBookHandler {

    public static void editBook(AdminDashboard dashboard, BookListPanel bookListPanel) {
        Book selected = bookListPanel.getSelectedBook();
        if (selected == null) {
            JOptionPane.showMessageDialog(dashboard, "Pilih buku yang ingin diedit terlebih dahulu.");
            return;
        }

        // Komponen input
        JTextField tfTitle = new JTextField(selected.getTitle());
        JTextField tfAuthor = new JTextField(selected.getAuthor());
        JTextField tfYear = new JTextField(String.valueOf(selected.getTahunTerbit()));
        JComboBox<String> cbPublisher = new JComboBox<>(new String[] {
            "PEN001 - Gramedia",
            "PEN002 - Erlangga",
            "PEN003 - Tiga Serangkai",
            "PEN004 - Mizan",
            "PEN005 - Andi Publisher"
        });
        tfPublisherSelect(cbPublisher, selected.getPublisher());

        JTextField tfSynopsis = new JTextField(selected.getSynopsis());
        JTextField tfImagePath = new JTextField(selected.getImagePath());
        JTextField tfStock = new JTextField(String.valueOf(selected.getStok()));

        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.GridLayout(0, 1));
        panel.add(new javax.swing.JLabel("Judul:"));
        panel.add(tfTitle);
        panel.add(new javax.swing.JLabel("Pengarang:"));
        panel.add(tfAuthor);
        panel.add(new javax.swing.JLabel("Tahun Terbit:"));
        panel.add(tfYear);
        panel.add(new javax.swing.JLabel("Penerbit:"));
        panel.add(cbPublisher);
        panel.add(new javax.swing.JLabel("Sinopsis:"));
        panel.add(tfSynopsis);
        panel.add(new javax.swing.JLabel("Path Foto:"));
        panel.add(tfImagePath);
        panel.add(new javax.swing.JLabel("Stok:"));
        panel.add(tfStock);

        int result = JOptionPane.showConfirmDialog(
                dashboard,
                panel,
                "Edit Buku",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                String title = tfTitle.getText().trim();
                String author = tfAuthor.getText().trim();
                int year = Integer.parseInt(tfYear.getText().trim());
                String publisherId = ((String) cbPublisher.getSelectedItem()).split(" - ")[0];
                String synopsis = tfSynopsis.getText().trim();
                String imagePath = tfImagePath.getText().trim();
                int stock = Integer.parseInt(tfStock.getText().trim());

                if (title.isEmpty() || author.isEmpty() || synopsis.isEmpty() || imagePath.isEmpty()) {
                    throw new IllegalArgumentException("Semua field wajib diisi.");
                }

                selected.setTitle(title);
                selected.setAuthor(author);
                selected.setTahunTerbit(year);
                selected.setPublisher(publisherId);
                selected.setSynopsis(synopsis);
                selected.setImagePath(imagePath);
                selected.setStok(stock);

                if (BookController.updateBook(selected)) {
                    JOptionPane.showMessageDialog(dashboard, "Buku berhasil diperbarui.");
                    bookListPanel.reloadData();
                } else {
                    JOptionPane.showMessageDialog(dashboard, "Gagal memperbarui buku.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(dashboard, "Tahun dan stok harus berupa angka.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(dashboard, e.getMessage());
            }
        }
    }

    private static void tfPublisherSelect(JComboBox<String> combo, String publisherId) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            String item = combo.getItemAt(i);
            if (item.startsWith(publisherId)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }
}
