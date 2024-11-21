package ru.job4j.lsp.second;

public class Driver {

    protected int experience;

    public Driver(int experience) {
        this.experience = experience;
    }

    public boolean checkExperience(int experience) {
        int minExperience = 2;
        if (experience < minExperience) {
            throw new IllegalArgumentException("Count of years for driving must be 2");
        }
        return true;
    }
}


class TaxiDriver extends Driver {

    public TaxiDriver(int experience) {
        super(experience);
    }

    /**
     * В данном случае происходит нарушение 2 пункта контракта LSP,
     * при котором происходит переопределение метода и "понижение" условия
     * присвоения поля experience.
     *
     * @param experience
     * @return true, если количество лет опыта удовлетворяет минимальному.
     */
    @Override
    public boolean checkExperience(int experience) {
        int minExperience = 1;
        if (experience < minExperience) {
            throw new IllegalArgumentException("Count of years for driving must be 1");
        }
        return true;
    }
}

class TruckDriver extends Driver {

    public TruckDriver(int experience) {
        super(experience);
    }
}