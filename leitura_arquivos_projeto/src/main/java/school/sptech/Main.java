package school.sptech;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.util.IOUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Main {

    public static void main(String[] args) throws IOException {

        ConexaoBanco conexao = new ConexaoBanco();
        JdbcTemplate jdbcTemplate = conexao.getJdbcTemplate();

        IOUtils.setByteArrayMaxOverride(400_000_000);

        Path caminho = Path.of("C:/Users/user/Downloads/planilha_dados.xlsx");
        InputStream arquivo = Files.newInputStream(caminho);

        Workbook workbook = new XSSFWorkbook(arquivo);

        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i < sheet.getLastRowNum() +1 ; i++) {
             String passage = "";
             String direction = "";
             String type = "";
             String region = "";
             Date dateTime = null;
             Integer jamSize = 0;
             String segment = "";
            for (int j = 1; j < sheet.getRow(i).getLastCellNum(); j++) {
                Cell cell = sheet.getRow(i).getCell(j);
                switch (j) {
                    case 1:
                        passage = cell.getStringCellValue();
                        break;
                    case 2:
                        direction = cell.getStringCellValue();
                        break;
                    case 3:
                        type = cell.getStringCellValue();
                        break;
                    case 4:
                        region = cell.getStringCellValue();
                        break;
                   case 5:
                       dateTime = cell.getDateCellValue();
                       break;
                    case 6:
                        jamSize = (int) cell.getNumericCellValue();
                        break;
                    case 7:
                        segment = cell.getStringCellValue();
                        break;
                }
            }
                jdbcTemplate.update("Insert into dados (passage, direction, type, region, dataRegistro, jamSize, segment) VALUES (?, ?, ?, ?, ?, ?, ?)", passage, direction, type, region, dateTime, jamSize, segment);
        }




    }
}