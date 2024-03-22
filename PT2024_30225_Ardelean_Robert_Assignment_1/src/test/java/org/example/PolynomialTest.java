package org.example;

import junit.framework.TestCase;
import org.example.pt2024_30225_ardelean_robert_assignment_1.Polynomial;

public class PolynomialTest extends TestCase {

    public void testAdd() {

        Polynomial p1 = new Polynomial();
        p1.parsePolynomial("2x^2 + 3x + 4");
        Polynomial p2 = new Polynomial();
        p2.parsePolynomial("x^3 + x^2 + 2x + 1");

        Polynomial result = p1.add(p2);

        assertEquals("x^3 + 3x^2 + 5x + 5", result.toString());

    }

    public void testSubtract() {

        Polynomial p1 = new Polynomial();
        p1.parsePolynomial("2x^2 + 3x + 4");
        Polynomial p2 = new Polynomial();
        p2.parsePolynomial("x^3 + x^2 + 2x + 1");

        Polynomial result = p1.subtract(p2);

        assertEquals("-x^3 + x^2 + x + 3", result.toString());

    }

    public void testMultiply() {

        Polynomial p1 = new Polynomial();
        p1.parsePolynomial("2x^2 + 3x + 4");
        Polynomial p2 = new Polynomial();
        p2.parsePolynomial("x^3 + x^2 + 2x + 1");

        Polynomial result = p1.multiply(p2);

        assertEquals("2x^5 + 5x^4 + 11x^3 + 12x^2 + 11x + 4", result.toString());

    }

    public void testDivide() {

        Polynomial p1 = new Polynomial();
        p1.parsePolynomial("2x^5 + 5x^4 + 11x^3 + 12x^2 + 11x + 4");
        Polynomial p2 = new Polynomial();
        p2.parsePolynomial("x^3 + x^2 + 2x + 1");

        Polynomial result = p1.divide(p2);

        assertEquals("2x^2 + 3x + 4", result.toString());

    }

    public void testDerivative() {

        Polynomial p1 = new Polynomial();
        p1.parsePolynomial("2x^2 + 3x + 4");
        Polynomial result = p1.derivative();

        assertEquals("4x + 3", result.toString());

    }

    public void testIntegrate() {

        Polynomial p1 = new Polynomial();
        p1.parsePolynomial("-2x^2 + 3x + 4");
        Polynomial result = p1.integrate();

        assertEquals("-0.67x^3 + 1.5x^2 + 4x", result.toString());

    }


}
