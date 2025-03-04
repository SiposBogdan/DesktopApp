package repository.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import model.Order;
import model.builder.OrderBuilder;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PDFGenerateMySQLRepository implements PDFGenerateRepository {
    private Connection connection;
    private static final String OUTPUT_SALE_FILE = "user_sale_report.pdf";
    private static final String OUTPUT_ORDER_FILE = "user_order_report.pdf";

    public PDFGenerateMySQLRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Map<String, Map<String, Double>> fetchUserOrders() {
        Map<String, Map<String, Double>> userEarnings = new HashMap<>();
        String query = "SELECT " +
                "    u.username AS user_name, " +
                "    DATE_FORMAT(o.time_stamp, '%Y-%m') AS order_month, " +
                "    SUM(o.quantity * b.price) AS total_earnings " +
                "FROM " +
                "    orders o " +
                "JOIN " +
                "    user u ON o.user_id = u.id " +
                "JOIN " +
                "    video_game b ON o.video_game_title = b.title " +
                "JOIN " +
                "    user_role ur ON u.id = ur.user_id " +
                "JOIN " +
                "    role r ON ur.role_id = r.id " +
                "WHERE " +
                "    r.role = 'EMPLOYEE' " +
                "GROUP BY " +
                "    u.username, DATE_FORMAT(o.time_stamp, '%Y-%m') " +
                "ORDER BY " +
                "    u.username, order_month;";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String username = rs.getString("user_name"); // alias corect
                String month = rs.getString("order_month"); // alias corect
                double earnings = rs.getDouble("total_earnings"); // alias corect

                userEarnings.putIfAbsent(username, new HashMap<>());
                userEarnings.get(username).put(month, earnings);

                System.out.println("User: " + username + ", Month: " + month + ", Earnings: " + earnings);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userEarnings;
    }

    @Override
    public void generateUserOrdersReport(Map<String, Map<String, Double>> userEarnings) {
        try {
            PdfWriter writer = new PdfWriter(new FileOutputStream(OUTPUT_SALE_FILE));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("User Orders and Earnings Report")
                    .setBold()
                    .setFontSize(18));

            float[] columnWidths = {200f, 150f, 150f};
            Table table = new Table(columnWidths);
            table.addCell(new Cell().add(new Paragraph("User")));
            table.addCell(new Cell().add(new Paragraph("Month")));
            table.addCell(new Cell().add(new Paragraph("Earnings ($)")));

            for (String user : userEarnings.keySet()) {
                Map<String, Double> earningsByMonth = userEarnings.get(user);
                for (String month : earningsByMonth.keySet()) {
                    table.addCell(new Cell().add(new Paragraph(user)));
                    table.addCell(new Cell().add(new Paragraph(month)));
                    table.addCell(new Cell().add(new Paragraph(String.format("%.2f", earningsByMonth.get(month)))));
                }
            }

            document.add(table);
            document.close();
            System.out.println("PDF generated successfully at: " + OUTPUT_SALE_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean generate() {
        try {
            Map<String, Map<String, Double>> userOrders = fetchUserOrders();
            generateUserOrdersReport(userOrders);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM `orders`";  // SQL query to get all orders

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Order order = new OrderBuilder()
                        .setId(resultSet.getLong("id"))
                        .setUserId(resultSet.getLong("user_id"))
                        .setVideoGameTitle(resultSet.getString("video_game_title"))
                        .setVideoGamePublisher(resultSet.getString("video_game_publisher"))
                        .setTimestamp(resultSet.getTimestamp("time_stamp"))
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
    public boolean generateOrdersReport() {
        List<Order> orders = getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders found to generate the PDF.");
            return false;
        }

        try {
            PdfWriter writer = new PdfWriter(new FileOutputStream(OUTPUT_ORDER_FILE));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("All Orders Report")
                    .setBold()
                    .setFontSize(18));

            // Table Setup
            float[] columnWidths = {100f, 100f, 200f, 150f, 100f};
            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.addCell(new Cell().add(new Paragraph("Order ID").setBold()));
            table.addCell(new Cell().add(new Paragraph("User ID").setBold()));
            table.addCell(new Cell().add(new Paragraph("Game Title").setBold()));
            table.addCell(new Cell().add(new Paragraph("Publisher").setBold()));
            table.addCell(new Cell().add(new Paragraph("Quantity").setBold()));

            // Fill Table with Order Data
            for (Order order : orders) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(order.getId()))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(order.getUserId()))));
                table.addCell(new Cell().add(new Paragraph(order.getVideoGameTitle())));
                table.addCell(new Cell().add(new Paragraph(order.getVideoGamePublisher())));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(order.getQuantity()))));
            }

            document.add(table);
            document.close();
            System.out.println("PDF generated successfully at: " + OUTPUT_ORDER_FILE);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
