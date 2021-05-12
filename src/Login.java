import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {
    ManageLargeMotorcycles motorcycles = new ManageLargeMotorcycles();
    Discount customers = new Discount();


    Scanner sc = new Scanner(System.in);

    public void login() {
        System.out.println(" ---------------------------------");
        System.out.println("/   ___________________________   |");
        System.out.println("|  | User Name:                |  |");
        System.out.println("|  | Password :                |  |");
        System.out.println("|  |___________________________|  |");
        System.out.println("|_________________________________/");
    }

    // Khách hàng đăng nhập
    public void customerLogin() {
        System.out.println("- Account should contain at least 6 characters.\n" +
                " For example: \"josh123\" ");
        System.out.println("- Password must contain an uppercase letter and at least 6 characters.\n" +
                " For example: \"Bickdig\" \n");
        while (true) {
            System.out.println("Enter the Use Name: ");
            String useName = sc.nextLine();
            boolean isTrue = false;
            boolean useName1 = useName(useName);
            if (useName1) {
                isTrue = true;
            }
            if (isTrue) {
                break;
            } else {
                System.err.println("This account may already be in use or wrong type, please enter another account!");
            }
        }

        while (true) {
            System.out.println("Enter the password:");
            String pass = sc.nextLine();
            boolean isTrue = false;
            boolean password1 = password(pass);
            if (password1) {
                isTrue = true;
            }
            if (isTrue) {
                break;
            } else {
                System.err.println("Wrong password, Pleas enter another Password!");
            }
        }
        // Đưa vào menu OrderSpecial
        do {
            customers.menuOfCustomer();
        } while (true);
    }

    // Quản lý đăng nhập
    public void manageLogin() {
        while (true) {
            System.out.println("Enter the Use Name: ");
            String useName = sc.nextLine();
            String adminAccount = "Admin";
            boolean isTrue = false;
            if (useName.equals(adminAccount)) {
                isTrue = true;
            }
            if (isTrue) {
                break;
            } else {
                System.err.println("Enter the wrong account!");
            }
        }

        while (true) {
            boolean isTrue = false;
            System.out.println("Enter the Password: ");
            String password = sc.nextLine();
            String adminPassword = "admin";
            if (password.equals(adminPassword)) {
                isTrue = true;
            }
            if (isTrue) break;
            else {
                System.err.println("Enter the wrong account!");
            }
        }
        // Đưa vào menu quản lý
        do {
            motorcycles.menuOfManagement();
        } while (true);
    }

    public boolean useName(String account) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{6,}$");
        Matcher matcher = pattern.matcher(account);
        return matcher.matches();
    }

    public boolean password(String pass) {
        Pattern pattern = Pattern.compile("^[A-Z][a-zA-Z0-9]{5,}$");
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }
}
