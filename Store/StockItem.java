package Store;

public class StockItem{
    /*attributs*/
    private int rentalPrice;
    private String title;
    private int itemID;
    public StockItem(int p,String t,int i){
        rentalPrice=p;
        title=t;
        itemID=i;
    }
    public StockItem(){}
    /* setters & getters*/
    public void setRentalPrice(int price) {
        this.rentalPrice=price;
    }
    public void setTitle(String title) {
        this.title=title;
    }
    public void setItemID(int id) {
        this.itemID=id;
    }
    public int getRentalPrice() {
       return this.rentalPrice;
    }
    public String getTitle() {
        return this.title;
    }
    public int getItemID() {
        return this.itemID;
    }
}
