package com.company;

import java.util.*;

public class Main {

    public static int sizeOfInfrastructure = 500;                   // количество объектов инфраструктуры всего
    public static int numberOfGenerations = 2;
    public static double deltaFF = 0.05;


    public static void main(String[] args) {
	// write your code here
        double differenceFF = 0;
        boolean addCost=false;

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

        int p = 1;
        while (p < 3) {
            //individuals[1]=Individual.mutationChild(individuals[1], infrastructureObjects);
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

        differenceFF = calculateFF(populations, differenceFF);

        while(differenceFF > deltaFF) {
            /*int i=2;
            if (i==3){
                individuals[1]=Individual.mutationChild(individuals[1], infrastructureObjects);
            }*/
            individuals = crossingOver1(individuals);
            //outputIndivArray(individuals);
            sortIndivArray(individuals);
            population = new Population(individuals);
            populations.add(p, population);
            differenceFF = calculateFF(populations, differenceFF);

            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("Фитнес функция поколения: " + population.getAverageFF());
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println();
            p++;
        }

        System.out.println("FF: ");
        for (Population value : populations) {
            System.out.println(value.getAverageFF());
        }
        System.out.println();
        System.out.println("Cost: ");
        for (Population value : populations) {
            value.setAverageSum();
            System.out.println(value.getAverageSum());
        }
        System.out.println();
        System.out.println("Railway: ");
        for (Population value : populations) {
            value.setSumPassengerRailway();
            System.out.println(value.getSumPassengerRailway());
        }
        System.out.println();
        System.out.println("Sum of Taxi: ");
        for (Population value : populations) {
            value.setAverageTaxi();
            System.out.println(value.getAverageTaxi());
        }
        System.out.println();
        System.out.println("Sum without taxi: ");
        for (Population value : populations) {
            value.setAverageWithoutTaxi();
            System.out.println(value.getAverageWithoutTaxi());
        }
        System.out.println();
        System.out.println("Percent of taxi cost: ");
        for (Population value : populations) {
            value.setPercent();
            System.out.println(value.getPercent());
        }
        populations.get(populations.size()-1).setAverageSum();

        while(addCost==false){

            //individuals[1]=Individual.mutationChild(individuals[1], infrastructureObjects);
            Individual[] newIndividuals = new Individual[Population.sizeOfPopulation];
            for (int i = 0; i < Population.sizeOfPopulation; i++) {
                newIndividuals[i]=Individual.newIndividual(individuals[i], infrastructureObjects);
            }
            population = new Population(newIndividuals);
            populations.add(population);
            individuals=newIndividuals;

            differenceFF = calculateFF(populations, differenceFF);        //
            //
            while(differenceFF > deltaFF) {                 //
                individuals =  crossingOver1(individuals);   //
                sortIndivArray(individuals);                //
                population = new Population(individuals);   //
                populations.add(population);                //
                differenceFF = calculateFF(populations, differenceFF);    //
            }                                               //

            for (Population value : populations) {
                value.setAverageFF();
                value.setAverageSum();
                value.setSumPassengerRailway();
                value.setAverageTaxi();
                value.setAverageWithoutTaxi();
                value.setPercent();
            }

            if(populations.get(populations.size()-1).getAverageSum() > 495){
                addCost = true;
            }

            for (int i = 0; i < Population.sizeOfPopulation; i++) {
                if(individuals[i].getSumOfCost()>495){
                    addCost = true;
                }
            }
        }

        if (populations.get(populations.size()-1).getAverageSum() < 495){
            Individual[] newIndividuals = new Individual[Population.sizeOfPopulation];
            for (int i = 0; i < Population.sizeOfPopulation; i++) {
                newIndividuals[i]=Individual.lastIndividual(individuals[i], infrastructureObjects);
            }
            population = new Population(newIndividuals);
            populations.add(population);
            populations.add(population);
            populations.add(population);
        }

        System.out.println("FF: ");
        for (Population value : populations) {
            System.out.println(value.getAverageFF());
        }
        System.out.println();
        System.out.println("Cost: ");
        for (Population value : populations) {
            value.setAverageSum();
            System.out.println(value.getAverageSum());
        }
        System.out.println();
        System.out.println("Railway: ");
        for (Population value : populations) {
            value.setSumPassengerRailway();
            System.out.println(value.getSumPassengerRailway());
        }
        System.out.println();
        System.out.println("Sum of Taxi: ");
        for (Population value : populations) {
            value.setAverageTaxi();
            System.out.println(value.getAverageTaxi());
        }
        System.out.println();
        System.out.println("Sum without taxi: ");
        for (Population value : populations) {
            value.setAverageWithoutTaxi();
            System.out.println(value.getAverageWithoutTaxi());
        }
        System.out.println();
        System.out.println("Percent of taxi cost: ");
        for (Population value : populations) {
            value.setPercent();
            System.out.println(value.getPercent());
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
     * Вовремя кроссинговера выбираются лучшие из полученных потомков.
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

    /**
     * Функция одноточечного кроссинговера.
     * Вовремя кроссинговера выбираются лучшие из полученных потомков.
     * @param individuals - массив существующих особей
     * @return новый массив особей
     */
    public static Individual[] crossingOverSecondPoint(Individual[] individuals){
        Individual[] newIndividuals = new Individual[Population.sizeOfPopulation];
        int half = Population.sizeOfPopulation / 2;
        Individual[] variantIndividuals = new Individual[(half*half-half)/2];
        int count = 0;
        for (int i = 0; i < half-1; i++) {
            for (int j = 1; j < half; j++) {
                if (j > i){
                    variantIndividuals[count] = Individual.childSecondPoint(individuals[i],individuals[j]);
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

    /**
     * Функция одноточечного кроссинговера.
     * Во время кроссинговера выбираются случайные из полученных потомков.
     * @param individuals - массив существующих особей
     * @return новый массив особей
     */
    public static Individual[] crossingOver2(Individual[] individuals){
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

        for (int i = 0; i < half; i++) {
            newIndividuals[i] = individuals[i];
        }

        Random random = new Random();
        int randomSize=(half*half-half)/2;
        int k;
        boolean check = true;
        int[] count1 = new int[half];
        k = random.nextInt(randomSize);
        count1[0]=k;
        newIndividuals[half]=variantIndividuals[k];
        for (int i = 1; i < half; i++) {       // случайная выборка объектов инфрастуктуры без повторений
            while (check==true){
                check = false;
                k = random.nextInt(randomSize);
                for (int j = 0; j < i; j++) {
                    if (k==count1[j]){
                        check = true;
                    }
                }
            }
            count1[i]=k;
            newIndividuals[half+i]=variantIndividuals[k];
            check = true;
        }
        return newIndividuals;
    }


    /**
     * Вычисление разности средних фитнес функций последних numberOfGenerations+1 поколений.
     * @param populations - динамический массив популяции.
     * @return разность фитнес функций
     */
    public static double calculateFF(ArrayList<Population> populations, double differenceFF){
        int i = (populations.size()-1);
        differenceFF = populations.get(i).getAverageFF() - populations.get(i-numberOfGenerations).getAverageFF();
        return differenceFF;
    }

}
