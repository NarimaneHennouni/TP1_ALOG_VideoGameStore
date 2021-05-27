package Store;

import java.util.*;

public class RentedItem{
    private int customerID;
    private int itemID;
    private Date dueDate;

    public RentedItem (int customerID, int itemID, Date dueDate){
        this.customerID=customerID;
        this.itemID=itemID;
        this.dueDate=dueDate;
    }
    public RentedItem(){}
    /*setters and getters */
    public void setCustomerID(int id){
        customerID=id;
    }
    public void setItemID(int id){
         itemID=id;
    }
    public void setDueDate(Date date){
        dueDate=date;
    }
    public int getCustomerID(){
        return customerID;
    }
    public int getItemID(){
        return itemID;
    }
    public Date getDueDate(){
        return dueDate;
    }
}
