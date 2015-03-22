package com.jakubd.knapsack;

import java.util.List;

public interface KnapsackProblem
{
    /**
     * Solves Knapsack problem with given parameters
     * @param treasures list of available treasures
     * @param knapsackCapacity capacity of knapsack
     * @return List of picked treasures
     */
    public List<Treasure> solve(Treasure[] treasures, int knapsackCapacity);

    /**
     * Short description how and with what parameters problem is solved
     */
    public String getDescription();
}
