package sanjana_177;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuess extends JFrame {
    private int target;
    private int attempts;
    private int maxAttempts;
    private int previousGuess = -1;

    private JTextField inputField;
    private JButton guessButton;
    private JLabel messageLabel;
    private JLabel attemptsLabel;
    private JLabel previousGuessLabel;

    public NumberGuess() {
        setTitle("Guess the Number");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Random random = new Random();
        target = random.nextInt(100) + 1; 
        attempts = 0;
        maxAttempts = 10;

        JLabel titleLabel = new JLabel("Guess the Number (1 - 100)");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 1));
        previousGuessLabel = new JLabel("Previous Guess: ");
        centerPanel.add(previousGuessLabel);

        messageLabel = new JLabel("Enter your guess:");
        centerPanel.add(messageLabel);

        inputField = new JTextField();
        inputField.addActionListener(new GuessListener());
        centerPanel.add(inputField);

        add(centerPanel, BorderLayout.CENTER);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessListener());
        add(guessButton, BorderLayout.SOUTH);

        attemptsLabel = new JLabel("Attempts left: " + (maxAttempts - attempts));
        attemptsLabel.setHorizontalAlignment(JLabel.CENTER);
        add(attemptsLabel, BorderLayout.EAST);

        setVisible(true);
    }

    private class GuessListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int guess;
            try {
                guess = Integer.parseInt(inputField.getText());
            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid input. Enter a valid number.");
                return;
            }

            previousGuess = guess;
            attempts++;
            attemptsLabel.setText("Attempts left: " + (maxAttempts - attempts));
            previousGuessLabel.setText("Previous Guess: " + previousGuess);

            if (guess == target) {
                messageLabel.setText("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                guessButton.setEnabled(false);
            } else if (attempts >= maxAttempts) {
                messageLabel.setText("Sorry, you've run out of attempts. The correct number was: " + target);
                guessButton.setEnabled(false);
            } else {
                if (guess < target) {
                    messageLabel.setText("Your guess is low. Try again.");
                } else {
                    messageLabel.setText("Your guess is high. Try again.");
                }
            }

            inputField.setText(""); 
            inputField.requestFocusInWindow();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGuess::new);
    }
}
