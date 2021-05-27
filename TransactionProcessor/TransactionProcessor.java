package TransactionProcessor;

import java.util.*;
import Store.*;
import pipeandfilter.Snippet;

public class TransactionProcessor{
     public static int itemID=Snippet.StockItemMap.values().size();
     public static int customerID=Snippet.CustomerMap.values().size();

    public void CheckOut(StockItem item, Client client, Date date ){
        RentedItem rentedItem = new RentedItem(client.getCustomerID(),item.getItemID(),date);
        Snippet.RentedItems.add(rentedItem);
    }
    public  void CheckIn(String iditem){
        Iterator<RentedItem> it=Snippet.RentedItems.iterator();
        while (it.hasNext()){
            RentedItem item=it.next();
            if (iditem.equals(Integer.toString(item.getItemID()))){
                Snippet.RentedItems.remove(item);
            }
        }
    }
    public  void AddCustomer(Client client){
        Snippet.CustomerMap.put(client.getName(),client);
        TransactionProcessor.customerID++;
    }
    public  void AddStockItem(StockItem item){
        Snippet.StockItemMap.put(item.getTitle(),item);
        TransactionProcessor.itemID++;
    }
    public  void AddFine(int fine,int clientid){
        Client client=Snippet.CustomerMap.get(Integer.toString(clientid));
        client.setAccountBalance(client.getAccountBalance()-fine);
    }
}