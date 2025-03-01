package view.model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

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
}
