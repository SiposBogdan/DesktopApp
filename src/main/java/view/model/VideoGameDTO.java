package view.model;

import javafx.beans.property.*;

public class VideoGameDTO {
     private StringProperty publisher;

    public void setPublisher(String publisher) {
        publisherProperty().set(publisher);
    }
    public String getPublisher() {
        return publisherProperty().get();
    }

    public StringProperty publisherProperty() {
        if(publisher == null){
            publisher = new SimpleStringProperty(this, "publisher");
        }
        return publisher;
    }


    private StringProperty title;
    public void setTitle(String title) {
        titleProperty().set(title);
    }
    public String getTitle() {
        return titleProperty().get();
    }

    public StringProperty titleProperty() {
        if(title == null){
            title = new SimpleStringProperty(this, "title");
        }
        return title;
    }

    public StringProperty genre;
    public void setGenre(String genre) {
        genreProperty().set(genre);
    }
    public String getGenre() {
        return genreProperty().get();
    }
    public StringProperty genreProperty() {
        if(genre == null){
            genre = new SimpleStringProperty(this, "genre");
        }
        return genre;
    }
    private IntegerProperty stock;
    public IntegerProperty stockProperty(){
        if(stock==null)
        {
            stock= new SimpleIntegerProperty(this,"stock");
        }
        return stock;
    }
    public int getStock()
    {
        return stockProperty().get();
    }
    public void setStock(int stock)
    {
        stockProperty().set(stock);
    }

    private FloatProperty price;
    public FloatProperty priceProperty(){
        if(price==null)
        {
            price= new SimpleFloatProperty(this,"price");
        }
        return price;
    }
    public float getPrice()
    {
        return priceProperty().get();
    }
    public void setPrice(float price)
    {
        priceProperty().set(price);
    }
}
