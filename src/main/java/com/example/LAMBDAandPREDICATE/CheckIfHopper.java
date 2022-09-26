package com.example.LAMBDAandPREDICATE;

public class CheckIfHopper implements CheckTrait{
    @Override
    public boolean testAnimal(Animal a) {
        return a.canHope();
    }
}
