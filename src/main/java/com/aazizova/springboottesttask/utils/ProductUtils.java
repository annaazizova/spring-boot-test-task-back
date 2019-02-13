package com.aazizova.springboottesttask.utils;

import com.aazizova.springboottesttask.model.entity.Product;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Anna on 10.02.2019.
 */
@Component
public class ProductUtils {
    public boolean exportToXLS(List<Product> products) {
        String[] columns = { "Id", "Name", "Brand", "Price", "Quantity" };
        try {
            try (Workbook workbook = new HSSFWorkbook(); FileOutputStream fileOut = new FileOutputStream("filtered_products.xls")) {
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

                    row.createCell(0).setCellValue(product.getId());
                    row.createCell(1).setCellValue(product.getName());
                    row.createCell(2).setCellValue(product.getBrand());
                    row.createCell(3).setCellValue(product.getPrice());
                    row.createCell(4).setCellValue(product.getQuantity());
                }

                //Auto-size all the above columns
                sheet.autoSizeColumn(0);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
                sheet.autoSizeColumn(4);

                workbook.write(fileOut);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
