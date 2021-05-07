import java.io.Serializable;

public class LargeMotorcycles implements Serializable {
    private int motorcyclesID;
    private String motorcyclesName;
    private double motorcyclesPrice;

    public LargeMotorcycles() {
    }

    public LargeMotorcycles(int motorcyclesID, String motorcyclesName, double motorcyclesPrice) {
        this.motorcyclesID = motorcyclesID;
        this.motorcyclesName = motorcyclesName;
        this.motorcyclesPrice = motorcyclesPrice;
    }

    public int getMotorcyclesID() {
        return motorcyclesID;
    }

    public void setMotorcyclesID(int motorcyclesID) {
        this.motorcyclesID = motorcyclesID;
    }

    public String getMotorcyclesName() {
        return motorcyclesName;
    }

    public void setMotorcyclesName(String motorcyclesName) {
        this.motorcyclesName = motorcyclesName;
    }

    public double getMotorcyclesPrice() {
        return motorcyclesPrice;
    }

    public void setMotorcyclesPrice(double motorcyclesPrice) {
        this.motorcyclesPrice = motorcyclesPrice;
    }

    @Override
    public String toString() {
        return "LargeMotorcycles{" +
                "motorcyclesID=" + motorcyclesID +
                ", motorcyclesName='" + motorcyclesName + '\'' +
                ", motorcyclesPrice=" + motorcyclesPrice +
                '}';
    }
}
