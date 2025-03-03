package repository.videoGame;

import model.VideoGame;

import java.util.List;
import java.util.Optional;

public class VideoGameRepositoryCacheDecorator extends VideoGameRepositoryDecorator{
    private Cache<VideoGame> cache;

    public VideoGameRepositoryCacheDecorator(VideoGameRepository videoGameRepository, Cache<VideoGame> cache) {
        super(videoGameRepository);
        this.cache = cache;


    }

    @Override
    public List<VideoGame> findAll() {
        if(cache.hasResult())
        {
            return cache.load();
        }
        List<VideoGame> videoGames = decoratedVideoGameRepository.findAll();
        cache.save(videoGames);
        return videoGames;

    }

    @Override
    public Optional<VideoGame> findById(Long id) {
        if(cache.hasResult()){
            return cache.load().stream()
                    .filter(it -> it.getId().equals(id))
                    .findFirst();
        }
        return decoratedVideoGameRepository.findById(id);
    }

    @Override
    public boolean save(VideoGame game) {
        cache.invalidateCache();
        return decoratedVideoGameRepository.save(game);
    }

    @Override
    public boolean delete(VideoGame game) {
        cache.invalidateCache();
        return decoratedVideoGameRepository.delete(game);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedVideoGameRepository.removeAll();
    }
    @Override
    public boolean update(VideoGame videoGame, int newStock) {
        cache.invalidateCache();
        return decoratedVideoGameRepository.update(videoGame,newStock);
    }


}
