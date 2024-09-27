package indice;
import java.util.LinkedList;

public class TabelaHash {
    private LinkedList<Palavra>[] tabela;

    @SuppressWarnings("unchecked")
    public TabelaHash(int tamanho) {
        tabela = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    private int hash(String palavra) {
        return palavra.toLowerCase().charAt(0) % tabela.length;
    }

    public void inserir(String palavra, int linha) {
        int index = hash(palavra);
        for (Palavra p : tabela[index]) {
            if (p.getTexto().equalsIgnoreCase(palavra)) {
                p.adicionarOcorrencia(linha);
                return;
            }
        }
        Palavra novaPalavra = new Palavra(palavra);
        novaPalavra.adicionarOcorrencia(linha);
        tabela[index].add(novaPalavra);
    }

    public LinkedList<Integer> buscar(String palavra) {
        int index = hash(palavra);
        for (Palavra p : tabela[index]) {
            if (p.getTexto().equalsIgnoreCase(palavra)) {
                return p.getOcorrencias();
            }
        }
        return null; 
    }
}

