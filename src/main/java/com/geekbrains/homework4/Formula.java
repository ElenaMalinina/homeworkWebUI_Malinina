package com.geekbrains.homework4;

public class Formula {
    private final int a;
    private final int b;
    private final int c;

    public Formula(int a, int b, int c) throws IllegalArgumentException {
        if (!this.isGoodTriangle(a, b, c)) throw new IllegalArgumentException("Треугольник не получится");
        this.a = a;
        this.b = b;
        this.c = c;

    }
    private boolean isGoodTriangle(int a, int b, int c) {
        if ((a <= 0) || (b <= 0) || (c <= 0)) return false;
        if ((a + b <= c) && (a + c <= b) && (b + c <= a)) return  false;
        return true;
    }
    public double formula() {
        int s = (int) (a + b + c) /2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}
