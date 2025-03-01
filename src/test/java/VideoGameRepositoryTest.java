import model.GameGenres;
import model.VideoGame;
import model.builder.VideoGameBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.VideoGameReposistory;
import repository.VideoGameRepositoryMock;
import repository.VideoGameRepositoryMySQL;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VideoGameRepositoryTest {
   /* private static VideoGameReposistory videoGameReposistory;
    @BeforeAll
    public static void setup(){
        videoGameReposistory = new VideoGameRepositoryMock();
    }

    @Test
    public void findAll(){
        List<VideoGame> videoGames = videoGameReposistory.findAll();
        assertEquals(0, videoGames.size());
    }

    @Test
    public void findById(){
        final Optional<VideoGame> book = videoGameReposistory.findById(1L);
        assertTrue(book.isEmpty());
    }

    @Test
    public void save(){
        assertTrue(videoGameReposistory.save(new VideoGameBuilder().setTitle("EldenRing")
                .setGenre(GameGenres.ADVENTURE)
                .setPublisher("2K")
                .setPublishedDate(LocalDate.of(2005,10,10))
                .build()));
    }*/

}
