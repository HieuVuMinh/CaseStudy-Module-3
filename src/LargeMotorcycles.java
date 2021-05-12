import java.io.Serializable;

public class LargeMotorcycles implements Serializable {
    private int motorcyclesID;
    private String motorcyclesName;
    private double motorcyclesPrice;
    private int motorcyclesAmount;

    public LargeMotorcycles() {
    }

    public LargeMotorcycles(int motorcyclesID, String motorcyclesName, double motorcyclesPrice, int motorcyclesAmount) {
        this.motorcyclesID = motorcyclesID;
        this.motorcyclesName = motorcyclesName;
        this.motorcyclesPrice = motorcyclesPrice;
        this.motorcyclesAmount = motorcyclesAmount;
    }

    public int getMotorcyclesAmount() {
        return motorcyclesAmount;
    }

    public void setMotorcyclesAmount(int motorcyclesAmount) {
        this.motorcyclesAmount = motorcyclesAmount;
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
