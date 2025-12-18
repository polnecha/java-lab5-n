package lab5.task1;

public class Fraction implements Fractionable {
    private int numerator;
    private int denominator;

    // Конструктор
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть нулём!");
        }
        // Приводим к каноническому виду: знаменатель всегда положительный
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    // Геттеры
    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    // Реализация интерфейса
    @Override
    public double getValue() {
        return (double) numerator / denominator;
    }

    @Override
    public void setNum(int num) {
        this.numerator = num;
    }

    @Override
    public void setDen(int den) {
        if (den == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть нулём!");
        }
        if (den < 0) {
            this.numerator = -this.numerator;
            den = -den;
        }
        this.denominator = den;
    }

    // Приведение к строке
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    // Переопределение equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fraction fraction = (Fraction) obj;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numerator) * 31 + Integer.hashCode(denominator);
    }
}