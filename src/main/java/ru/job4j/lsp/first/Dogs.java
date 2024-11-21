package ru.job4j.lsp.first;

public class Dogs {

    protected int weightCormForEating;

    public Dogs(int weightCormForEating) {
        this.weightCormForEating = weightCormForEating;
    }

    public void move(int weightCormForEating) {
        int minWeightCormForEating = 1;
        if (weightCormForEating < 0) {
            throw new IllegalArgumentException("Weight of corm must be great then 0");
        }
        if (weightCormForEating < minWeightCormForEating) {
            throw new IllegalArgumentException("Weight of corm for dogs must be min 1 kg");
        }
        System.out.println("Dog is moving...");
    }

}

class Spitz extends Dogs {

    public Spitz(int weightCormForEating) {
        super(weightCormForEating);
    }
}

class TibetanMastiff extends Dogs {

    public TibetanMastiff(int weightCormForEating) {
        super(weightCormForEating);
    }

    /**
     * В данном случае происходит нарушение 1 пункта LSP принципа,
     * а именно: происходит "усиление" предусловия.
     * Если в классе Spitz переопределять и проводить проверки не было необходимости,
     * так как размеры собаки удовлетворяли проверке условий из родительского класса Dog,
     * то в текущем классе размеры собаки в разы больше, и приходится переопределять
     * метод move, а в дальнейшем(возможно) придется проводить проверку на наследника класса Dog.
     * @param weightCormForEating
     */
    @Override
    public void move(int weightCormForEating) {
        int minWeightCormForEating = 10;
        if (weightCormForEating < 0) {
            throw new IllegalArgumentException("Weight of corm must be great then 0");
        }
        if (weightCormForEating < minWeightCormForEating) {
            throw new IllegalArgumentException("Weight of corm for dogs must be min 10 kg");
        }
        System.out.println("Dog is moving...");
    }
}