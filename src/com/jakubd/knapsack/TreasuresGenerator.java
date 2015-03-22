package com.jakubd.knapsack;

import java.util.Random;

public class TreasuresGenerator
{
    /**
     * Generates list of treasures with given parameters
     * @param knapsackSize  max weight of single treasure will be in range <1, knapsackSize/2>
     * @param maxTreasures  number of treasures will be in range <1, maxTreasures)
     */
    public static Treasure[] generate(int knapsackSize, int maxTreasures)
    {
        Random random = new Random();
        Treasure[] treasures = new Treasure[random.nextInt(maxTreasures - 1) + 1];
        for (int i = 0; i < treasures.length; i++)
        {
            treasures[i] = new Treasure(random.nextInt(knapsackSize/2) + 1, random.nextInt(100) + 1);
        }
        return  treasures;
    }
}
