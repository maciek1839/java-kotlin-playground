package com.showmeyourcode.playground.java.effectivejava._3;

public class EnumsInsteadOfConstants {

    public enum Operation {
        PLUS, MINUS, TIMES, DIVIDE;

        // Do the arithmetic operation represented by this constant
        public double apply(double x, double y) {
            switch(this) {
                case PLUS:   return x + y;
                case MINUS:  return x - y;
                case TIMES:  return x * y;
                case DIVIDE: return x / y;
            }
            throw new AssertionError("Unknown op: " + this);
        }
    }

    public enum Operation2 {
        PLUS  {public double apply(double x, double y){return x + y;}},
        MINUS {public double apply(double x, double y){return x - y;}},
        TIMES {public double apply(double x, double y){return x * y;}},
        DIVIDE{public double apply(double x, double y){return x / y;}};

        public abstract double apply(double x, double y);
    }

    public enum Operation3 {
        PLUS("+") {
            public double apply(double x, double y) { return x + y; }
        },
        MINUS("-") {
            public double apply(double x, double y) { return x - y; }
        },
        TIMES("*") {
            public double apply(double x, double y) { return x * y; }
        },
        DIVIDE("/") {
            public double apply(double x, double y) { return x / y; }
        };

        private final String symbol;

        Operation3(String symbol) { this.symbol = symbol; }

        @Override public String toString() { return symbol; }

        public abstract double apply(double x, double y);
    }
}
