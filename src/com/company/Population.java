package com.company;

/**
 * Популяция
 */
public class Population {

    private double averageFF;
    public static int sizeOfPopulation = 6;
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
}
