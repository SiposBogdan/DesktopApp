package service.videoGame;

import model.VideoGame;
import repository.sale.SaleRepository;
import repository.videoGame.VideoGameRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class VideoGameServiceImpl implements VideoGameService{

    private final VideoGameRepository gameRepository;

    public VideoGameServiceImpl(VideoGameRepository gameRepository){
        this.gameRepository = gameRepository;
    }
    @Override
    public List<VideoGame> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public VideoGame findById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("The video game with id: %id was not found".formatted(id)));

    }
    @Override
    public boolean save(VideoGame game) {
        return gameRepository.save(game);
    }
    @Override
    public boolean delete(VideoGame game) {
        return gameRepository.delete(game);
    }
    @Override
    public void removeAll() {
        gameRepository.removeAll();
    }
    @Override
    public int getAgeOfGame(Long id) {
        VideoGame game = this.findById(id);
        LocalDate currentDate = LocalDate.now();
        return (int) ChronoUnit.YEARS.between(currentDate, game.getPublishedDate());
    }
    @Override
    public boolean update(VideoGame videoGame, int newStock) {
        return gameRepository.update(videoGame,newStock);
    }

}
