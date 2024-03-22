package org.example.pt2024_30225_ardelean_robert_assignment_1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PolynomialCalculatorController {

    @FXML
    private TextField polynomialField1;

    @FXML
    private TextField polynomialField2;

    @FXML
    private Label resultLabel;

    private final Polynomial polynomial1;
    private final Polynomial polynomial2;

    public PolynomialCalculatorController() {
        polynomial1 = new Polynomial();
        polynomial2 = new Polynomial();
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleAddition() {
        clearResultLabel();
        try {
            polynomial1.parsePolynomial(polynomialField1.getText());
            polynomial2.parsePolynomial(polynomialField2.getText());
            Polynomial result = polynomial1.add(polynomial2);
            calculateAndDisplayResult(result);
        } catch (IllegalArgumentException e) {
            resultLabel.setText(e.getMessage());
        }
    }

    @FXML
    private void handleSubtraction() {
        clearResultLabel();
        try {
            polynomial1.parsePolynomial(polynomialField1.getText());
            polynomial2.parsePolynomial(polynomialField2.getText());
            Polynomial result = polynomial1.subtract(polynomial2);
            calculateAndDisplayResult(result);
        } catch (IllegalArgumentException e) {
            resultLabel.setText(e.getMessage());
        }
    }

    @FXML
    private void handleMultiplication() {
        clearResultLabel();
        try {
            polynomial1.parsePolynomial(polynomialField1.getText());
            polynomial2.parsePolynomial(polynomialField2.getText());
            Polynomial result = polynomial1.multiply(polynomial2);
            calculateAndDisplayResult(result);
        } catch (IllegalArgumentException e) {
            resultLabel.setText(e.getMessage());
        }
    }


    @FXML
    private void handleDivision() {
        clearResultLabel();
        try {
            polynomial1.parsePolynomial(polynomialField1.getText());
            polynomial2.parsePolynomial(polynomialField2.getText());
            Polynomial result = polynomial1.divide(polynomial2);
            calculateAndDisplayResult(result);
        } catch (IllegalArgumentException e) {
            resultLabel.setText(e.getMessage()); // Afiseaza un mesaj de eroare in caz ca avem impartire la 0
        }
    }


    @FXML
    private void handleDerivative() {
        clearResultLabel();
        try {
            polynomial1.parsePolynomial(polynomialField1.getText());
            Polynomial result = polynomial1.derivative();
            calculateAndDisplayResult(result);
        } catch (IllegalArgumentException e) {
            resultLabel.setText(e.getMessage());
        }
    }

    @FXML
    private void handleIntegration() {
        clearResultLabel();
        try {
            polynomial1.parsePolynomial(polynomialField1.getText());
            Polynomial result = polynomial1.integrate();
            calculateAndDisplayResult(result);
        } catch (IllegalArgumentException e) {
            resultLabel.setText(e.getMessage());
        }
    }

    private void calculateAndDisplayResult(Polynomial result) {
        resultLabel.setText(" " + result.toString());

    }

    public void clearResultLabel() {
        resultLabel.setText("");
    }

}