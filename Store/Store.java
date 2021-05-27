package Store;
import java.util.*;


public class Store {
    private HashMap<String,Client> customers;
    private HashMap<String,StockItem> stockItems;
    private ArrayList<RentedItem> rentedItems;
    static int nbcustm;
    static int stockitm;
    static RentedItem renteditm;

    public Store (){
        customers =new HashMap<String,Client> ();
        stockItems =new HashMap <String,StockItem> ();
        rentedItems = new ArrayList<RentedItem>();
        
    }

    public static void main(String[] args){
        System.out.println("Choisir une des options suivantes :\n");
        System.out.println("1-Ajouter un article\n2- Ajouter un client\n3-Louer un article à un client\n4-Remettre un article par un client\n0-Quitter");
        Scanner scanner =new Scanner(System.in);
        int choice=scanner.nextInt();
        while (choice!=0){
            switch(choice){
                case 1:
                System.out.println("Veuillez introduire l'");
                break;
                case 2:
                System.out.println("Veuillez introduire l'");
                break;
                case 3:
                System.out.println("Veuillez introduire l'");
                break;
                case 4:
                System.out.println("Veuillez introduire l'");
                break;
            }
            scanner.close();
        } 
    }
}
