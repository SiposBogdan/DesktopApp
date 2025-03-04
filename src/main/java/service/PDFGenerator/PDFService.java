package service.PDFGenerator;

import java.io.FileNotFoundException;

public interface PDFService {
    public boolean generateSalesPDF();
    public boolean generateOrdersPDF();
}
