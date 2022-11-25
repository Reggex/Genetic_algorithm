package com.company;

import java.util.Random;

/**
 * Объект инфраструктуры
 */
public class InfrastructureObject {

    private int cost;
    private int capacity;
    private int type;           // 0 - ЖД, 1 - наземный, 2 - такси

    /**
     * Конструктор объекта инфраструктуры
     */
    public InfrastructureObject() {
        Random random = new Random();
        this.cost = 5 + random.nextInt(26);
        this.capacity = 1 + random.nextInt(5);
        this.type = random.nextInt(3);
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
