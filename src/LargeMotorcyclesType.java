public class LargeMotorcyclesType extends LargeMotorcycles{
    private String motorcyclesProducer;
    private String color;
    private String description;

    public LargeMotorcyclesType(){}

    public LargeMotorcyclesType(String motorcyclesProducer, String color, String description) {
        this.motorcyclesProducer = motorcyclesProducer;
        this.color = color;
        this.description = description;
    }

    public LargeMotorcyclesType(int motorcyclesID, String motorcyclesName, double motorcyclesPrice, String motorcyclesProducer, String color, int motorcyclesAmount, String description) {
        super(motorcyclesID, motorcyclesName, motorcyclesPrice, motorcyclesAmount);
        this.motorcyclesProducer = motorcyclesProducer;
        this.color = color;
        this.description = description;
    }


    public String getMotorcyclesProducer() {
        return motorcyclesProducer;
    }

    public void setMotorcyclesProducer(String motorcyclesProducer) {
        this.motorcyclesProducer = motorcyclesProducer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Motorcycles ID: " + getMotorcyclesID() + ", Motorcycles Name: " + getMotorcyclesName() +
                ", Motorcycles Price: " + getMotorcyclesPrice() + " USD" + ", Motorcycles Producer: " + motorcyclesProducer +
                ", Color: " + color + ", Description: " + description + ", Motorcycles Amount: " + getMotorcyclesAmount() + "\n";
    }
}
