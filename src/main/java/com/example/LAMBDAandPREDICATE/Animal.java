package com.example.LAMBDAandPREDICATE;

public class Animal {
    private String species;
    private boolean canHope;
    private boolean canSwim;

    public Animal(String species, boolean canHope, boolean canSwim) {
        this.species = species;
        this.canHope = canHope;
        this.canSwim = canSwim;
    }

    public boolean canHope() {
        return canHope;
    }

    public boolean canSwim() {
        return canSwim;
    }

    @Override
    public String toString() {
        return  species ;
    }
}
