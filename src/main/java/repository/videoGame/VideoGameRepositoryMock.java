package repository.videoGame;

import model.VideoGame;

import java.util.List;
import java.util.Optional;

public class VideoGameRepositoryMock implements VideoGameRepository{

    @Override
    public List<VideoGame> findAll() {
        return null;
    }

    @Override
    public Optional<VideoGame> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean save(VideoGame game) {
        return false;
    }

    @Override
    public boolean delete(VideoGame game) {
        return false;
    }


    @Override
    public void removeAll() {
    }

    @Override
    public boolean update(VideoGame videoGame, int newStock) {
        return false;
    }

}
