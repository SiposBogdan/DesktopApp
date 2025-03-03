package model;

import java.time.LocalDate;

public class VideoGame {
    private Long id;
    private String title;
    private String publisher;
    private LocalDate publishedDate;
    private GameGenres genre;
    private Integer stock;
    private Float price;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public GameGenres getGenre() {
        return genre;
    }

    public void setGenre(GameGenres genre) {
        this.genre = genre;
    }
    @Override
    public String toString() {
        return "VideoGame{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishedDate=" + publishedDate +
                ", genre=" + genre + ", Stock: " + stock +
                " Price: " + price+"}";
    }
}
