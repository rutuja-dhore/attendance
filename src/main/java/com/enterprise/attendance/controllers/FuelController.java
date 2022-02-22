package com.enterprise.attendance.controllers;

import com.enterprise.attendance.common.Helper;
import com.enterprise.attendance.config.EmailClient;
import com.enterprise.attendance.dto.input.FuelInputDTO;
import com.enterprise.attendance.dto.output.AttendanceOutputDTO;
import com.enterprise.attendance.dto.output.FuelOutputDTO;
import com.enterprise.attendance.service.FuelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/fuel")
public class FuelController {

    @Autowired
    private FuelService fuelService;

    @PostMapping
    public FuelOutputDTO create(@RequestBody FuelInputDTO fuelInputDTO) {
        return fuelService.create(fuelInputDTO);
    }

    @GetMapping(value = "/{vanNumber}")
    public List<FuelOutputDTO> retrieveByVanNumber(@PathVariable String vanNumber) {
        return fuelService.retrieveByVanNumber(vanNumber);
    }

    @GetMapping
    public List<FuelOutputDTO> retrieveAll(
            @RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,
            @RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate,
            @RequestParam(value = "vanNumber", required = false) String vanNumber) {
        return fuelService.retrieveAll(fromDate, toDate, vanNumber);
    }

    @GetMapping(value = "/export")
    public boolean exportByMail(@RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,
                                @RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate,
                                @RequestParam(value = "vanNumber", required = false) String vanNumber) throws Exception {
        try {
            List<FuelOutputDTO> data = fuelService.retrieveAll(fromDate, toDate, vanNumber);
            if (!data.isEmpty()) {

                if (fromDate == null) {
                    fromDate = Helper.getFirstDateOfMonth(new Date());
                }
                if (toDate == null) {
                    toDate = new Date();
                }

                String excelFilePath = "Fuel-Report-" + fromDate + "-" + toDate + ".xls";
                Workbook workbook = writeExcel(data, excelFilePath);
                try {
                    EmailClient.sendAsHtml("rutuja.dhore@gmail.com",
                            "" + excelFilePath,
                            "<h2>Java Mail</h2><p>hi there!</p>", excelFilePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public Workbook writeExcel(List<FuelOutputDTO> listBook, String excelFilePath) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        createHeaderRow(sheet);
        int rowCount = 0;

        for (FuelOutputDTO aBook : listBook) {
            Row row = sheet.createRow(++rowCount);
            writeBook(aBook, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
        return workbook;
    }

    private void writeBook(FuelOutputDTO aBook, Row row) {

        String type = aBook.getType() == null? "" : aBook.getType();

        Cell cell = row.createCell(0);
        cell.setCellValue(type + " " + aBook.getVanOutputDTO().getNumber());

        double totalKm = 0;
        for (int i = 1; i <= 31; i++) {
            if (aBook.getDayOfLogDate() == i) {
                cell = row.createCell(i);
                cell.setCellValue(aBook.getAmount());
                totalKm += aBook.getAmount();
            }
        }
        Cell celltotalKm = row.createCell(32);
        celltotalKm.setCellValue(totalKm);

    }

    private void createHeaderRow(Sheet sheet) {

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBoldweight((short) 10);
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        cellStyle.setFillBackgroundColor((short) 2);
        Row row = sheet.createRow(0);
        Cell cellTitle = row.createCell(0);
        cellTitle.setCellStyle(cellStyle);
        cellTitle.setCellValue("DATE");
        int i = 1;
        for (; i <= 31; i++) {
            Cell cellTitleDay = row.createCell(i);
            cellTitleDay.setCellStyle(cellStyle);
            cellTitleDay.setCellValue(i);
        }

        Cell cellTotalKm = row.createCell(i);
        cellTotalKm.setCellStyle(cellStyle);
        cellTotalKm.setCellValue("TOTAL KM");


    }
}
