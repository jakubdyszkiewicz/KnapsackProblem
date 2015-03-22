package com.jakubd.knapsack.ga;

import java.util.Arrays;

/**
 * Represents single result
 * example: 10001 means that thief took first and fifth treasure
 */
public class Chromosome implements Comparable
{
    private boolean[] bits;
    private double score; // how much value picked treasures have. -1 if thief can't carry that much

    public Chromosome(int numberOfBits)
    {
        bits = new boolean[numberOfBits];
    }

    public Chromosome(Chromosome chromosome)
    {
        this.bits = new boolean[chromosome.bits.length];
        this.bits = Arrays.copyOf(chromosome.bits, this.bits.length);
        this.score = chromosome.score;
    }

    public boolean getBit(int position)
    {
        return bits[position];
    }

    public void setBit(int position, boolean bit)
    {
        bits[position] = bit;
    }

    public double getScore()
    {
        return score;
    }

    public void setScore(double score)
    {
        this.score = score;
    }

    public int getNumOfBits()
    {
        return bits.length;
    }

    @Override
    @SuppressWarnings("all")
    public int compareTo(Object o)
    {
        if (o == null)
            return 0;
        Chromosome chromosome = (Chromosome) o;
        if (chromosome.getScore() == this.getScore())
            return 0;
        if (chromosome.getScore() < this.getScore())
            return 1;
        return -1;
    }

    @Override
    public String toString()
    {
        return "Chromosome{" +
                "bits=" + Arrays.toString(bits) +
                ", score=" + score +
                '}';
    }
}
