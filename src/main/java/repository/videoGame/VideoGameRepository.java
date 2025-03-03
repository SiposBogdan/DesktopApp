package repository.videoGame;

import model.VideoGame;

import java.util.List;
import java.util.Optional;

public interface VideoGameRepository {
    List<VideoGame> findAll();
    Optional <VideoGame> findById(Long id);
    boolean save(VideoGame game);
    boolean delete(VideoGame game);
    void removeAll();
    boolean update (VideoGame videoGame, int newStock);

}
