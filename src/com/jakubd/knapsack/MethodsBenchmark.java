package com.jakubd.knapsack;

import com.jakubd.knapsack.dynamic.KnapsackDynamic;
import com.jakubd.knapsack.ga.KnapsackGA;

import java.util.List;

/**
 * Compares methods of solving problem
 */
public class MethodsBenchmark
{
    private KnapsackProblem[] methods;
    private KnapsackProblem modelMethod;
    private boolean printTests = true;

    public MethodsBenchmark(KnapsackProblem modelMethod, KnapsackProblem[] methods)
    {
        this.modelMethod = modelMethod;
        this.methods = methods;
    }

    /**
     * Returns sum of treasures values picked by thief
     */
    public int getResult(KnapsackProblem knapsackProblem, Treasure[] treasures, int knapsackSize)
    {
        int result = 0;
        List<Treasure> resultTreasures = knapsackProblem.solve(treasures, knapsackSize);
        for (Treasure treasure : resultTreasures)
        {
            result += treasure.getValue();
        }
        return result;
    }

    public void runTest(int tests)
    {
        int perfectSolutions[] = new int[methods.length];
        double error[] = new double[methods.length];
        double maxError[] = new double[methods.length];
        double averageTime[] = new double[methods.length];
        long maxTime[] = new long[methods.length];

        double modelAverageTime = 0.0;
        long modelMaxTime = 0;

        System.out.println("----- METHODS -----");
        System.out.printf("Model method\t%s\n", modelMethod.getDescription());
        for (int i = 0; i < methods.length; i++)
        {
            System.out.printf("Method %-6d\t%s\n", i + 1, methods[i].getDescription());
        }

        for (int i = 0; i < tests; i++)
        {
            final int knapsackSize = 100;
            Treasure[] treasures = TreasuresGenerator.generate(knapsackSize, 100);

            if (printTests)
            {
                System.out.printf("TEST: %-5dNumber of Treasures: %-10d\n", i, treasures.length);
            }

            long startTime = System.currentTimeMillis();
            int modelResult = getResult(modelMethod, treasures, knapsackSize);
            long stopTime = System.currentTimeMillis();
            long modelTime = stopTime - startTime;
            modelAverageTime += modelTime;
            modelMaxTime = Math.max(modelMaxTime, modelTime);
            if (printTests)
            {
                System.out.printf("Model:      \tResult: %-5d\tTime (ms): %-5d\n", modelResult, modelTime);
            }


            for (int j = 0; j < methods.length; j++)
            {
                startTime = System.currentTimeMillis();
                int result = getResult(methods[j], treasures, knapsackSize);
                stopTime = System.currentTimeMillis();
                long time = stopTime - startTime;

                if (result == modelResult)
                    perfectSolutions[j]++;

                double currentError = (modelResult - result) / (double)modelResult;
                error[j] += currentError;
                maxError[j] = Math.max(maxError[j], currentError);

                averageTime[j] += time;
                maxTime[j] = Math.max(maxTime[j], time);

                if (printTests)
                {
                    System.out.printf("Method: %-5d\tResult: %-5d\tTime (ms): %-5d\tError: %-5.2f\tTime difference (ms): %d\n",
                            j + 1, result, time, currentError * 100, modelTime - time);
                }
            }
        }

        System.out.println("----- RESULTS ----");
        System.out.println("Number of tests: " + tests);
        System.out.printf("Model: %-6s", "");
        System.out.printf("Found perfect solutions: %4d/%-4d\t", tests, tests);
        System.out.printf("Average error: %.2f%%\t", 0.0);
        System.out.printf("Max error: %.2f%%\t", 0.0);
        modelAverageTime /= (double)tests;
        System.out.printf("Average time (ms): %-5.2f\t", modelAverageTime);
        System.out.printf("Max time (ms): %-5d\n", modelMaxTime);
        for (int i = 0; i < methods.length; i++)
        {
            System.out.printf("Method: %-5d", i + 1);
            System.out.printf("Found perfect solutions: %4d/%-4d\t", perfectSolutions[i], tests);
            error[i] /= (double)tests;
            System.out.printf("Average error: %.2f%%\t", error[i] * 100);
            System.out.printf("Max error: %.2f%%\t", maxError[i] * 100);
            averageTime[i] /= (double)tests;
            System.out.printf("Average time (ms): %-5.2f\t", averageTime[i]);
            System.out.printf("Max time (ms): %-5d\n", maxTime[i]);
        }
    }

    public void enablePrintTest(boolean printTests)
    {
        this.printTests = printTests;
    }

    public static void main(String[] args)
    {
        KnapsackProblem[] problems = new KnapsackProblem[5];
        for (int i = 0; i < 5; i++)
            problems[i] = new KnapsackGA((int) (10 * Math.pow(3, i + 1)), (int) (10 * Math.pow(2, i + 1)), 0.85, 0.1);
        MethodsBenchmark methodsBenchmark = new MethodsBenchmark(new KnapsackDynamic(), problems);
        methodsBenchmark.enablePrintTest(false);
        methodsBenchmark.runTest(10);
    }

}
