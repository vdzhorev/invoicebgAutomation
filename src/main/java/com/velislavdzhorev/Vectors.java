package com.velislavdzhorev;

public class Vectors {
    public static int[] findShortest(int[][] vectors) {
        int[] shortestVector = null;
        double minSquared = Double.MAX_VALUE;

        for (int[] vector : vectors) {
            double vectorSquared = vector[0] * vector[0] + vector[1] * vector[1] + vector[2] * vector[2];

            if (vectorSquared < minSquared) {
                minSquared = vectorSquared;
                shortestVector = vector;
            }
        }

        return shortestVector;
    }

    public static void main(String[] args) {
        int[][] vectors = {
                new int[] { 1, 1, 1 },
                new int[] { 2, 2, 2 },
                new int[] { 3, 3, 3 }
        };

        int[] shortest = Vectors.findShortest(vectors);
        System.out.println("x: " + shortest[0] + " y: " + shortest[1] + " z: " + shortest[2]);
    }
}
