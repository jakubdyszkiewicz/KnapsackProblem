package com.jakubd.knapsack;

/**
 * Represents single item to pick by thief
 */
public class Treasure
{
    private int weight;
    private double value;

    public Treasure(int weight, double value)
    {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight()
    {
        return weight;
    }

    public double getValue()
    {
        return value;
    }
}
