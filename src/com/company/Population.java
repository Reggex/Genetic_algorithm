package com.company;

/**
 * Популяция
 */
public class Population {

    private double averageFF;
    private double averageSum;
    private int averageTaxi;
    private int averageWithoutTaxi;
    private int sumPassengerRailway;
    public static int sizeOfPopulation = 200;
    private double percent;
    public Individual[] individualsArray = new Individual[sizeOfPopulation];

    /**
     * Конструктор популяции (массив особи)
     * @param individual - особь
     */
    public Population(Individual[] individual){
        individualsArray = individual;
        this.setAverageFF();
    }


    public int getAverageTaxi() {
        return averageTaxi;
    }

    public void setAverageTaxi() {
        averageTaxi = 0;
        for (int i = 0; i < sizeOfPopulation; i++) {
            averageTaxi = averageTaxi + individualsArray[i].getCostOfTaxi();
        }
        averageTaxi = averageTaxi/sizeOfPopulation;
    }

    public int getAverageWithoutTaxi() {
        return averageWithoutTaxi;
    }

    public void setAverageWithoutTaxi() {
        averageWithoutTaxi = 0;
        for (int i = 0; i < sizeOfPopulation; i++) {
            averageWithoutTaxi = averageWithoutTaxi + individualsArray[i].getCostOfRailwayGround();
        }
        averageWithoutTaxi = averageWithoutTaxi/sizeOfPopulation;
    }


    public double getPercent() {
        return percent;
    }

    public void setPercent() {
        this.percent = 100 * this.averageTaxi / this.averageWithoutTaxi;
    }

    public int getSumPassengerRailway() {
        return sumPassengerRailway;
    }

    /**
     * Вычиление пассажиропотока каждого
     */
    public void setSumPassengerRailway() {
        sumPassengerRailway = 0;
        for (int i = 0; i < sizeOfPopulation; i++) {
            sumPassengerRailway = sumPassengerRailway + individualsArray[i].getCapacityOfRailway();
        }
        sumPassengerRailway = sumPassengerRailway/sizeOfPopulation;
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

    /**
     * Вычисление средней стоимости всех особей одного поколения.
     */
    public void setAverageSum() {
        averageSum = 0;
        for (int i = 0; i < sizeOfPopulation; i++) {
            averageSum = averageSum + individualsArray[i].getSumOfCost();
        }
        averageSum = averageSum/sizeOfPopulation;
    }
}
