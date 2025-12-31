import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class BlackJack {
    ArrayList<Card> deck;
    Random random = new Random();
    Dealer dealer;
    Jogador player; 

    int boardWidth = 800;
    int boardHeight = boardWidth;
    int cardWidth = 110; 
    int cardHeight = 154;

    JFrame frame = new JFrame("Black Jack POO");
    JPanel gamePanel = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // Obter a soma e mãos do dealer e player
            int dealerSum = dealer.getSum();
            int playerSum = player.getSum();
            String dealerScoreText = "?";
            
            // Quando o jogo está rodadndo mostra apenas o valor da primeira carta visível
            if (stayButton.isEnabled()) {
                dealerScoreText = String.valueOf(dealer.getHand().get(1).getValue());
            } else {
                // Quando apertado o botão stay mostra a soma das duas cartas do dealer
                dealerScoreText = String.valueOf(dealerSum);
            }

            // Configurações de fonte para o placar do player e do dealer
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.setColor(Color.WHITE);

            // **Dealer**
            // Cria carta oculta
            Image hiddenCardImg = new ImageIcon(getClass().getResource("./cards/BACK.png")).getImage();
            if (!stayButton.isEnabled()) {
                // Revela a carta oculta ao final do jogo
                hiddenCardImg = new ImageIcon(getClass().getResource(dealer.getHiddenCard().getImagePath())).getImage();
            }
            g.drawImage(hiddenCardImg, 20, 20, cardWidth, cardHeight, null);

            // Cria a mão do dealer 
            for (int i = 1; i < dealer.getHand().size(); i++) {
                Card card = dealer.getHand().get(i);
                Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                g.drawImage(cardImg, cardWidth + 25 + (cardWidth + 5)*(i-1), 20, cardWidth, cardHeight, null);
            }

            // Plcar dealer
            g.drawString("Dealer: " + dealerScoreText, 20, 200);

            // **Player**
            // Placar jogador
            g.drawString(player.getNome() + ": " + playerSum, 20, 300);

            // Desenha a mão do jogador
            for (int i = 0; i < player.getHand().size(); i++) {
                Card card = player.getHand().get(i);
                Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                g.drawImage(cardImg, 20 + (cardWidth + 5)*i, 320, cardWidth, cardHeight, null);
            }

            // Mostra o resultado final se ele ganhou/perder/empatou
            if (!stayButton.isEnabled()) {
                String message = "";
                
                if (playerSum > 21) {
                    message = "Você Perdeu! (Estourou)";
                }
                else if (dealerSum > 21) {
                    message = "Você Ganhou! (Dealer Estourou)";
                }
                else if (playerSum == dealerSum) {
                    message = "Empate!";
                }
                else if (playerSum > dealerSum) {
                    message = "Você Ganhou!";
                }
                else if (playerSum < dealerSum) {
                    message = "Você Perdeu!";
                }

                g.setFont(new Font("Arial", Font.PLAIN, 30));
                g.setColor(Color.RED);
                g.drawString(message, 220, 250);
            }

        }
    };

    //Botões
    JPanel buttonPanel = new JPanel();
    JButton hitButton = new JButton("Hit");
    JButton stayButton = new JButton("Stay");
    JButton restartButton = new JButton("Restart");

    BlackJack() {
        dealer = new Dealer();
        player = new Jogador("Eu"); 
        
        startGame();

        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(53, 101, 77));
        frame.add(gamePanel);

        // Configuração dos botões
        hitButton.setFocusable(false);
        buttonPanel.add(hitButton);
        stayButton.setFocusable(false);
        buttonPanel.add(stayButton);
        restartButton.setFocusable(false);
        buttonPanel.add(restartButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        // **Jogador**
        // Botão HIT
        hitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Card card = deck.remove(deck.size()-1);
                player.addCard(card);
                
                // Verifica se estourou ou não
                if (player.getSum() > 21) { 
                    hitButton.setEnabled(false); 
                    stayButton.setEnabled(false);
                }
                gamePanel.repaint();
            }
        });

        // Botão STAY
        stayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hitButton.setEnabled(false);
                stayButton.setEnabled(false);

                dealer.agir(deck);
                
                gamePanel.repaint();
            }
        });
        
        // Botão RESTART
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
                gamePanel.repaint();
            }
        });

        gamePanel.repaint();
    }

    public void startGame() {
        // Limpa as mãos
        player.clearHand();
        dealer.clearHand();
        
        // Cria o deck e embaralha ele
        buildDeck();
        shuffleDeck();

        
        // 1. Dealer: Carta Oculta 
        Card hiddenCard = deck.remove(deck.size()-1); 
        dealer.setHiddenCard(hiddenCard);

        // 2. Dealer: Carta Visível
        dealer.addCard(deck.remove(deck.size()-1));

        // 3. Player: Duas cartas
        for (int i = 0; i < 2; i++) {
            player.addCard(deck.remove(deck.size()-1));
        }
        
        hitButton.setEnabled(true);
        stayButton.setEnabled(true);
    }

    // Cria o deck fazendo com que cada carta possua um valor, ex.: "2-D"
    public void buildDeck() {
        deck = new ArrayList<Card>();
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] types = {"C", "D", "H", "S"};

        for (int i = 0; i < types.length; i++) {
            for (int j = 0; j < values.length; j++) {
                Card card = new Card(values[j], types[i]); 
                deck.add(card);
            }
        }
    }

    // Embaralha o deck 
    public void shuffleDeck() {
        for (int i = 0; i < deck.size(); i++) {
            int j = random.nextInt(deck.size());
            Card currCard = deck.get(i);
            Card randomCard = deck.get(j);
            deck.set(i, randomCard);
            deck.set(j, currCard);
        }
    }
    
    public static void main(String[] args) {
        new BlackJack();
    }
}