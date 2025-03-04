package repository.pdf;

import model.Order;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface PDFGenerateRepository {
    Map<String, Map<String, Double>> fetchUserOrders();
    void generateUserOrdersReport(Map<String, Map<String, Double>> userEarnings);
    boolean generate();
    List<Order> getAllOrders();
    boolean generateOrdersReport();
}
