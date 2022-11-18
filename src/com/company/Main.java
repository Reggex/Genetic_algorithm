package com.company;

import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static int sizeOfInfrastructure = 500;                   // количество объектов инфраструктуры всего

    public static void main(String[] args) {
	// write your code here

        InfrastructureObject[] infrastructureObjects = new InfrastructureObject[sizeOfInfrastructure];
        createInfrArray(infrastructureObjects);
        outputInfrArray(infrastructureObjects);

        Individual[] individuals = new Individual[Population.sizeOfPopulation];
        for (int i = 0; i < Population.sizeOfPopulation; i++) {
            System.out.println((i+1) + "-ая особь:");
            individuals[i] = new Individual(infrastructureObjects);
            System.out.println();
        }

        System.out.println("******************************************************************");
        System.out.println("******************************************************************");
        outputIndivArray(individuals);
        sortIndivArray(individuals);
        Population population = new Population(individuals);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Фитнес функция поколения: " + population.getAverageFF());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        for (int i = 0; i < 6; i++) {
            if (i==3){
                individuals[1]=Individual.mutationChild(individuals[1], infrastructureObjects);
            }
            individuals = crossingOver(individuals);
            //outputIndivArray(individuals);
            sortIndivArray(individuals);
            population = new Population(individuals);
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("Фитнес функция поколения: " + population.getAverageFF());
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println();

        }



    }


    /**
     * Создание объектов инфраструктуры.
     * @param infrastructureObjects - массив объектов инфраструктуры
     */
    public static void createInfrArray(InfrastructureObject[] infrastructureObjects){
        for (int i = 0; i < sizeOfInfrastructure; i++) {
            infrastructureObjects[i] = new InfrastructureObject();
        }
    }


    /**
     * Вывод на экран всех объектов инфраструктуры.
     * @param infrastructureObjects - массив объектов инфраструктуры
     */
    public static void outputInfrArray(InfrastructureObject[] infrastructureObjects){
        for (int i = 0; i < sizeOfInfrastructure; i++) {
            System.out.println((i+1) + "-ый объект инфраструктуры");
            System.out.println("Стоимость: " + infrastructureObjects[i].getCost());
            System.out.println("Пассажиропоток: " + infrastructureObjects[i].getCapacity());
            System.out.println("Тип: " + infrastructureObjects[i].getType());
            System.out.println("----------------------------------------------------------------------");
        }
    }


    /**
     * Вывод на экран всех параметров особей.
     * @param individuals - массив особей
     */
    public static void outputIndivArray(Individual[] individuals){
        for (int i = 0; i < Population.sizeOfPopulation; i++) {
            System.out.println((i+1) + "-ая особь");
            System.out.println("Cтоимость всех объектов: " + individuals[i].getSumOfCost());
            System.out.println("Пассажиропоток всех объектов: " + individuals[i].getSumOfCapacity());
            System.out.println("Пассажиропоток по ЖД: " + individuals[i].getCapacityOfRailway());
            System.out.println("Критерий по такси: " + individuals[i].costFF + " " + individuals[i].getCostOfTaxi()
                    + " " + individuals[i].getCostOfRailwayGround());
            System.out.println("Фитнес функция: " + individuals[i].getFitnessFunction());
            System.out.println("-------------------------------------------------------------------------");
        }
    }


    /**
     * Сортировка массива особей по фитнес функции.
     * @param individuals - массив особей
     */
    public static void sortIndivArray(Individual[] individuals){
        Arrays.sort(individuals, Collections.reverseOrder());
        for (int i = 0; i < Population.sizeOfPopulation; i++) {
            System.out.println((i + 1) + "-ая особь:");
            System.out.println("Фитнес функция: " + individuals[i].getFitnessFunction());
        }
        System.out.println();
    }


    /**
     * Функция одноточечного кроссинговера.
     * @param individuals - массив существующих особей
     * @return новый массив особей
     */
    public static Individual[] crossingOver(Individual[] individuals){
        Individual[] newIndividuals = new Individual[Population.sizeOfPopulation];
        int half = Population.sizeOfPopulation / 2;
        int count = 0;
        for (int i = 0; i < half; i++) {
            newIndividuals[i] = individuals[i];
        }
        for (int i = 0; i < half-1; i++) {
            for (int j = 1; j < half; j++) {
                if (i!=j){
                    newIndividuals[half+count] = Individual.child(individuals[i],individuals[j]);
                    count++;
                }
            }
        }
        return newIndividuals;
    }

}









/*
//
System.out.println("Cтоимость всех объектов: " + newIndividuals[half+count].getSumOfCost());
System.out.println("Фитнес функция: " + newIndividuals[half+count].getFitnessFunction());
System.out.println("-------------------------------------------------------------------------");
System.out.println("Cтоимость всех объектов: " + individuals[i].getSumOfCost());
System.out.println("Фитнес функция: " + individuals[i].getFitnessFunction());
System.out.println("-------------------------------------------------------------------------");
System.out.println("Cтоимость всех объектов: " + individuals[j].getSumOfCost());
System.out.println("Фитнес функция: " + individuals[j].getFitnessFunction());
System.out.println("-------------------------------------------------------------------------");//
 */