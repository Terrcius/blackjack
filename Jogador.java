import java.util.ArrayList;

public class Jogador extends Participante {
    
    private String nome;

    public Jogador(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    @Override
    public void agir(ArrayList<Card> deck) {
        // Ação vazia, logo , a lógica vai para a classe BlackJack 
    }
}