package repository.videoGame;

public abstract class VideoGameRepositoryDecorator implements VideoGameRepository{
    protected VideoGameRepository decoratedVideoGameRepository;
    public VideoGameRepositoryDecorator(VideoGameRepository videoGame){
        decoratedVideoGameRepository = videoGame;
    }

}
