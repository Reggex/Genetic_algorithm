package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static int sizeOfInfrastructure = 500;                   // количество объектов инфраструктуры всего
    public static double differenceFF;

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
        ArrayList<Population> populations = new ArrayList<>();
        populations.add(0,population);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Фитнес функция поколения: " + population.getAverageFF());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        /**
         * По количеству поколений
         */
        /*for (int i = 1; i < 5; i++) {
            *//*if (i==3){
            individuals[1]=Individual.mutationChild(individuals[1], infrastructureObjects);
            }*//*
            individuals = crossingOver1(individuals);
            //outputIndivArray(individuals);
            sortIndivArray(individuals);
            population = new Population(individuals);
            populations.add(i,population);
            differenceFF = calculateFF(populations);

            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("Фитнес функция поколения: " + population.getAverageFF());
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println();
        }*/

        int p = 1;
        while (p < 3) {
            individuals = crossingOver1(individuals);
            //outputIndivArray(individuals);
            sortIndivArray(individuals);
            population = new Population(individuals);
            populations.add(p, population);

            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("Фитнес функция поколения: " + population.getAverageFF());
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println();
            p++;
        }

        differenceFF = calculateFF(populations);

        while(differenceFF > 0) {
            /*int i=2;
            if (i==3){
                individuals[1]=Individual.mutationChild(individuals[1], infrastructureObjects);
            }*/
            individuals = crossingOver1(individuals);
            //outputIndivArray(individuals);
            sortIndivArray(individuals);
            population = new Population(individuals);
            populations.add(p, population);
            differenceFF = calculateFF(populations);

            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("Фитнес функция поколения: " + population.getAverageFF());
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println();

            p++;

        }

        for (Population value : populations) {
            System.out.println("FF: " + value.getAverageFF());
            value.setAverageSum();
            System.out.println("Cost: " + value.getAverageSum());
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
        for (int i = 0; i < individuals.length; i++) {
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
    public static Individual[] crossingOver1(Individual[] individuals){
        Individual[] newIndividuals = new Individual[Population.sizeOfPopulation];
        int half = Population.sizeOfPopulation / 2;
        Individual[] variantIndividuals = new Individual[(half*half-half)/2];
        int count = 0;
        for (int i = 0; i < half-1; i++) {
            for (int j = 1; j < half; j++) {
                if (j > i){
                    variantIndividuals[count] = Individual.child(individuals[i],individuals[j]);
                    count++;
                }
            }
        }
        Arrays.sort(variantIndividuals, Collections.reverseOrder());
        for (int i = 0; i < half; i++) {
            newIndividuals[i] = individuals[i];
        }
        for (int i = 0; i < half; i++) {
            newIndividuals[half+i] = variantIndividuals[i];
        }
        return newIndividuals;
    }


    public static double calculateFF(ArrayList<Population> populations){
        int i = (populations.size()-1);
        differenceFF = populations.get(i).getAverageFF() - populations.get(i-2).getAverageFF();
        return differenceFF;
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