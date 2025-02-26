package repository;

import model.VideoGame;

import java.util.List;
import java.util.Optional;

public interface VideoGameReposistory {
    List<VideoGame> findAll();
    Optional <VideoGame> findById(Long id);
    boolean save(VideoGame game);
    boolean delete(VideoGame game);
    int removeAll();

}
