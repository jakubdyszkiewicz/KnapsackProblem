package com.jakubd.knapsack.ga;

import java.util.Arrays;
import java.util.Collections;

/**
 * Represents set of chromosomes, that is set of solutions
 */
public class Population
{
    private Chromosome[] chromosomes;

    public Population(int populationSize)
    {
        chromosomes = new Chromosome[populationSize];
    }

    public Chromosome getChromosome(int position)
    {
        return chromosomes[position];
    }

    public void setChromosome(int position, Chromosome chromosome)
    {
        chromosomes[position] = chromosome;
    }

    public void sortByScoreDesc()
    {
        Arrays.sort(chromosomes, Collections.reverseOrder());
    }

    @Override
    public String toString()
    {
        return "Population{" +
                "chromosomes=" + Arrays.toString(chromosomes) +
                '}';
    }
}
