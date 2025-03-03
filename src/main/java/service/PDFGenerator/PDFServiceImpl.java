package service.PDFGenerator;

import repository.pdf.PDFGenerateRepository;

import java.sql.Connection;

public class PDFServiceImpl implements PDFService{
    private PDFGenerateRepository generatePDF;
    private Connection connection;

    public PDFServiceImpl(PDFGenerateRepository generatePDF, Connection connection) {
        this.generatePDF = generatePDF;
        this.connection = connection;
    }

    @Override
    public boolean generatePDF() {
        return generatePDF.generate();  // This will trigger the PDF generation
    }

}
