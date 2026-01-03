Blackjack Game in Java
A command-line implementation of the classic Blackjack (21) card game, featuring object-oriented design and Portuguese-language interface.

https://img.shields.io/badge/Java-100%2525-blue?style=flat-square

🎯 Features
Complete Blackjack Rules: Standard 21 gameplay with dealer rules

Object-Oriented Architecture: Clean separation of game components

Command-Line Interface: Simple text-based interaction

Portuguese Language: Game interface and messages in Portuguese

Multiple Participants: Player vs. Dealer setup

📁 Project Structure
text
blackjack/
├── BlackJack.java      # Main game class and logic controller
├── Card.java          # Card representation and properties
├── Dealer.java        # Dealer-specific behavior and AI
├── Jogador.java       # Player class (Jogador means "Player" in Portuguese)
├── Participante.java  # Base class for game participants
└── README.md          # Project documentation (this file)
🚀 Getting Started
Prerequisites
Java Development Kit (JDK) 8 or higher

Basic command-line/terminal knowledge

Installation & Running
Clone the repository

bash
git clone https://github.com/Terrcius/blackjack.git
cd blackjack
Compile the Java files

bash
javac *.java
Run the game

bash
java BlackJack
🎮 How to Play
The game starts with both player and dealer receiving two cards

Player can choose to:

Hit: Take another card

Stand: Keep current hand

Dealer follows standard casino rules (hits on 16 or below, stands on 17 or above)

The goal is to get as close to 21 as possible without going over (busting)

Win conditions:

Beat the dealer's hand without busting

Get Blackjack (21 with first two cards)

Dealer busts while you don't

🏗️ Architecture Overview
Class Hierarchy
Participante: Abstract base class containing common player/dealer functionality

Jogador: Extends Participante with player-specific logic

Dealer: Extends Participante with dealer AI and game rules

Key Components
Card: Represents a playing card with suit and value

BlackJack: Main game controller managing rounds, scoring, and flow

📝 Development History
Version 1.0: Initial release (October 14, 2025)

Version 2.0: Major update with improved architecture (December 31, 2025)

🤝 Contributing
Contributions are welcome! Feel free to:

Report bugs or issues

Suggest new features

Submit pull requests with improvements
