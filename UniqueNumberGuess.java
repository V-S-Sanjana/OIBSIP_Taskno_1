package sanjana_177;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class UniqueNumberGuess extends JFrame {
    private int targetNumber;
    private int numAttempts;
    private int maxAttempts;
    private int previousGuess = -1;

    private JTextField guessInputField;
    private JButton submitGuessButton;
    private JLabel resultMessageLabel;
    private JLabel attemptsLeftLabel;
    private JLabel previousGuessInfoLabel;

    public UniqueNumberGuess() {
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
        numAttempts = 0;
        maxAttempts = 10;

        JLabel gameTitleLabel = new JLabel("Guess the Mystery Number (1 - 100)");
        gameTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(gameTitleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 1));
        previousGuessInfoLabel = new JLabel("Previous Attempt: ");
        centerPanel.add(previousGuessInfoLabel);

        resultMessageLabel = new JLabel("Enter your guess:");
        centerPanel.add(resultMessageLabel);

        guessInputField = new JTextField();
        guessInputField.addActionListener(new GuessSubmitListener());
        centerPanel.add(guessInputField);

        add(centerPanel, BorderLayout.CENTER);

        submitGuessButton = new JButton("Submit Guess");
        submitGuessButton.addActionListener(new GuessSubmitListener());
        add(submitGuessButton, BorderLayout.SOUTH);

        attemptsLeftLabel = new JLabel("Attempts Remaining: " + (maxAttempts - numAttempts));
        attemptsLeftLabel.setHorizontalAlignment(JLabel.CENTER);
        add(attemptsLeftLabel, BorderLayout.EAST);

        setVisible(true);
    }

    private class GuessSubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int userGuess;
            try {
                userGuess = Integer.parseInt(guessInputField.getText());
            } catch (NumberFormatException ex) {
                resultMessageLabel.setText("Invalid input. Please enter a valid number.");
                return;
            }

            previousGuess = userGuess;
            numAttempts++;
            attemptsLeftLabel.setText("Attempts Remaining: " + (maxAttempts - numAttempts));
            previousGuessInfoLabel.setText("Previous Attempt: " + previousGuess);

            if (userGuess == targetNumber) {
                resultMessageLabel.setText("Congratulations! You've guessed the correct number in " + numAttempts + " attempts.");
                submitGuessButton.setEnabled(false);
            } else if (numAttempts >= maxAttempts) {
                resultMessageLabel.setText("Sorry, you've exhausted your attempts. The correct number was: " + targetNumber);
                submitGuessButton.setEnabled(false);
            } else {
                if (userGuess < targetNumber) {
                    resultMessageLabel.setText("Your guess is low. Try again.");
                } else {
                    resultMessageLabel.setText("Your guess is high. Try again.");
                }
            }

            guessInputField.setText("");
            guessInputField.requestFocusInWindow();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UniqueNumberGuess::new);
    }
}

