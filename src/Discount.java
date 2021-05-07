public class Discount extends ManageCustomers{
    public Discount() {
    }

    @Override
    public void showTotalOfCart() {
        double total = 0;
        for (int i = 0; i < cart.size(); i++) {
            double price = cart.get(i).getMotorcyclesPrice();
            total += price;
        }
        double discount = (total * 3) / 100;
        double totalPrice = total - discount;
        System.out.println("The amount of the bill is: " + totalPrice + " USD\n");
    }
}
