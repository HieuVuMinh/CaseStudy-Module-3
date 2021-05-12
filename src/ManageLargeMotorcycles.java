import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageLargeMotorcycles {

    public static final String notFound = "Not found";
    public List<LargeMotorcyclesType> motorcyclesList = new ArrayList<>();
    private int motorcyclesID;
    private String motorcyclesName;
    private double motorcyclesPrice;
    private String motorcyclesProducer;
    private String color;
    private String description;
    private int motorcyclesAmount;

    public ManageLargeMotorcycles() {

        motorcyclesList.add(new LargeMotorcyclesType(1, "Ducati Panigale v4s",
                40999.0, "Ducati Corse", "Red", 2, "Panigale V4 And V4 S And V4 S Corse\n"));
        motorcyclesList.add(new LargeMotorcyclesType(2, "Yamaha YZF-R1",
                22999.0, "Yamaha", "Black", 2, "Yamaha YZF-R1 / R1M Pricing\n"));
        motorcyclesList.add(new LargeMotorcyclesType(3, "Triumph Bonneville T120",
                10995.0, "Triumph", "Black", 2, "THE ULTIMATE MODERN CLASSIC\n"));
        motorcyclesList.add(new LargeMotorcyclesType(4, "Honda CBR1000RR",
                28599.0, "Honda", "White", 2, "long-awaited redesign of Honda's open-class superbike\n"));
        motorcyclesList.add(new LargeMotorcyclesType(5, "Kawasaki Ninja ZX-10R",
                16699.0, "Kawasaki", "Blue", 2, "KRT Edition, no ABS\n"));
        motorcyclesList.add(new LargeMotorcyclesType(6, "BMW S1000RR",
                16999.0, "BMW", "Blue-White", 2, "BMW Motorrad has finally revealed\n"));
    }

    Scanner sc = new Scanner(System.in);
//    ManageCustomers customers = new ManageCustomers();
//    Login login = new Login();
    // Menu Management
    public void menuOfManagement() {
        menu();
        int selection = sc.nextInt();
        switch (selection) {
            case 1 -> addMotorcycles();
            case 2 -> display();
            case 3 -> {
                sc.nextLine();
                System.out.println("Enter name of Motorcycle you want to find: ");
                String name = sc.nextLine();
                search(name);
            }
            case 4 -> {
                sc.nextLine();
                System.out.println("Enter name of Motorcycle you want to edit: ");
                String name = sc.nextLine();
                editInfo(name);
            }
            case 5 -> {
                sc.nextLine();
                System.out.println("Enter name of Motorcycle you want to delete: ");
                String name = sc.nextLine();
                deleteInfo(name);
            }
            case 6 -> writeToBinaryMotocyclesFile("LargeMotorcycles.txt");
            case 7 -> readFormBinaryMotocyclesFile("LargeMotorcycles.txt");
            case 8 -> back();
            case 9 -> exit();
            default -> menuOfManagement();
        }
    }
    private void menu() {
        System.out.println("------------------------------------------------------------");
        System.out.println("|       **    WELCOME TO THE MANAGEMENT SYSTEM    **       |");
        System.out.println("|----------------------------------------------------------|");
        System.out.println("|  1. Add Large Motorcycles                                |");
        System.out.println("|  2. Display Large Motorcycles List                       |");
        System.out.println("|  3. Search Large Motorcycle                              |");
        System.out.println("|  4. Edit Information of Motorcycle                       |");
        System.out.println("|  5. Delete Information of Motorcycle                     |");
        System.out.println("|  6. Write to binary file                                 |");
        System.out.println("|  7. Read form binary file                                |");
        System.out.println("|  8. Back                                                 |");
        System.out.println("|  9. Exit                                                 |");
        System.out.println("|__________________________________________________________/");
    }

    // 1. Add Large Motorcycles
    public void addMotorcycles() {
        // Kiểm tra nếu hàng nhập vào đã có trong kho thì chỉ cần nhập số lượng sản phẩm
        System.out.println("CHECK NAME OF MOTORCYCLES");
        System.out.println("Name of motorcycles: ");
        sc.nextLine();
        String name = sc.nextLine();
        LargeMotorcyclesType motorcyclesType = searchByName(name);
        if (motorcyclesType==null){
            LargeMotorcyclesType motorcycles = inputInfoMotorcycles();
            motorcyclesList.add(motorcycles);
        } else {
            System.out.println("Enter motorcycles amount: ");
            motorcyclesAmount = sc.nextInt();
            int amount = motorcyclesType.getMotorcyclesAmount();
            motorcyclesType.setMotorcyclesAmount(motorcyclesAmount + amount);
            // Ghi lại dữ liệu vào file
            writeToBinaryMotocyclesFile("LargeMotorcycles.txt");

        }
    }

    // Input Information
    private LargeMotorcyclesType inputInfoMotorcycles() {
        System.out.println("Enter Motorcycle ID: ");
        while (true) {
            motorcyclesID = sc.nextInt();
            boolean isFind = false;
            for (LargeMotorcyclesType motorcycles : motorcyclesList) {
                if (motorcycles.getMotorcyclesID() == motorcyclesID) {
                    isFind = true;
                }
            }
            if (!isFind)
                break;
            else{
                System.err.println("The ID was in existence, enter another ID: ");
            }
        }
        sc.nextLine();
        System.out.println("Enter Motorcycle Name: ");
        motorcyclesName = sc.nextLine();
        System.out.println("Enter Motorcycle Price: ");
        motorcyclesPrice = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter Motorcycle Producer: ");
        motorcyclesProducer = sc.nextLine();
        System.out.println("Enter Motorcycle Color: ");
        color = sc.nextLine();
        System.out.println("Enter Motorcycle Description: ");
        description = sc.nextLine();
        System.out.println("Enter Motorcycle Amount: ");
        motorcyclesAmount = sc.nextInt();
        LargeMotorcyclesType motorcyclesType = new LargeMotorcyclesType(motorcyclesID, motorcyclesName,
                motorcyclesPrice, motorcyclesProducer, color, motorcyclesAmount, description);
        return motorcyclesType;
    }

    // 2. Display Large Motorcycles List
    public void display() {
        readFormBinaryMotocyclesFile("LargeMotorcycles.txt");
//        for (LargeMotorcyclesType motorcycleList :
//                motorcyclesList) {
//            System.out.println(motorcycleList);
//        }
    }

    // 3. Search Large Motorcycle
    public void search(String name) {
        LargeMotorcyclesType motorcyclesType = searchByName(name);
        if (motorcyclesType == null) {
            System.out.println(notFound);
        } else {
            System.out.println("Is this the Motorcycle you are looking for!\n" + motorcyclesType);
        }
    }

    public LargeMotorcyclesType searchByName(String name) {
        int count = -1;
        for (int i = 0; i < motorcyclesList.size(); i++) {
            LargeMotorcyclesType motorcyclesType = motorcyclesList.get(i);
            if (motorcyclesType.getMotorcyclesName().equals(name)) {
                count = i;
                break;
            }
        }
        if (count == -1) {
            return null;
        } else {
            return motorcyclesList.get(count);
        }
    }

    // 4. Edit Information of Motorcycle
    public void editInfo(String name) {
        LargeMotorcyclesType motorcyclesType = searchByName(name);
        if (motorcyclesType == null) {
            System.out.println(notFound);
        } else {
            inputInfoMotorcycles();
            motorcyclesType.setMotorcyclesID(motorcyclesID);
            motorcyclesType.setMotorcyclesName(motorcyclesName);
            motorcyclesType.setMotorcyclesPrice(motorcyclesPrice);
            motorcyclesType.setMotorcyclesProducer(motorcyclesProducer);
            motorcyclesType.setColor(color);
            motorcyclesType.setDescription(description);
        }
    }

    // 5. Delete Information of Motorcycle
    public void deleteInfo(String name) {
        LargeMotorcyclesType motorcyclesType = searchByName(name);
        if (motorcyclesType == null) {
            System.out.println(notFound);
        } else {
            motorcyclesList.remove(motorcyclesType);
        }
    }

    // 6. Write to binary file
    public void writeToBinaryMotocyclesFile(String nameFile) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nameFile));
            oos.writeObject(motorcyclesList);
            System.out.println("LargeMotorcycles.txt was just update!\n");
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 7. Read form binary file
    public void readFormBinaryMotocyclesFile(String nameFile) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nameFile));
            motorcyclesList = (List<LargeMotorcyclesType>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (LargeMotorcyclesType largeMotorcycles : motorcyclesList) {
            System.out.println(largeMotorcycles);
        }
    }
//    // 8. Back
    public void back(){
        Main.areYouCustomerOrManage();
        ManageCustomers customers = new ManageCustomers();
        Login login = new Login();
        Main.runMain(customers, login, sc);
    }
    // 9. Exit
    public void exit() {
        System.exit(0);
    }
}
