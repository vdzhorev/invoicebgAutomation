package com.velislavdzhorev;

public class TaxCalculator {
    public static float taxRate = 10;
    public float grossIncome;
    public float deduction;

    public TaxCalculator(float grossIncome, float deduction) {
        this.grossIncome = grossIncome;
        this.deduction = deduction;
    }

    public float getPayableTax() {
        return (TaxCalculator.taxRate / 100) * (this.grossIncome - this.deduction);
    }

    public static void main(String[] args) {
        TaxCalculator taxCalculator = new TaxCalculator(1000, 100);
        float payableTax = taxCalculator.getPayableTax();
        System.out.println(payableTax);

    }
}


