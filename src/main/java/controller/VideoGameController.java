package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mapper.VideoGameMapper;
import model.Order;
import model.User;
import model.builder.OrderBuilder;
import service.order.OrderService;
import service.videoGame.VideoGameService;
import view.VideoGameView;
import view.model.VideoGameDTO;
import view.model.builder.VideoGameDTOBuilder;

import java.sql.Timestamp;
import java.time.Instant;

public class VideoGameController {

   private final VideoGameView videoGameView;
   private final VideoGameService videoGameService;
    private final OrderService orderService;
    private final User user;
   public VideoGameController(VideoGameService videoGameService, VideoGameView videoGameView,OrderService orderService, User user){
       this.user = user;
       this.videoGameView = videoGameView;
       this.videoGameService = videoGameService;
       this.orderService = orderService;

       this.videoGameView.addSaveButtonListener(new SaveButtonListener());
       this.videoGameView.addDeleteButtonListener(new DeleteButtonListener());
       this.videoGameView.addSellButtonListener(new SellButtonListener());
   }
   private class SaveButtonListener implements EventHandler<ActionEvent> {
       @Override
       public void handle(ActionEvent event) {
           String title = videoGameView.getTitle();
           String publisher = videoGameView.getPublisher();
           String genre = videoGameView.getGenre();
           int stock = videoGameView.getStock();
           float price = videoGameView.getPrice();

           if (title.isEmpty() || publisher.isEmpty() || genre.isEmpty()) {
               videoGameView.addDisplayAlertMessage("Save Error", "Problem at Title or Publisher or Genre fields", "Can not have an empty Title or Publisher or Genre field.");
           } else {
               VideoGameDTO videoGameDTO = new VideoGameDTOBuilder().setTitle(title).setPublisher(publisher).setGenre(genre).setStock(stock).setPrice(price).build();


               boolean savedVideoGame = videoGameService.save(VideoGameMapper.convertVideoGameDTOToVideoGame(videoGameDTO));

               if (savedVideoGame) {
                   videoGameView.addDisplayAlertMessage("Save Successful", "Video game Added", "Video Game was successfully added to the database.");
                   videoGameView.addVideoGameToObservableList(videoGameDTO);
               } else {
                   videoGameView.addDisplayAlertMessage("Save Error", "Problem at adding Video Game", "There was a problem at adding the video game to the database. Please try again!");
               }
           }
       }
   }
       private class DeleteButtonListener implements EventHandler<ActionEvent>{

           @Override
           public void handle(ActionEvent event) {
               VideoGameDTO videoGameDTO = (VideoGameDTO) videoGameView.getVideoGameTableView().getSelectionModel().getSelectedItem();
               if (videoGameDTO != null){
                   boolean deletionSuccessful = videoGameService.delete(VideoGameMapper.convertVideoGameDTOToVideoGame(videoGameDTO));

                   if (deletionSuccessful){
                       videoGameView.addDisplayAlertMessage("Delete Successful", "Video game Deleted", "Video game was successfully deleted from the database.");
                       videoGameView.removeVideoGameToObservableList(videoGameDTO);
                   } else {
                       videoGameView.addDisplayAlertMessage("Delete Error", "Problem at deleting video game", "There was a problem with the database. Please try again!");
                   }
               } else {
                   videoGameView.addDisplayAlertMessage("Delete Error", "Problem at deleting video game", "You must select a video game before pressing the delete button.");
               }
           }
       }
    private class SellButtonListener implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            VideoGameDTO videoGameDTO = (VideoGameDTO) videoGameView.getVideoGameTableView().getSelectionModel().getSelectedItem();
            VideoGameDTO oldVideoGameDTO = videoGameDTO;

            if (videoGameDTO != null) {
                boolean updateSuccessful = videoGameService.update(
                        VideoGameMapper.convertVideoGameDTOToVideoGame(videoGameDTO),
                        VideoGameMapper.convertVideoGameDTOToVideoGame(videoGameDTO).getStock() - videoGameView.getQuantity()
                );

                if (updateSuccessful) {
                    Order order = new OrderBuilder()
                            .setId(null)
                            .setVideoGameTitle(videoGameDTO.getTitle())
                            .setVideoGamePublisher(videoGameDTO.getPublisher())
                            .setTimestamp(Timestamp.from(Instant.now()))
                            .setQuantity(videoGameView.getQuantity())
                            .setUserId(user.getId())
                            .build();

                    orderService.save(order);
                    videoGameView.addDisplayAlertMessage("Success", "Order Created", "The video game order was successfully made!");
                } else {
                    videoGameView.addDisplayAlertMessage("Order Error", "Problem at ordering", "There was a problem with the order. Please try again.");
                }
            } else {
                videoGameView.addDisplayAlertMessage("Order Error", "Problem at updating video game", "");
            }
        }

    }



}
