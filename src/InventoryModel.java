public class InventoryModel {
    private int id;
    private String itemName;
    private int quantity;
    private int rate;
    private String expiry_date;
    private String isSalable;

    public InventoryModel(int id, String itemName, int quantity, int rate, String expiry_date, String isSalable) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.rate = rate;
        this.expiry_date = expiry_date;
        this.isSalable = isSalable;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getRate() {
        return rate;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public String getIsSalable() {
        return isSalable;
    }

}
