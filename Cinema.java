package cinema;
import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        char[][] cinema = new char[rows][seats];
        for (char[] chars : cinema) {
            Arrays.fill(chars, 'S');
        }

        selectAction(cinema);
    }

    public static void selectAction(char[][] cinema) {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        int selection = scanner.nextInt();
        switch (selection) {
            case 1 -> showSeats(cinema);
            case 2 -> buyTicket(cinema);
            case 3 -> showStats(cinema);
            case 0 -> {}
        }
    }

    public static void showSeats(char[][] cinema) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= cinema[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(" " + cinema[i][j]);
            }
            System.out.println();
        }

        selectAction(cinema);
    }

    public static void buyTicket(char[][] cinema) {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Enter a row number:");
        int rowNr = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNr = scanner.nextInt();

        if (rowNr > cinema.length) {
            System.out.println("Wrong input!");
            buyTicket(cinema);
        } else if (seatNr > cinema[0].length) {
            System.out.println("Wrong input!");
            buyTicket(cinema);
        } else if (cinema[rowNr - 1][seatNr - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            buyTicket(cinema);
        } else {
            processTicket(rowNr, seatNr, cinema);
        }
    }

    public static void processTicket(int rowNr, int seatNr, char[][] cinema) {
        if (cinema.length * cinema[0].length <= 60) {
            int price = 10;
            System.out.println();
            System.out.print("Ticket price: ");
            System.out.println("$" + price);
        } else if (rowNr <= cinema.length / 2){
            int price = 10;
            System.out.println();
            System.out.print("Ticket price: ");
            System.out.println("$" + price);
        } else {
            int price = 8;
            System.out.println();
            System.out.print("Ticket price: ");
            System.out.println("$" + price);
        }

        cinema[rowNr - 1][seatNr - 1] = 'B';

        selectAction(cinema);
    }

    public static void showStats(char[][] cinema) {
        int ticketsPurchased = calcTickets(cinema);
        double percentage = calcPercentage(cinema);
        int currentIncome = calcCurrentInc(cinema);
        int totalIncome = calcTotalInc(cinema);

        System.out.printf("Number of purchased tickets: %d%n", ticketsPurchased);
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d", totalIncome);

        selectAction(cinema);
    }

    public static int calcTickets(char[][] cinema) {
        int ticketsPurchased = 0;
        for (char[] chars : cinema) {
            for (int j = 0; j < cinema[0].length; j++) {
                if (chars[j] == 'B') {
                    ticketsPurchased += 1;
                }
            }
        }
        return ticketsPurchased;
    }

    public static double calcPercentage(char[][] cinema) {
        double percentage;
        double purchased = 0;
        double total = cinema.length * cinema[0].length;
        for (char[] chars : cinema) {
            for (int j = 0; j < cinema[0].length; j++) {
                if (chars[j] == 'B') {
                    purchased += 1;
                }
            }
        }
        percentage = (purchased / total) * 100;
        return percentage;
    }

    public static int calcCurrentInc(char[][] cinema) {
        int currentIncome = 0;
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (cinema[i][j] == 'B') {
                    if (cinema.length * cinema[i].length <= 60) {
                        currentIncome += 10;
                    }
                    else if (i < cinema.length / 2) {
                        currentIncome += 10;
                    } else {
                        currentIncome += 8;
                    }
                }
            }
        }
        return currentIncome;
    }

    public static int calcTotalInc(char[][] cinema) {
        int totalIncome;
        if (cinema.length * cinema[0].length <= 60) {
            totalIncome = cinema.length * cinema[0].length * 10;
        } else if  (cinema.length % 2 == 0){
            totalIncome = (cinema.length / 2) * cinema[0].length * 10
                    + (cinema.length / 2) * cinema[0].length * 8;
        } else {
            totalIncome = (cinema.length / 2) * cinema[0].length * 10
                    + (cinema.length - cinema.length / 2) * cinema[0].length * 8;
        }
        return totalIncome;
    }
}