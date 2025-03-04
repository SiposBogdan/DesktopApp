package service.PDFGenerator;

import repository.pdf.PDFGenerateRepository;

import java.io.FileNotFoundException;
import java.sql.Connection;

public class PDFServiceImpl implements PDFService{
    private PDFGenerateRepository generatePDF;
    private Connection connection;

    public PDFServiceImpl(PDFGenerateRepository generatePDF, Connection connection) {
        this.generatePDF = generatePDF;
        this.connection = connection;
    }

    @Override
    public boolean generateSalesPDF() {
        return generatePDF.generate();
    }

    @Override
    public boolean generateOrdersPDF() {
        return generatePDF.generateOrdersReport();
    }

}
