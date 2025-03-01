package repository;

import model.GameGenres;
import model.VideoGame;
import model.builder.VideoGameBuilder;

import java.sql.*;
import java.util.ArrayList;
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
        List<VideoGame> games = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                games.add(getVideoGameFromResultSet(resultSet));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return games;
    }

    @Override
    public Optional<VideoGame> findById(Long id) {
        String sql = "SELECT * FROM video_game WHERE id=" + id;

        Optional<VideoGame> game = Optional.empty();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                game = Optional.of(getVideoGameFromResultSet(resultSet));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return game;
    }

    @Override
    public boolean save(VideoGame game) {
        String newSql = "INSERT INTO video_game (title, publisher, publishedDate, genre) VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(newSql);
            preparedStatement.setString(1, game.getTitle());
            preparedStatement.setString(2, game.getPublisher());
            preparedStatement.setDate(3, java.sql.Date.valueOf(game.getPublishedDate()));
            preparedStatement.setString(4, game.getGenre().name()); // Convert Enum to String

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(VideoGame game) {
        String newSql = "DELETE FROM video_game WHERE publisher = ? AND title = ?";


        try{
            PreparedStatement statement = connection.prepareStatement(newSql);
            statement.setString(1, game.getPublisher());
            statement.setString(2, game.getTitle());
            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void removeAll() {
        String sql = "DELETE FROM video_game WHERE id >= 0;";

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private VideoGame getVideoGameFromResultSet(ResultSet resultSet) throws Exception{
        return new VideoGameBuilder()
                .setId(resultSet.getLong("id"))
                .setTitle(resultSet.getString("title"))
                .setPublisher(resultSet.getString("publisher"))
                .setPublishedDate(new java.sql.Date(resultSet.getDate("publishedDate").getTime()).toLocalDate())
                .setGenre(GameGenres.valueOf(resultSet.getString("genre")))
                .build();

    }
}
