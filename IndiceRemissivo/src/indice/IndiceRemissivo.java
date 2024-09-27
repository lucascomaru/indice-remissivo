package indice;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IndiceRemissivo {
    private static TabelaHash tabelaHash;

    public static void main(String[] args) {
        tabelaHash = new TabelaHash(26);
        String textoArquivo = "data/texto.txt"; 
        String palavrasChavesArquivo = "data/palavras_chaves.txt"; 
        String indiceSaida = "data/indice_remissivo.txt"; 

        processarTexto(textoArquivo);
        gerarIndiceRemissivo(palavrasChavesArquivo, indiceSaida);
    }

    private static void processarTexto(String arquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int numeroLinha = 0;
            while ((linha = reader.readLine()) != null) {
                numeroLinha++;
                System.out.println("Processando linha " + numeroLinha + ": " + linha); 
                String[] palavras = linha.split("\\W+");
                for (String palavra : palavras) {
                    if (!palavra.isEmpty()) {
                        tabelaHash.inserir(palavra, numeroLinha);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void gerarIndiceRemissivo(String arquivoChaves, String arquivoSaida) {
        List<String> palavrasChaves = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoChaves))) {
            String palavraChave;
            while ((palavraChave = reader.readLine()) != null) {
                palavrasChaves.add(palavraChave.trim());
                System.out.println("Palavra chave lida: " + palavraChave);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            java.io.File file = new java.io.File(arquivoSaida);
            file.getParentFile().mkdirs(); 
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String palavraChave : palavrasChaves) {
                    LinkedList<Integer> ocorrencias = tabelaHash.buscar(palavraChave);
                    if (ocorrencias != null) {
                        System.out.println("Ocorrências encontradas para: " + palavraChave + " - " + ocorrencias.toString());
                        writer.write(palavraChave + ": " + ocorrencias.toString());
                        writer.newLine();
                    } else {
                        System.out.println("Nenhuma ocorrência encontrada para: " + palavraChave);
                    }
                }
                System.out.println("Índice remissivo gerado em: " + arquivoSaida);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
