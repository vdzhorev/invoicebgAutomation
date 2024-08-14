package com.velislavdzhorev;

public class NumberAddition {

    public static void main(String[] args) {
        Number a = new Number(1);
        Integer b = Integer.valueOf(1);
        int c = 1;

        incrementNumber(a);
        incrementIntegerBy2(b);
        incrementIntBy3(c);

        System.out.println(a.getValue() + b + c);
    }

    private static void incrementNumber(Number number){
        number.increment();
    }

    private static void incrementIntegerBy2(int intArg){
        intArg += 2;
    }

    private static void incrementIntBy3(Integer integerArg){
        integerArg += 3;
    }

    private static class Number{

        private int value;

        public Number(int value) {
            this.value = value;
        }

        public void increment(){
            value += 1;
        }

        public int getValue(){
            return value;
        }
    }
}
