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

    public ManageLargeMotorcycles() {

        motorcyclesList.add(new LargeMotorcyclesType(1, "Ducati Panigale v4s",
                40999.0, "Ducati Corse", "Red", "Panigale V4 And V4 S And V4 S Corse\n"));
        motorcyclesList.add(new LargeMotorcyclesType(2, "Yamaha YZF-R1",
                22999.0, "Yamaha", "Black", "Yamaha YZF-R1 / R1M Pricing\n"));
        motorcyclesList.add(new LargeMotorcyclesType(3, "Triumph Bonneville T120",
                10995.0, "Triumph", "Black", "THE ULTIMATE MODERN CLASSIC\n"));
        motorcyclesList.add(new LargeMotorcyclesType(4, "Honda CBR1000RR",
                28599.0, "Honda", "White", "long-awaited redesign of Honda's open-class superbike\n"));
        motorcyclesList.add(new LargeMotorcyclesType(5, "Kawasaki Ninja ZX-10R",
                16699.0, "Kawasaki", "Blue", "KRT Edition, no ABS\n"));
        motorcyclesList.add(new LargeMotorcyclesType(6, "BMW S1000RR",
                16999.0, "BMW", "Blue-White", "BMW Motorrad has finally revealed\n"));
    }

    Scanner sc = new Scanner(System.in);
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
            case 6 -> writeToBinaryMotocyclesFile();
            case 7 -> readFormBinaryMotocyclesFile();
            case 8 -> exit();
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
        System.out.println("|  8. Exit                                                 |");
        System.out.println("|__________________________________________________________/");
    }

    // 1. Add Large Motorcycles
    public void addMotorcycles() {
        LargeMotorcyclesType motorcycles = inputInfoMotorcycles();
        motorcyclesList.add(motorcycles);
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
        LargeMotorcyclesType motorcyclesType = new LargeMotorcyclesType(motorcyclesID, motorcyclesName,
                motorcyclesPrice, motorcyclesProducer, color, description);
        return motorcyclesType;
    }

    // 2. Display Large Motorcycles List
    public void display() {
        for (LargeMotorcyclesType motorcycleList :
                motorcyclesList) {
            System.out.println(motorcycleList);
        }
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
    public void writeToBinaryMotocyclesFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("LargeMotorcycles.txt"));
            oos.writeObject(motorcyclesList);
            System.out.println("LargeMotorcycles.txt was just update!\n");
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 7. Read form binary file
    public void readFormBinaryMotocyclesFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("LargeMotorcycles.txt"));
            motorcyclesList = (List<LargeMotorcyclesType>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (LargeMotorcyclesType largeMotorcycles : motorcyclesList) {
            System.out.println(largeMotorcycles);
        }
    }

    // 7. Exit
    public void exit() {
        System.exit(0);
    }
}
