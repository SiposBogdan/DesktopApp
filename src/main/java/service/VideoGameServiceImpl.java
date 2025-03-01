package service;

import model.VideoGame;
import repository.VideoGameReposistory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class VideoGameServiceImpl implements VideoGameService{

    private final VideoGameReposistory gameReposistory;

    public VideoGameServiceImpl(VideoGameReposistory gameReposistory){
        this.gameReposistory = gameReposistory;
    }
    @Override
    public List<VideoGame> findAll() {
        return gameReposistory.findAll();
    }

    @Override
    public VideoGame findById(Long id) {
        return gameReposistory.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("The video game with id: %id was not found".formatted(id)));

    }

    @Override
    public boolean save(VideoGame game) {
        return gameReposistory.save(game);
    }

    @Override
    public boolean delete(VideoGame game) {
        return gameReposistory.delete(game);
    }

    @Override
    public void removeAll() {
        gameReposistory.removeAll();
    }

    @Override
    public int getAgeOfGame(Long id) {
        VideoGame game = this.findById(id);
        LocalDate currentDate = LocalDate.now();
        return (int) ChronoUnit.YEARS.between(currentDate, game.getPublishedDate());
    }
}
