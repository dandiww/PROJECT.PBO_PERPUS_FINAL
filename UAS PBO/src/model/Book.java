package model;

public class Book {
    private String idBuku;
    private String title;
    private String author;
    private String publisher;
    private int tahunTerbit;
    private String synopsis;
    private String imagePath;
    private int stok;
    private int dipinjam;
    private String filePath;

    public Book() {
    }

    public Book(String idBuku, String title, String author, String publisher, int tahunTerbit,
                String synopsis, String imagePath, int stok) {
        this.idBuku = idBuku;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.tahunTerbit = tahunTerbit;
        this.synopsis = synopsis;
        this.imagePath = imagePath;
        this.stok = stok;
    }

    public String getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {   
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {  
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {  
        this.publisher = publisher;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {  
        this.synopsis = synopsis;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getDipinjam() {
        return dipinjam;
    }

    public void setDipinjam(int dipinjam) {
        this.dipinjam = dipinjam;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) { 
        this.filePath = filePath;
    }
}
