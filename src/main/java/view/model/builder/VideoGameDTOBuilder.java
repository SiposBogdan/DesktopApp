package view.model.builder;

import model.VideoGame;
import model.builder.VideoGameBuilder;
import view.model.VideoGameDTO;

public class VideoGameDTOBuilder {
    private VideoGameDTO videoGameDTO;
    public VideoGameDTOBuilder(){
        videoGameDTO = new VideoGameDTO();
    }
    public VideoGameDTOBuilder setPublisher(String publisher){
        videoGameDTO.setPublisher(publisher);
        return this;
    }
    public VideoGameDTOBuilder setTitle(String title){
        videoGameDTO.setTitle(title);
        return this;
    }
    public VideoGameDTOBuilder setGenre(String genre){
        videoGameDTO.setGenre(genre);
        return this;
    }
    public VideoGameDTO build(){
        return videoGameDTO;
    }

}
