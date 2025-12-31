public class Card { 
    
    private String value; 
    private String type;

    public Card(String value, String type) {
        this.value = value;
        this.type = type;
    }
    
    @Override
    public String toString() {
        return value + "-" + type;
    }

    // Verifica as cartas especiais (A J Q K) e atribui valor a elas
    public int getValue() {
        if ("AJQK".contains(value)) {
            if (value.equals("A")) { 
                return 11;
            }
            return 10;
        }
        // Se não for uma carta especial apenas pega o valor apresentado na carta e transforma em int
        return Integer.parseInt(value);
    }

    // Verifica se é um Ace
    public boolean isAce() {
        return value.equals("A"); 
    }

    public String getImagePath() {
        return "./cards/" + toString() + ".png";
    }
}