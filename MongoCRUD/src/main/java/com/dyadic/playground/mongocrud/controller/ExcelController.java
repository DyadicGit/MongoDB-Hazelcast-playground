package com.dyadic.playground.mongocrud.controller;

import com.dyadic.playground.mongocrud.domain.Company;
import com.dyadic.playground.mongocrud.service.CompanyService;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/excel")
public class ExcelController {
    private final CompanyService companyService;

    public ExcelController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/upload")
    String uploadAndParseExcelFile(Model model) {
        return "api/companies";
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadAndParseExcelFile(Model model,
                                   @RequestParam("file") MultipartFile multipartFile,
                                   @RequestParam(value = "saveToMongodb", required = false) Boolean saveToMongodb) throws IOException {
        boolean savedToMongo = false;
        if (multipartFile != null && !multipartFile.isEmpty()) {
            InputStream excelFile = multipartFile.getInputStream();

            List<Map<String, String>> excelMap = parseExcelFileToMap(excelFile);
            List<Company> companies =  excelMapToCompanyList(excelMap);
            if (Optional.ofNullable(saveToMongodb).isPresent()) {
                companyService.addNewCompanies(companies);
                savedToMongo = true;
            }
            model.addAttribute("companies", companies);
            model.addAttribute("savedToMongo", savedToMongo);
            return "api/companies";
        }
        return "api/companies";
    }

    @GetMapping("/parse")
    String parseExampleExcelFile(Model model) throws FileNotFoundException {
        FileInputStream excelFile = new FileInputStream(new File("./MongoCRUD/src/main/resources/excelFiles/example_of_excel_table.xlsx"));

        List<Map<String, String>> excelMap = parseExcelFileToMap(excelFile);
        model.addAttribute("companies", excelMapToCompanyList(excelMap));
        return "api/companies";
    }

    private List<Company> excelMapToCompanyList(List<Map<String, String>> excelMap) {
        return excelMap.stream().map(row ->
                new Company(
                        row.get("JAR_KODAS"),
                        row.get("PAVAD"),
                        row.get("ADRES"),
                        row.get("VIETOVE")
                )
        ).collect(Collectors.toList());
    }

    private List<Map<String, String>> parseExcelFileToMap(FileInputStream fileInputStream) {
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(fileInputStream);
            return parseExcelFileToMap(workbook);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private List<Map<String, String>> parseExcelFileToMap(InputStream inputStream) {
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(inputStream);
            return parseExcelFileToMap(workbook);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private List<Map<String, String>> parseExcelFileToMap(Workbook workbook) {
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = firstSheet.rowIterator();
        List<Map<String, String>> rowMapList = new ArrayList<>();
        List<String> headerRow = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();
        while (rowIterator.hasNext()) {
            Row currentRow = rowIterator.next();
            if (currentRow.getRowNum() == 0) {
                currentRow.cellIterator().forEachRemaining(cell -> headerRow.add(dataFormatter.formatCellValue(cell)));
            } else {
                Map<String, String> rowMap = new HashMap<>();
                currentRow.cellIterator().forEachRemaining(cell ->
                        rowMap.put(headerRow.get(cell.getColumnIndex()), dataFormatter.formatCellValue(cell))
                );
                rowMapList.add(rowMap);
            }
        }
        return rowMapList;
    }
}
