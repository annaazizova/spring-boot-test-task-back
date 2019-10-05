package com.aazizova.springboottesttask.utils;

import com.aazizova.springboottesttask.model.entity.Product;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Product utils.
 */
@Component
public class ProductUtils {
    /**
     * Logger.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ProductUtils.class);
    /**
     * Zero.
     */
    private static final int ZERO = 0;
    /**
     * One.
     */
    private static final int ONE = 1;
    /**
     * Two.
     */
    private static final int TWO = 2;
    /**
     * Three.
     */
    private static final int THREE = 3;
    /**
     * Four.
     */
    private static final int FOUR = 4;

    /**
     * Export products to XLS.
     *
     * @param products List<Product>
     * @return boolean
     */
    public boolean exportToXLS(final List<Product> products) {
        String[] columns = {"Id", "Name", "Brand", "Price", "Quantity"};
        try {
            try (Workbook workbook = new HSSFWorkbook();
                 FileOutputStream fileOut
                         = new FileOutputStream("filtered_products.xlsx")) {
                Sheet sheet = workbook.createSheet("Users");

                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setColor(IndexedColors.BLUE.getIndex());

                CellStyle headerCellStyle = workbook.createCellStyle();
                headerCellStyle.setFont(headerFont);

                // Header Row
                Row headerRow = sheet.createRow(0);

                // Table Header
                for (int col = 0; col < columns.length; col++) {
                    Cell cell = headerRow.createCell(col);
                    cell.setCellValue(columns[col]);
                    cell.setCellStyle(headerCellStyle);
                }

                int rowIdx = 1;
                for (Product product : products) {
                    Row row = sheet.createRow(rowIdx++);

                    row.createCell(ZERO).setCellValue(product.getId());
                    row.createCell(ONE).setCellValue(product.getName());
                    row.createCell(TWO).setCellValue(product.getBrand());
                    row.createCell(THREE).setCellValue(product.getPrice());
                    row.createCell(FOUR).setCellValue(product.getQuantity());
                }

                //Auto-size all the above columns
                sheet.autoSizeColumn(ZERO);
                sheet.autoSizeColumn(ONE);
                sheet.autoSizeColumn(TWO);
                sheet.autoSizeColumn(THREE);
                sheet.autoSizeColumn(FOUR);

                workbook.write(fileOut);
                return true;
            }
        } catch (IOException e) {
            LOGGER.warn("Can't export products: {}", e.getLocalizedMessage());
            return false;
        }
    }
}
