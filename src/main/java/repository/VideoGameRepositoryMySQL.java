package repository;

import model.VideoGame;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class VideoGameRepositoryMySQL implements VideoGameReposistory{

    private final Connection connection;
    public VideoGameRepositoryMySQL(Connection connection){
        this.connection = connection;
    }
    @Override
    public List<VideoGame> findAll() {
        String sql = "Select * from video_game;";
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
    public int removeAll() {
        return 0;
    }
}
