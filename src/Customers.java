public class Customers extends User{
    private String manageAddress;
    private String managePhone;
    private String date;

    public Customers(){}

    public Customers(String manageAddress, String managePhone, String date) {
        this.manageAddress = manageAddress;
        this.managePhone = managePhone;
        this.date = date;
    }

    public Customers(int userID, int userName, String manageAddress, String managePhone, String date) {
        super(userID, userName);
        this.manageAddress = manageAddress;
        this.managePhone = managePhone;
        this.date = date;
    }

    public String getManageAddress() {
        return manageAddress;
    }

    public void setManageAddress(String manageAddress) {
        this.manageAddress = manageAddress;
    }

    public String getManagePhone() {
        return managePhone;
    }

    public void setManagePhone(String managePhone) {
        this.managePhone = managePhone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "manageAddress='" + manageAddress + '\'' +
                ", managePhone='" + managePhone + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
