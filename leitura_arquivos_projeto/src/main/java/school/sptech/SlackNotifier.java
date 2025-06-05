package school.sptech;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SlackNotifier {
    private static final String WEBHOOK_URL = "COLOQUE O LINK DO PLANNER AQUI";

    public static void enviarMensagem(String mensagem) {
        try {
            URL url = new URL(WEBHOOK_URL);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("POST");
            conexao.setDoOutput(true);
            conexao.setRequestProperty("Content-Type", "application/json");

            String jsonPayload = String.format("{\"text\": \"%s\"}", mensagem);

            try (OutputStream os = conexao.getOutputStream()) {
                byte[] entrada = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(entrada);
            }

            int resposta = conexao.getResponseCode();
            if (resposta == 200) {
                System.out.println("✅ Mensagem enviada com sucesso para o Slack!");
            } else {
                System.out.println("❌ Erro ao enviar mensagem. Código HTTP: " + resposta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
