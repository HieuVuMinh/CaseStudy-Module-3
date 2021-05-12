import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ManageLargeMotorcycles motorcycles = new ManageLargeMotorcycles();
        ManageCustomers customers = new ManageCustomers();
        Login login = new Login();
        Scanner sc = new Scanner(System.in);

        // Menu first question
        areYouCustomerOrManage();

        // Run main system
        runMain(customers, login, sc);
    }

    public static void runMain(ManageCustomers customers, Login login, Scanner sc) {
        int selection = sc.nextInt();
        loginMenu(customers, login, sc, selection);
    }

    public static void loginMenu(ManageCustomers customers, Login login, Scanner sc, int selection) {
        switch (selection){
            case 1 -> {
                login.login();
                System.err.println(" ----------------------------------------------------------------------------");
                System.err.println("|  If you have a member account, 3% discount on the total bill will be made  |");
                System.err.println(" ----------------------------------------------------------------------------");
                System.out.println("Press 1 to next");
                System.out.println("Press 0 to skip");
                int choice = sc.nextInt();
                switch (choice){
                    case 0 -> {
                        do {
                            customers.menuOfCustomer();
                        }while (true);
                    }
                    case 1 -> login.customerLogin();
                    default -> {
                        System.err.println("Wrong choice!");
                        loginMenu(customers, login, sc, selection);
                    }
                }
            }
            case 2 -> {
                login.login();
                login.manageLogin();
            }
            default -> {
                System.err.println("Wrong choice!");
                runMain(customers, login, sc);
            }
        }
    }

    public static void areYouCustomerOrManage() {
        System.out.println("-------------------------");
        System.out.println("|--------{*****}--------|");
        System.out.println("|  1. You are Customer  |");
        System.out.println("|  2. You are Manager   |");
        System.out.println("|_______________________|");
    }

}
