package school.sptech;

import org.springframework.jdbc.core.JdbcTemplate;
import software.amazon.awssdk.services.s3.S3Client;


public class Main {

    public static void main(String[] args) {
        String bucket = ConfigLoader.get("S3_BUCKET");
        S3Client s3Client = ConexaoS3.criarS3Client();

        Info info = new Info();
        Error error = new Error();

        ConexaoBanco conexaoBanco = new ConexaoBanco();
        JdbcTemplate jdbcTemplate = conexaoBanco.getJdbcTemplate();
        LeitorDados leitorDados = new LeitorDados(jdbcTemplate, s3Client);
        try {
            leitorDados.processar(bucket, "planilhas/base-dados-route-finder.xlsx");
            info.inserirLog( "O arquivo planilhas/base-dados-route-finder.xlsx foi lido com sucesso");
        } catch (Exception e) {
            System.out.println(String.format("Não foi possível processar os dados, erro: %s", e.getMessage()));
            error.inserirLog(String.format("Não foi possível processar os dados, erro: %s", e.getMessage()));
        }
    }
}

