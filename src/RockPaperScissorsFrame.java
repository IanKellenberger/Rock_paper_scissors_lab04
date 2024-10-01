import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * creates a GUI for the Rock Paper Scissors game.
 * It extends JFrame and has components for selecting the player's move and displaying
 * results.
 *
 * the computer's choice is determined randomly, then compared with the player's selection,
 * The totals for player wins, computer wins, and ties are displayed in JTextFields.
 *
 * @author Ian Kellenberger
 */
public class RockPaperScissorsFrame extends JFrame {

    // Declare GUI components
    private JButton rockButton, paperButton, scissorsButton, quitButton;
    private JTextField playerWinsField, computerWinsField, tiesField;
    private JTextArea resultsArea;
    private int playerWins = 0, computerWins = 0, ties = 0;
    private Random random = new Random();

    /**
     * Constructs the RockPaperScissorsFrame and initializes the GUI components.
     * Sets up the layout for buttons, stats panel, and results area.
     */
    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel buttonPanel = new JPanel();
        rockButton = new JButton(new ImageIcon("src/rock.png"));
        paperButton = new JButton(new ImageIcon("src/paper.png"));
        scissorsButton = new JButton(new ImageIcon("src/scissors.png"));
        quitButton = new JButton("Quit");

        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose Your Move"));
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(3, 2));
        statsPanel.add(new JLabel("Player Wins:"));
        playerWinsField = new JTextField("0");
        playerWinsField.setEditable(false);
        statsPanel.add(playerWinsField);

        statsPanel.add(new JLabel("Computer Wins:"));
        computerWinsField = new JTextField("0");
        computerWinsField.setEditable(false);
        statsPanel.add(computerWinsField);

        statsPanel.add(new JLabel("Ties:"));
        tiesField = new JTextField("0");
        tiesField.setEditable(false);
        statsPanel.add(tiesField);
        add(statsPanel, BorderLayout.CENTER);

        // Results
        resultsArea = new JTextArea(10, 30);
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        add(scrollPane, BorderLayout.SOUTH);

        rockButton.addActionListener(e -> playGame("Rock"));
        paperButton.addActionListener(e -> playGame("Paper"));
        scissorsButton.addActionListener(e -> playGame("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));
    }

    /**
     * Determines the result of a game based on the player's choice and a random
     * choice by the computer. Updates the game results and statistics accordingly.
     */
    private void playGame(String playerChoice) {
        String[] options = {"Rock", "Paper", "Scissors"};
        String computerChoice = options[random.nextInt(3)];
        String result = "";

        if (playerChoice.equals(computerChoice)) {
            result = playerChoice + " vs " + computerChoice + " (Tie)";
            ties++;
            tiesField.setText(String.valueOf(ties));
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            result = playerChoice + " beats " + computerChoice + " (Player wins)";
            playerWins++;
            playerWinsField.setText(String.valueOf(playerWins));
        } else {
            result = computerChoice + " beats " + playerChoice + " (Computer wins)";
            computerWins++;
            computerWinsField.setText(String.valueOf(computerWins));
        }

        resultsArea.append(result + "\n");
    }
}
