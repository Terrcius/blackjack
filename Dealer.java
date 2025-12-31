import java.util.ArrayList;

public class Dealer extends Participante {
    
    private Card hiddenCard; // Carta oculta

    // Define a carta oculta e a inclui na soma
    public void setHiddenCard(Card card) {
        this.hiddenCard = card;
        addCard(card); 
    }

    public Card getHiddenCard() {
        return hiddenCard;
    }

    @Override
    public void agir(ArrayList<Card> deck) {
        // Dealer vai continuar comprando até a soma ser 17 ou mais
        while (this.sum < 17) {
            // Garante que a carta seja retirada do topo do deck
            addCard(deck.remove(deck.size() - 1));
        }
    }

    // Sobrescreve clearHand para incluir a carta oculta 
    @Override
    public void clearHand() {
        super.clearHand();
        this.hiddenCard = null;
    }
} 