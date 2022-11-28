package com.company;

/**
 * Популяция
 */
public class Population {

    private double averageFF;
    private double averageSum;
    public static int sizeOfPopulation = 20;
    public Individual[] individualsArray = new Individual[sizeOfPopulation];

    /**
     * Конструктор популяции (массив особи)
     * @param individual - особь
     */
    public Population(Individual[] individual){
        individualsArray = individual;
        this.setAverageFF();
    }


    public double getAverageFF() {
        return averageFF;
    }

    /**
     * Вычисление среднего арифметического фитнес функции одного поколения.
     */
    public void setAverageFF() {
        averageFF = 0;
        for (int i = 0; i < sizeOfPopulation; i++) {
            averageFF = averageFF + individualsArray[i].getFitnessFunction();
        }
        averageFF = averageFF/sizeOfPopulation;
    }

    public double getAverageSum() {
        return averageSum;
    }

    public void setAverageSum() {
        averageSum = 0;
        for (int i = 0; i < sizeOfPopulation; i++) {
            averageSum = averageSum + individualsArray[i].getSumOfCost();
        }
        averageSum = averageSum/sizeOfPopulation;
    }
}
