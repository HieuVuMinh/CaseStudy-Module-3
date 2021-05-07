public class Manage extends User{
    private String manageAddress;
    private String managePhone;

    public Manage(){}

    public Manage(String manageAddress, String managePhone) {
        this.manageAddress = manageAddress;
        this.managePhone = managePhone;
    }

    public Manage(int userID, int userName, String manageAddress, String managePhone) {
        super(userID, userName);
        this.manageAddress = manageAddress;
        this.managePhone = managePhone;
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

    @Override
    public String toString() {
        return "Manage{" +
                "manageAddress='" + manageAddress + '\'' +
                ", managePhone='" + managePhone + '\'' +
                '}';
    }
}
