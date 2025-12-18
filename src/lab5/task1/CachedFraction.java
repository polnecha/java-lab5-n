package lab5.task1;

public class CachedFraction implements Fractionable {
    private int numerator;
    private int denominator;
    private Double cachedValue = null; // Кэш значения (null = не вычислено)

    public CachedFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть нулём!");
        }
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public double getValue() {
        if (cachedValue == null) {
            cachedValue = (double) numerator / denominator;
        }
        return cachedValue;
    }

    @Override
    public void setNum(int num) {
        this.numerator = num;
        cachedValue = null; // Сбрасываем кэш
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
        cachedValue = null;
    }

    // Геттеры
    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CachedFraction that = (CachedFraction) obj;
        return numerator == that.numerator && denominator == that.denominator;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numerator) * 31 + Integer.hashCode(denominator);
    }
}