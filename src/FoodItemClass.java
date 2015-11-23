public class FoodItemClass {
    private String name;
    private double price;
    private int quantity;
    private String desc;
    private String size;
    private boolean specialOrder;

    public FoodItemClass() {
        name = "";
        price = 0.00;
        quantity = -1;
        desc = "N/A";
        size = "N/A";
        specialOrder = false;
    }

    public FoodItemClass(String name, double price, int quantity,
                         String desc, String size, boolean specialOrder) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.desc = desc;
        this.size = size;
        this.specialOrder = specialOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isSpecialOrder() {
        return specialOrder;
    }

    public void setSpecialOrder(boolean specialOrder) {
        this.specialOrder = specialOrder;
    }

    @Override
    public String toString() {
        return "FoodItemClass{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", desc='" + desc + '\'' +
                ", size='" + size + '\'' +
                ", specialOrder=" + specialOrder +
                '}';
    }
}