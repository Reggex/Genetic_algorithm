package com.company;

import java.util.Random;

/**
 * Особь
 */
public class Individual implements Comparable<Individual>{

    public static int sizeOfIndividual = 26;    // количество объектов инфраструктуры в одной особи
    private int sumOfCost;                      // стоимость всех объектов
    boolean sumFF = true;                       // метка выполнения критерия предела стоимости всех объектов
    private int sumOfCapacity;                  // пассажиропоток всех объектов
    private int capacityOfRailway;              // пассажиропоток ЖД транспорта
    boolean capacityFF = true;                  // метка выполнения критерия по пассажиропотоку ЖД транспорта
    private int costOfTaxi;                     // стоимость объектов такси
    private int costOfRailwayGround;            // стоимость объектов ЖД и наземного транспорта
    boolean costFF = true;                      // метка выполнения критерия по стоимости объектов такси
    private int fitnessFunction;                // фитнес-функция

    public InfrastructureObject[] individualArray = new InfrastructureObject[sizeOfIndividual]; // массив объектов инфраструктуры

    /**
     * Конструктор особи по умолчанию
     */
    public Individual() {
    }

    /**
     * Конструктор особи (массив объектов инфрастуктуры)
     * @param infrastructureObject - массив объектов инфраструктуры
     */
    public Individual(InfrastructureObject[] infrastructureObject){
        Random random = new Random();
        int k;
        boolean check = true;
        int[] count = new int[sizeOfIndividual];
        k = random.nextInt(Main.sizeOfInfrastructure);
        count[0]=k;
        individualArray[0]=infrastructureObject[k];
        for (int i = 1; i < sizeOfIndividual; i++) {       // случайная выборка объектов инфрастуктуры без повторений
            while (check==true){
                check = false;
                k = random.nextInt(Main.sizeOfInfrastructure);
                for (int j = 0; j < i; j++) {
                    if (k==count[j]){
                        check = true;
                    }
                }
            }
            count[i]=k;
            individualArray[i]=infrastructureObject[k];
            check = true;
        }
        for (int i = 0; i < sizeOfIndividual; i++) {
            System.out.print(count[i] + " ");
        }
        this.setSumOfCost();
        this.setSumOfCapacity();
        this.setCapacityOfRailway();
        this.setCostOfTaxi();
        this.setFitnessFunction();
    }


    public int getSumOfCost() {
        return sumOfCost;
    }

    /**
     * Вычисление общей стоимости всех объектов каждой особи
     */
    public void setSumOfCost() {
        sumOfCost = 0;
        for (int i = 0; i < sizeOfIndividual; i++) {
            sumOfCost = sumOfCost + individualArray[i].getCost();
        }
        if (sumOfCost > 500){
            sumFF = false;
        }
    }


    public int getSumOfCapacity() {
        return sumOfCapacity;
    }

    /**
     * Вычисление общего пассажиропотока каждой особи
     */
    public void setSumOfCapacity() {
        sumOfCapacity = 0;
        for (int i = 0; i < sizeOfIndividual; i++) {
            sumOfCapacity = sumOfCapacity + individualArray[i].getCapacity();
        }
    }


    public int getCapacityOfRailway() {
        return capacityOfRailway;
    }

    /**
     * Вычисление пассажиропотока на ж/д транспорте по критерию
     */
    public void setCapacityOfRailway() {
        capacityOfRailway = 0;
        for (int i = 0; i < sizeOfIndividual; i++) {
            if(individualArray[i].getType() == 0){
                capacityOfRailway = capacityOfRailway + individualArray[i].getCapacity();
            }
        }
        if (capacityOfRailway < 3){
            capacityFF = false;
        }
    }


    public int getCostOfTaxi() {
        return costOfTaxi;
    }

    public int getCostOfRailwayGround() {
        return costOfRailwayGround;
    }

    /**
     * Вычисление стоимости такси, наземного и ж/д транспорта по критерию
     */
    public void setCostOfTaxi() {
        costOfTaxi = 0;
        costOfRailwayGround = 0;
        for (int i = 0; i < sizeOfIndividual; i++) {
            if(individualArray[i].getType() == 2){
                costOfTaxi = costOfTaxi + individualArray[i].getCost();
            } else {
                costOfRailwayGround = costOfRailwayGround + individualArray[i].getCost();
            }
        }
        if (costOfTaxi < 0.15 * costOfRailwayGround){
            costFF = false;
        }
    }


    public int getFitnessFunction() {
        return fitnessFunction;
    }

    /**
     * Вычисление фитнес функции
     */
    public void setFitnessFunction() {
        fitnessFunction = sumOfCapacity;
        if (!sumFF){
            fitnessFunction = fitnessFunction - 100 * (sumOfCost - 500);
        }
        if (!costFF){
            fitnessFunction = (int) (fitnessFunction - 10000 * (0.15 * costOfRailwayGround - costOfTaxi/costOfRailwayGround));
        }
        if (!capacityFF){
            fitnessFunction = fitnessFunction - 1000000 * (3 - capacityOfRailway);
        }
    }

    /**
     * Переопределение функции сравнения объектов класса "Individual"(особь) по фитнес функции
     * @param o - объекты класса "Individual"(особь)
     * @return результат стравнения
     */
    //https://javarush.com/groups/posts/2262-comparator-v-java
    @Override
    public int compareTo(Individual o) {
        return this.getFitnessFunction() - o.getFitnessFunction();
    }

    /**
     * Создание ребёнка
     * @param individual1 - особь-родитель №1
     * @param individual2 - особь-родитель №2
     * @return особь-ребёнка
     */
    public static Individual child(Individual individual1, Individual individual2){
        Individual child = new Individual();
        for (int i = 0; i < 13; i++) {
            child.individualArray[i] = individual1.individualArray[i];
        }
        for (int i = 13; i < sizeOfIndividual; i++) {
            child.individualArray[i] = individual2.individualArray[i];
        }
        child.setSumOfCost();
        child.setSumOfCapacity();
        child.setCapacityOfRailway();
        child.setCostOfTaxi();
        child.setFitnessFunction();
        return child;
    }

    public static Individual mutationChild(Individual individual, InfrastructureObject[] infrastructureObject){
        Random random = new Random();
        Individual child = new Individual();
        int x = random.nextInt(sizeOfIndividual);
        int y = random.nextInt(Main.sizeOfInfrastructure);
        for (int i = 0; i < x; i++) {
            child.individualArray[i] = individual.individualArray[i];
        }
        child.individualArray[x] = infrastructureObject[y];
        for (int i = (x+1); i < sizeOfIndividual; i++) {
            child.individualArray[i] = individual.individualArray[i];
        }
        child.setSumOfCost();
        child.setSumOfCapacity();
        child.setCapacityOfRailway();
        child.setCostOfTaxi();
        child.setFitnessFunction();
        return child;
    }


}
