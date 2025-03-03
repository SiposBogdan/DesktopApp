import model.GameGenres;
import model.VideoGame;
import model.builder.VideoGameBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import repository.videoGame.VideoGameRepository;
import repository.videoGame.VideoGameRepositoryMock;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VideoGameRepositoryTest {
    private static VideoGameRepository videoGameRepository;

    @BeforeAll
    public static void setup()
    {
        videoGameRepository= new VideoGameRepositoryMock();
    }
    //@Test

    /*public void findAll(){
        List<Book> books=bookRepository.findAll();
        assertEquals(0, books.size());
    }*/

    @Test

    public void findById()
    {
        final Optional<VideoGame> book=videoGameRepository.findById(1L);
        assertTrue(book.isEmpty());
    }

    @Test

    public void save()
    {
        //assertTrue(videoGameRepository.save(new VideoGameBuilder().setTitle("Ion").setPublisher("Liviu Rebreanu").setPublishedDate(LocalDate.of(1910 ,10, 20)).setId((long) 1).setGenre(GameGenres.valueOf("ACTION")).build()));
    }


}
