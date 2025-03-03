package repository.sale;


import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import repository.videoGame.VideoGameRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaleRepositoryMySQL {
//    private VideoGameRepository videoGameRepository;
//    private UserRepository userRepository;
//    private Connection connection;
//    public SaleRepositoryMySQL(VideoGameRepository videoGameRepository, UserRepository userRepository,Connection connection1)
//    {
//        this.videoGameRepository=videoGameRepository;
//        this.userRepository=userRepository;
//        this.connection=connection1;
//    }
//    @Override
//    public boolean addSale() {
//        String sql = "INSERT INTO sales (username, videoGamename) VALUES (?, ?)";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setString(1, userRepository.getUsername());
//            preparedStatement.setString(2, videoGameRepository.getVideoGameName());
//            preparedStatement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
