abstract class Train {
    private String name;
    private int maxCapacity;
    private int availableSeatsSL;
    private int availableSeatsAC1;
    private int availableSeatsAC2;
    private int availableSeatsAC3;

    public Train(String name, int maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.availableSeatsSL = maxCapacity;
        this.availableSeatsAC1 = maxCapacity;
        this.availableSeatsAC2 = maxCapacity;
        this.availableSeatsAC3 = maxCapacity;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getAvailableSeatsSL() {
        return availableSeatsSL;
    }

    public int getAvailableSeatsAC1() {
        return availableSeatsAC1;
    }

    public int getAvailableSeatsAC2() {
        return availableSeatsAC2;
    }

    public int getAvailableSeatsAC3() {
        return availableSeatsAC3;
    }

    public void bookTicket(int slSeats, int ac1Seats, int ac2Seats, int ac3Seats) {
        availableSeatsSL -= slSeats;
        availableSeatsAC1 -= ac1Seats;
        availableSeatsAC2 -= ac2Seats;
        availableSeatsAC3 -= ac3Seats;
    }
}

class GareebRath extends Train {
    public GareebRath() {
        super("Gareeb Rath", 100);
    }
}

class Shatabdi extends Train {
    public Shatabdi() {
        super("Shatabdi", 150);
    }
}

class DoubleDecker extends Train {
    public DoubleDecker() {
        super("Double Decker", 120);
    }
}

class SuperExpress extends Train {
    public SuperExpress() {
        super("Super Express", 200);
    }
}
