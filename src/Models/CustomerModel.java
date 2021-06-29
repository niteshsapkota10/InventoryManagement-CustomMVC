package Models;

public class CustomerModel {
    private int id;
    private String name;
    private String panno;

    public CustomerModel(int id, String name, String panno) {
        this.id = id;
        this.name = name;
        this.panno = panno;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPanno() {
        return panno;
    }
}
