package school.sptech;

import software.amazon.awssdk.services.s3.S3Client;

import java.util.List;

public class Main2 {
    public static void main(String[] args) {

        String bucket = ConfigLoader.get("S3_BUCKET");
        S3Client s3Client = ConexaoS3.criarS3Client();
        String prefixo = "planilhas/";

        List<String> arquivos = ConexaoS3.listarArquivos(bucket, prefixo);

        if(!arquivos.isEmpty()) {
            String arquivoMaisRecente = arquivos.getFirst();
        }
    }
}

