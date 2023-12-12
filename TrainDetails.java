public class TrainDetails {
    public static abstract class Train {
        protected String name;
        protected int maxCapacity;
        protected int availableSeatsSL;
        protected int availableSeatsAC1;
        protected int availableSeatsAC2;
        protected int availableSeatsAC3;

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

        public void bookTicket(int numTicketsSL, int numTicketsAC1, int numTicketsAC2, int numTicketsAC3) {
            if (numTicketsSL <= availableSeatsSL && numTicketsAC1 <= availableSeatsAC1
                && numTicketsAC2 <= availableSeatsAC2 && numTicketsAC3 <= availableSeatsAC3) {
                availableSeatsSL -= numTicketsSL;
                availableSeatsAC1 -= numTicketsAC1;
                availableSeatsAC2 -= numTicketsAC2;
                availableSeatsAC3 -= numTicketsAC3;
                System.out.println("Ticket booked successfully.");
            } else {
                System.out.println("Not enough available seats for the requested tickets.");
            }
        }
    }

    public static class GareebRath extends Train {
        public GareebRath() {
            super("Gareeb Rath", 6);
        }
    }

    public static class Shatabdi extends Train {
        public Shatabdi() {
            super("Shatabdi", 6);
        }
    }

    public static class DoubleDecker extends Train {
        public DoubleDecker() {
            super("Double Decker", 6);
        }
    }

    public static class SuperExpress extends Train {
        public SuperExpress() {
            super("Super Express", 6);
        }
    }
}
