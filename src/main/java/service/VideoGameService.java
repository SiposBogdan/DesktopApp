package service;

import model.VideoGame;

import java.util.List;

public interface VideoGameService {
    List<VideoGame> findAll();
    VideoGame findById(Long id);
    boolean save (VideoGame game);
    boolean delete(VideoGame game);
    int getAgeOfGame(Long id);
}
