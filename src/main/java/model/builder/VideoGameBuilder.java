package model.builder;

import model.GameGenres;
import model.VideoGame;

import java.time.LocalDate;

public class VideoGameBuilder {
    private VideoGame game;
    public VideoGameBuilder(){
        game = new VideoGame();
    }
    public VideoGameBuilder setStock(int stock)
    {
        game.setStock(stock);
        return this;
    }
    public VideoGameBuilder setPrice(float price)
    {
        game.setPrice(price);
        return this;
    }
    public VideoGameBuilder setId(Long id){
        game.setId(id);
        return this;
    }
    public VideoGameBuilder setTitle(String title){
        game.setTitle(title);
        return this;
    }
    public VideoGameBuilder setPublisher(String publisher){
        game.setPublisher(publisher);
        return this;
    }
    public VideoGameBuilder setPublishedDate(LocalDate publishedDate){
        game.setPublishedDate(publishedDate);
        return this;
    }
    public VideoGameBuilder setGenre(GameGenres genre){
        game.setGenre(genre);
        return this;
    }
    public VideoGame build(){
        return game;
    }

}
