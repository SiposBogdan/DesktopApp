package mapper;

import model.GameGenres;
import model.VideoGame;
import model.builder.VideoGameBuilder;
import view.model.VideoGameDTO;
import view.model.builder.VideoGameDTOBuilder;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class VideoGameMapper {
    public static VideoGameDTO convertVideoGameToVideoGameDTO(VideoGame videoGame){
        return new VideoGameDTOBuilder().setTitle(videoGame.getTitle()).setPublisher(videoGame.getPublisher()).setGenre(videoGame.getGenre().toString()).setStock(videoGame.getStock()).setPrice(videoGame.getPrice()).build();

    }

    public static VideoGame convertVideoGameDTOToVideoGame(VideoGameDTO videoGameDTO){
        return new VideoGameBuilder().setTitle(videoGameDTO.getTitle()).setPublisher(videoGameDTO.getPublisher()).setGenre(GameGenres.valueOf(videoGameDTO.getGenre())).setPublishedDate(LocalDate.of(2025,1,1)).setStock(videoGameDTO.getStock()).setPrice(videoGameDTO.getPrice()).build();
    }

    public static List<VideoGameDTO> convertVideoGameListToVideoGameDTOList(List<VideoGame> videoGames){
        return videoGames.parallelStream().map(VideoGameMapper::convertVideoGameToVideoGameDTO).collect(Collectors.toList());
    }

    public static List<VideoGame> convertVideoGameDTOListToVideoGameList(List<VideoGameDTO> videoGameDTOS){
        return videoGameDTOS.parallelStream().map(VideoGameMapper::convertVideoGameDTOToVideoGame).collect(Collectors.toList());
    }
}
