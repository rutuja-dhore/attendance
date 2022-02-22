package com.enterprise.attendance.controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.enterprise.attendance.common.Helper;
import com.enterprise.attendance.config.EmailClient;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.enterprise.attendance.dto.input.AttendanceInputDTO;
import com.enterprise.attendance.dto.output.AttendanceOutputDTO;
import com.enterprise.attendance.service.AttendanceService;

@RestController
@RequestMapping(value = "/trips")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@PostMapping
	public AttendanceOutputDTO create(@RequestBody AttendanceInputDTO vanInputDTO) {
		return attendanceService.create(vanInputDTO);
	}

	@GetMapping
	public List<AttendanceOutputDTO> retrieveAll(
			@RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,
			@RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate,
			@RequestParam(value = "mobileNumber", required = false) String mobileNumber) {
		return attendanceService.retrieveAll(fromDate, toDate, mobileNumber);
	}

	public Workbook writeExcel(List<AttendanceOutputDTO> listBook, String excelFilePath) throws IOException {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet();

		createHeaderRow(sheet);
		int rowCount = 0;

		for (AttendanceOutputDTO aBook : listBook) {
			Row row = sheet.createRow(++rowCount);
			writeBook(aBook, row);
		}

		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		}
		return workbook;
	}

	private void writeBook(AttendanceOutputDTO aBook, Row row) {
		Cell cell = row.createCell(0);
		cell.setCellValue(aBook.getLogDate());

		cell = row.createCell(1);
		cell.setCellValue(aBook.getVanOutputDTO().getNumber());

		cell = row.createCell(2);
		cell.setCellValue(aBook.getUserOutupDTO().getFirstName());

		cell = row.createCell(3);
		cell.setCellValue(aBook.getStartKm());

		cell = row.createCell(4);
		cell.setCellValue(aBook.getEndKm());

		cell = row.createCell(5);
		cell.setCellValue(aBook.getTotalKm());

		cell = row.createCell(6);
		cell.setCellValue(aBook.getComment());

	}

	private void createHeaderRow(Sheet sheet) {

		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		Font font = sheet.getWorkbook().createFont();
		font.setBoldweight((short) 2);
		font.setFontHeightInPoints((short) 11);
		cellStyle.setFont(font);
cellStyle.setFillBackgroundColor((short) 2);
		Row row = sheet.createRow(0);
		Cell cellTitle = row.createCell(0);

		cellTitle.setCellStyle(cellStyle);
		cellTitle.setCellValue("DATE");

		Cell cellAuthor = row.createCell(1);
		cellAuthor.setCellStyle(cellStyle);
		cellAuthor.setCellValue("VECHILE NO");

		Cell cellDriverName = row.createCell(2);
		cellDriverName.setCellStyle(cellStyle);
		cellDriverName.setCellValue("DRIVER NAME");

		Cell cellStartKm = row.createCell(3);
		cellStartKm.setCellStyle(cellStyle);
		cellStartKm.setCellValue("START KM");

		Cell cellEndKm = row.createCell(4);
		cellEndKm.setCellStyle(cellStyle);
		cellEndKm.setCellValue("END KM");

		Cell cellTotalKm = row.createCell(5);
		cellTotalKm.setCellStyle(cellStyle);
		cellTotalKm.setCellValue("TOTAL KM");

		Cell cellComment = row.createCell(6);
		cellComment.setCellStyle(cellStyle);
		cellComment.setCellValue("COMMENT");

	}

	@GetMapping(value="/export")
	public boolean exportByMail(@RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,
															  @RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate,
															  @RequestParam(value = "mobileNumber", required = false) String mobileNumber) throws Exception {
		try {
			List<AttendanceOutputDTO> data = attendanceService.retrieveAll(fromDate, toDate, mobileNumber);
			if(!data.isEmpty()){

				if(fromDate == null) {
					fromDate = Helper.getFirstDateOfMonth(new Date());
				}
				if(toDate == null) {
					toDate = new Date();
				}

				String excelFilePath = "Report-" + fromDate+"-"+ toDate +".xls";
			Workbook workbook = writeExcel(data, excelFilePath);
			try  {
				EmailClient.sendAsHtml("rutuja.dhore@gmail.com",
						"Trip Report for " +excelFilePath,
						"<h2>Java Mail</h2><p>hi there!</p>", excelFilePath);
			}
			catch (Exception e) {
				e.printStackTrace();
			}

			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}


	@GetMapping(value="/downloadTemplate")
	public ResponseEntity<ByteArrayResource> downloadTemplate(@RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,
															  @RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate,
															  @RequestParam(value = "mobileNumber", required = false) String mobileNumber) throws Exception {
		try {
			List<AttendanceOutputDTO> data = attendanceService.retrieveAll(fromDate, toDate, mobileNumber);
			String excelFilePath = "Report.xls";
			Workbook workbook = writeExcel(data, excelFilePath);

			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "force-download"));
			header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+excelFilePath);
			workbook.write(stream);
			return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),
					header, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(value = "/{mobileNumber}")
	public List<AttendanceOutputDTO> retrieveByMobileNumber(@PathVariable String mobileNumber) {
		return attendanceService.retrieveByMobileNumber(mobileNumber);
	}

	// @GetMapping(value = "/{id}")
	// public VanOutputDTO retrieveById(@PathVariable Integer id) {
	// return attendanceService.retrieveById(id);
	// }

	@PutMapping
	public AttendanceOutputDTO update(@RequestBody AttendanceInputDTO attendanceInputDTO, @RequestParam Integer id) {
		return attendanceService.update(attendanceInputDTO, id);
	}
}
