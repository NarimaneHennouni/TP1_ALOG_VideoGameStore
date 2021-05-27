package QueryProcessor;

import java.util.*;
import Store.*;
import pipeandfilter.Snippet;


public class QueryProcessor{

    public boolean FindByTitle(String title){
        boolean found=false;
        for( StockItem item:  Snippet.StockItemMap.values()){
            if(title.equals(item.getTitle())){
                found=true;
                break;
            }
        }
        return found;
    }

    public String AvailableForRent(String title){
        boolean found=true;
        if (FindByTitle(title)){// s'il existe et dispo à la location
        int itemid=-1;
        for (StockItem item:Snippet.StockItemMap.values()){
            if (item.getTitle().equals(title)){
             itemid=item.getItemID();
                break;
            }
        }
            for (RentedItem item: Snippet.RentedItems){
                if(item.getItemID()==itemid){
                    found=false;
                    break;
                }
            }
        }else{found=false;}
        String result=Boolean.toString(found);
        return result;
    }

    
    public String NdByActor(String actor){
        String result="";
        for( StockItem item:  Snippet.StockItemMap.values()){
            if("class Store.Film".equals(item.getClass().toString())){
                if(actor.equals(((Film) item).getActor()))
                {
                    result+=item.getTitle()+"\n";
                }
            }
        }
        return result;
    }

    public String CustomerFilms(String clientid){
        int id=Integer.parseInt(clientid);
        String result="";
        for (RentedItem item:  Snippet.RentedItems){
            //determine if it is a Film or not
            StockItem sitem= Snippet.StockItemMap.get(Integer.toString(item.getItemID()));
            if("class Store.Film".equals(sitem.getClass().toString())){
            if (item.getCustomerID()==id){
                result+="Date d'échéance: "+item.getDueDate()+ " | " + " Films loués: "+sitem.getTitle()+"\n";
            }}
        }
        return result;
    }

    public boolean IsCheckedOut(RentedItem article){
        boolean found=false;
        for( RentedItem item:  Snippet.RentedItems){
            if(item.getItemID()==article.getItemID()){
                found=true;
                break;
            }
        }
        return found;
    }

    public String Solde(String clientid){
        Client client= Snippet.CustomerMap.get(clientid);
        return Integer.toString(client.getAccountBalance());
    }

    public String OverdueItems(){
        Date date = new Date();
        String result="";
        for( RentedItem item:  Snippet.RentedItems){
            if(item.getDueDate().before(date)){
                //Overdue.add(item);
                result+="Item "+item.getItemID()+" | Date d'echéance: "+item.getDueDate()+"\n";
            }
        }
        return result;
    }
}