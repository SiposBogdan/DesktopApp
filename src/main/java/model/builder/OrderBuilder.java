package model.builder;

import model.Order;

import java.sql.Timestamp;

public class OrderBuilder {
    private Long id;
    private Timestamp timestamp;
    private Long userId;
    private String videoGameTitle;
    private String videoGamePublisher;
    private Integer quantity;

    public OrderBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public OrderBuilder setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public OrderBuilder setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public OrderBuilder setVideoGameTitle(String videoGameTitle) {
        this.videoGameTitle = videoGameTitle;
        return this;
    }

    public OrderBuilder setVideoGamePublisher(String videoGamePublisher) {
        this.videoGamePublisher = videoGamePublisher;
        return this;
    }

    public OrderBuilder setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Order build() {
        Order order = new Order();
        order.setId(this.id);
        order.setTimestamp(this.timestamp);
        order.setUserId(this.userId);
        order.setVideoGameTitle(this.videoGameTitle);
        order.setVideoGamePublisher(this.videoGamePublisher);
        order.setQuantity(this.quantity);
        return order;
    }
}
