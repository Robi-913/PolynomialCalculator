package org.example.pt2024_30225_ardelean_robert_assignment_1;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {

    private final Map<Integer, Double> terms;

    public Polynomial() {
        this.terms = new HashMap<>();
    }


    public void parsePolynomial(String input) {
        terms.clear();

        Pattern termPattern = Pattern.compile("([-+]?\\b\\d*x\\^?\\d*|[-+]?\\b\\d+\\.?\\d*)");
        Matcher termMatcher = termPattern.matcher(input.replaceAll("\\s+", "")); // Elimina spatii  pentru a simplifica analiza

        while (termMatcher.find()) {
            String term = termMatcher.group(1); // Extrage termenul identificat de expresia regulata

            double coefficient = 1; // Coeficientul implicit este 1, pentru a gestiona cazul in care termenul este doar 'x'
            int exponent = 0; // Exponentul implicit este 0, pentru termenii constanti (numerele fara 'x')

            if (term.contains("x")) {
                // Cazul in care termenul contine variabila 'x'
                if (term.equals("x")) {
                    // Daca termenul este doar 'x', atunci exponentul este 1
                    exponent = 1;
                } else {
                    // Separa coeficientul de exponent, daca exista
                    String[] parts = term.split("x\\^?");
                    if (parts.length > 0 && !parts[0].equals("-") && !parts[0].equals("+") && !parts[0].isEmpty()) {
                        // Daca exista un coeficient explicit inainte de 'x', il converteste din string in double
                        coefficient = Double.parseDouble(parts[0]);
                    } else if (parts[0].equals("-")) {
                        // Daca termenul incepe cu '-', coeficientul este -1
                        coefficient = -1;
                    }

                    if (parts.length == 2 && !parts[1].isEmpty()) {
                        // Daca dupa 'x' urmeaza un exponent explicit, il converteste din string in int
                        exponent = Integer.parseInt(parts[1]);
                    } else if (term.endsWith("x")) {
                        // Daca termenul se termina cu 'x', fara exponent explicit, atunci exponentul este 1
                        exponent = 1;
                    }
                }
            } else {
                // Cazul in care termenul este un numar constant (fara 'x')
                coefficient = Double.parseDouble(term); // Converteste termenul din string in double
            }

            // Adauga sau actualizeaza coeficientul pentru exponentul gasit
            // Daca un exponent este deja prezent in polinom, aduna noul coeficient la valoarea existenta
            terms.merge(exponent, coefficient, Double::sum);
        }
    }


    // Realizeaza operatii de adunare sau scadere intre polinoame
    private Polynomial operate(Polynomial other, boolean isAdd) {
        Polynomial result = new Polynomial();
        // Adauga termenii polinomului curent in rezultat
        terms.forEach((exponent, coefficient) -> result.terms.merge(exponent, coefficient, Double::sum));
        // Adauga sau scade termenii polinomului operat in/din rezultat
        other.terms.forEach((exponent, coefficient) -> result.terms.merge(exponent, isAdd ? coefficient : -coefficient, Double::sum));
        // Elimina termenii cu coeficientul 0
        result.terms.values().removeIf(coefficient -> coefficient == 0);
        return result;
    }

    public Polynomial add(Polynomial other) {
        return operate(other, true);
    }

    public Polynomial subtract(Polynomial other) {
        return operate(other, false);
    }

    // Inmulteste doua polinoame
    public Polynomial multiply(Polynomial other) {
        Polynomial result = new Polynomial();
        terms.forEach((thisExponent, thisCoeff) -> other.terms.forEach((otherExponent, otherCoeff) ->
                // Inmulteste coeficientii si aduna exponentii termenilor
                result.terms.merge(thisExponent + otherExponent, thisCoeff * otherCoeff, Double::sum)));
        return result;
    }

    // Returneaza gradul polinomului
    public int degree() {
        return this.terms.keySet().stream().max(Integer::compare).orElse(0);
    }

    // Returneaza coeficientul termenului de grad maxim
    public double leadingCoefficient() {
        return this.terms.getOrDefault(this.degree(), 0.0);
    }

    // Imparte polinomul curent la un alt polinom
    public Polynomial divide(Polynomial divisor) throws IllegalArgumentException {
        if (divisor.terms.isEmpty() || divisor.leadingCoefficient() == 0) {
            throw new IllegalArgumentException("Nu se poate imparti la polinomul zero.");
        }

        if (this.degree() < divisor.degree()) {
            throw new IllegalArgumentException("Gradul divizorului este mai mare decat gradul dividendului.");
        }

        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();
        remainder.terms.putAll(this.terms);

        while (!remainder.terms.isEmpty() && remainder.degree() >= divisor.degree()) {
            int degreeDiff = remainder.degree() - divisor.degree();
            double coeffDiff = remainder.leadingCoefficient() / divisor.leadingCoefficient();
            Polynomial term = new Polynomial();
            term.terms.put(degreeDiff, coeffDiff);
            quotient = quotient.add(term);
            Polynomial product = term.multiply(divisor);
            remainder = remainder.subtract(product);
        }
        System.out.println(remainder);

        return quotient;
    }

    // Calculeaza derivata polinomului
    public Polynomial derivative() {
        Polynomial result = new Polynomial();
        terms.forEach((exponent, coefficient) -> {
            if (exponent > 0) result.terms.put(exponent - 1, coefficient * exponent);
        });
        return result;
    }

    // Calculeaza integrala polinomului
    public Polynomial integrate() {
        Polynomial result = new Polynomial();
        terms.forEach((exponent, coefficient) -> result.terms.put(exponent + 1, coefficient / (exponent + 1)));
        return result;
    }

    // Formateaza un termen al polinomului pentru afisare
    private void formatTerm(StringBuilder builder, int exponent, double coefficient) {
        if (!builder.isEmpty()) builder.append(coefficient > 0 ? " + " : " - ");
        else if (coefficient < 0) builder.append("-");
        String formattedCoeff = String.format("%s", Math.abs(coefficient) != 1 || exponent == 0 ? String.format("%.2f", Math.abs(coefficient)).replaceAll("([.][0-9]*?)0+$", "$1").replaceAll("[.]$", "") : "");
        builder.append(formattedCoeff);
        if (exponent == 1) builder.append("x");
        else if (exponent > 1) builder.append("x^").append(exponent);
    }

    // Returneaza reprezentarea polinomului ca string
    @Override
    public String toString() {
        if (terms.isEmpty()) return "0";
        StringBuilder builder = new StringBuilder();
        // Sorteaza termenii in ordine descrescatoare dupa exponent si ii formateaza pentru afisare
        terms.entrySet().stream()
                .sorted(Map.Entry.<Integer, Double>comparingByKey().reversed())
                .forEach(entry -> formatTerm(builder, entry.getKey(), entry.getValue()));
        return builder.toString();
    }

}