import java.util.ArrayList;

// Define a base para Jogador e Dealer
public abstract class Participante {
    
    protected ArrayList<Card> hand = new ArrayList<>();
    protected int sum = 0;
    protected int aceCount = 0;

    // Adiciona uma carta e atualiza a soma
    public void addCard(Card card) {
        hand.add(card);
        sum += card.getValue();
        // Verifica se a carta é um Ás
        aceCount += card.isAce() ? 1 : 0;
        reduceAce();
    }
    
    // Lógica para reduzir o Ás (11 para 1)
    protected void reduceAce() {
        while (sum > 21 && aceCount > 0) {
            sum -= 10;
            aceCount -= 1;
        }
    }
    
    // Método Abstrato agir
    public abstract void agir(ArrayList<Card> deck);

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getSum() {
        return sum;
    }
    
    // limpa a mão antes de um novo jogo
    public void clearHand() {
        hand.clear();
        sum = 0;
        aceCount = 0;
    }
}