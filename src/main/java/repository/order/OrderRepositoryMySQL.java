package repository.order;

import model.Order;
import model.builder.OrderBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryMySQL implements OrderRepository{
    private Connection connection;
    public OrderRepositoryMySQL(Connection connection){
        this.connection=connection;
    }
    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM `order`";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Order order = new OrderBuilder()
                        .setId(resultSet.getLong("id"))
                        .setUserId(resultSet.getLong("user_id"))
                        .setVideoGameTitle(resultSet.getString("video_game_title"))
                        .setVideoGamePublisher(resultSet.getString("video_game_publisher"))
                        .setTimestamp(resultSet.getTimestamp("timestamp"))
                        .setQuantity(resultSet.getInt("quantity"))
                        .build();

                    orders.add(order);
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }


    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean create(Order order) {

        String newSql = "INSERT INTO orders VALUES (null,?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(newSql)) {
            preparedStatement.setTimestamp(1, order.getTimestamp());
            preparedStatement.setLong(2, order.getUserId());
            preparedStatement.setString(3, order.getVideoGameTitle());
            preparedStatement.setString(4,order.getVideoGamePublisher());
            preparedStatement.setInt(5,order.getQuantity());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(Order order) {
        return false;
    }

    @Override
    public void removeAll() {

    }
}
