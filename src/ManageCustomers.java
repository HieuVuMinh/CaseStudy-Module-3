import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageCustomers {
    ManageLargeMotorcycles motorcycles = new ManageLargeMotorcycles();
    List<LargeMotorcyclesType> cart = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public ManageCustomers() {
    }

    // Menu Customer
    public void menuOfCustomer() {
        menu();
        int selection = sc.nextInt();
        switch (selection) {
            case 1 -> motorcycles.display();
            case 2 -> {
                sc.nextLine();
                System.out.println("Enter name of Motorcycle you want to find: ");
                String name = sc.nextLine();
                motorcycles.search(name);
            }
            case 3 -> {
                sc.nextLine();
                System.out.println("Enter name of Motorcycle you want to put in cart: ");
                String name = sc.nextLine();
                // Kiểm tra còn hàng hay không
                LargeMotorcyclesType motorcyclesType = motorcycles.searchByName(name);
                if (motorcyclesType==null){
                    System.out.println("Not Found\n");
                    menuOfCustomer();
                } else if (motorcyclesType.getMotorcyclesAmount()!=0){
                    selectMotorcycles(name);
                } else {
                    System.err.println("That Motorcycle is out of stock");
                    menuOfCustomer();
                }

            }
            case 4 -> displayCart();
            case 5 -> {
                sc.nextLine();
                System.out.println("Enter name of Motorcycle you want delete: ");
                String name = sc.nextLine();
                deleteMotorcycles(name);
            }
            case 6 -> showTotalOfCart();
            case 7 -> {
                File history = new File("CartOfCustomer.txt");
                if (history.length() == 0) {
                    System.out.println("Had not purchased before");
                } else {
                    readFormBinaryFile();
                }
            }
            case 8 -> back();
            case 9 -> exit();
            default -> menuOfCustomer();
        }
    }

    public void menu() {
        System.out.println("--------WELCOME TO THE LARGE MOTORCYCLES SHOP---------");
        System.out.println("|---{** ** ** ** ** ** ** ** ** ** ** ** ** ** **}---|");
        System.out.println("|----------------------------------------------------|");
        System.out.println("|  1. Display Large Motorcycles List                 |");
        System.out.println("|  2. Search Large Motorcycle                        |");
        System.out.println("|  3. Select Motorcycles to cart                     |");
        System.out.println("|  4. Display cart                                   |");
        System.out.println("|  5. Delete Motorcycles in cart                     |");
        System.out.println("|  6. Show the amount of the bill                    |");
        System.out.println("|  7. Check purchase history                         |");
        System.out.println("|  8. Back                                           |");
        System.out.println("|  9. Exit                                           |");
        System.out.println("|____________________________________________________/");
    }

    // 3. Select Motorcycles to cart
    public void selectMotorcycles(String name) {
        LargeMotorcyclesType motorcyclesType = motorcycles.searchByName(name);
        if (motorcyclesType == null) {
            System.out.println("Not Found\n");
            menuOfCustomer();
        } else if (!cart.isEmpty()) {
            int k = -1;
            for (int i = 0; i < cart.size(); i++) {
                LargeMotorcyclesType motor = cart.get(i);
                if (motorcyclesType.getMotorcyclesName().equals(motor.getMotorcyclesName())) {
                    k = 1;
                }
            }
            if (k == 1) {
                System.err.println(motorcyclesType.getMotorcyclesName() + " is already exits");
            } else {
                cart.add(motorcyclesType);
                System.out.println("[" + motorcyclesType.getMotorcyclesName() + "]" + " was add to your cart\n");
            }
        } else {
            cart.add(motorcyclesType);
            System.out.println("[" + motorcyclesType.getMotorcyclesName() + "]" + " was add to your cart\n");
        }
    }

    // 4. Display cart
    public void displayCart() {
        if (cart.isEmpty()) {
            System.err.println("You have nothing in cart!\n");
        } else {
            System.out.println("Your cart: ");
            System.out.println(cart + "\n");
        }
    }

    // 5. Delete Motorcycles in cart
    public void deleteMotorcycles(String name) {
        LargeMotorcyclesType motorcyclesType = motorcycles.searchByName(name);
        if (motorcyclesType == null) {
            System.out.println("Not Found");
        } else {
            cart.remove(motorcyclesType);
            System.out.println("[" + motorcyclesType.getMotorcyclesName() + "]" + " was just delete of your cart\n");
        }
    }

    // 6. Show total of cart
    public void showTotalOfCart() {
        double total = 0;
        for (int i = 0; i < cart.size(); i++) {
            double price = cart.get(i).getMotorcyclesPrice();
            total += price;
        }
        System.out.println("The amount of the bill is: " + total + " USD\n");
        if (total > 0) {
            System.out.println("1. Buy");
            System.out.println("2. Select more Motorcycles");
            int selection = sc.nextInt();
            switch (selection) {
                case 1 -> buy();
                case 2 -> menuOfCustomer();
            }
        }
    }

    public void buy() {
        motorcycles.writeToBinaryMotocyclesFile("CartOfCustomer.txt");
        for (int i = 0; i < cart.size(); i++) {
            int amount = cart.get(i).getMotorcyclesAmount();
            cart.get(i).setMotorcyclesAmount(amount - 1);
        }
        motorcycles.writeToBinaryMotocyclesFile("LargeMotorcycles.txt");
        cart.clear();
        System.out.println("Purchase Success");
    }

    // 7. Back
    public void back() {
        Main.areYouCustomerOrManage();
        ManageCustomers customers = new ManageCustomers();
        Login login = new Login();
        Main.runMain(customers, login, sc);
    }

    // 8. Exit
    public void exit() {
        System.exit(0);
    }

    // Read form binary file
    public void readFormBinaryFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("CartOfCustomer.txt"));
            cart = (List<LargeMotorcyclesType>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Product was purchased:");
        for (LargeMotorcyclesType cartList : cart) {
            System.out.println("Name - " + cartList.getMotorcyclesName() + ", price - " + cartList.getMotorcyclesPrice() + " USD");
        }
    }

}
