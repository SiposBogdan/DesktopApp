package repository.pdf;

import java.util.Map;

public interface PDFGenerateRepository {
    Map<String, Map<String, Double>> fetchUserOrders();
    void generateUserOrdersReport(Map<String, Map<String, Double>> userEarnings);
    boolean generate();
}
