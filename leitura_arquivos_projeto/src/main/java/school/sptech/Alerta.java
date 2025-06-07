package school.sptech;

import org.apache.commons.pool2.PoolUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import static school.sptech.ConexaoBanco.getJdbcTemplate;

public class Alerta {

    private String nome;
    private String description;
    private SlackNotifier slackNotifier;

    public Alerta() {
        this.slackNotifier = new SlackNotifier();
    }

    public Alerta(String nome, String description) {
        this();
        this.nome = nome;
        this.description = description;
    }

    public void inserirAlerta(){
        getJdbcTemplate().update("INSERT INTO alert (name_alert, description) VALUES (?, ?)", nome, description);
        enviarMensagem();
    }

    public void enviarMensagem(){
        String textoMensagem = String.format("""
                %s
                %s
                """, nome, description).trim();
        System.out.println(textoMensagem);
        slackNotifier.enviarMensagem(textoMensagem);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SlackNotifier getSlackNotifier() {
        return slackNotifier;
    }

    public void setSlackNotifier(SlackNotifier slackNotifier) {
        this.slackNotifier = slackNotifier;
    }

    @Override
    public String toString() {
        return "Alerta{" +
                "nome='" + nome + '\'' +
                ", description='" + description + '\'' +
                ", slackNotifier=" + slackNotifier +
                '}';
    }
}
