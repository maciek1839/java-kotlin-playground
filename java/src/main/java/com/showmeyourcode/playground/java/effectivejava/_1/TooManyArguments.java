package com.showmeyourcode.playground.java.effectivejava._1;

public class TooManyArguments {

    private String A;
    private String B;

    private TooManyArguments(String a, String b) {
        A = a;
        B = b;
    }

    public static class Builder{
        private String a;
        private String b;

        public Builder() {
        }

        public Builder setA(String a) {
            this.a = a;
            return this;
        }

        public Builder setB(String b) {
            this.b = b;
            return this;
        }

        public TooManyArguments build() throws IllegalArgumentException{
            // The build() method is where the final object is constructed.
            // It’s an appropriate place for validation because at this point, all the necessary fields are gathered and ready for checking.

            if(a == null && b == null){
                throw new IllegalArgumentException("At least one property should not be null.");
            }

            return new TooManyArguments(a, b);
        }
    }
}
