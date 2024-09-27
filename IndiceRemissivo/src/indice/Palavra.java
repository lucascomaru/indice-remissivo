package indice;
import java.util.LinkedList;

public class Palavra {
    private String texto;
    private LinkedList<Integer> ocorrencias;

    public Palavra(String texto) {
        this.texto = texto;
        this.ocorrencias = new LinkedList<>();
    }

    public String getTexto() {
        return texto;
    }

    public void adicionarOcorrencia(int linha) {
        if (!ocorrencias.contains(linha)) {
            ocorrencias.add(linha);
        }
    }

    public LinkedList<Integer> getOcorrencias() {
        return ocorrencias;
    }
}

