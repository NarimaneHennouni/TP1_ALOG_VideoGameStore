package pipeandfilter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import Store.*;

public class Snippet {

	static public Map<String, Client> CustomerMap = new HashMap<String, Client>();;
    static public Map<String, StockItem> StockItemMap = new HashMap<String, StockItem>();
    static public List<RentedItem> RentedItems=new ArrayList<RentedItem>();

	public static void init(){
		//clients
		Client c1=new Client(0,"Nour",15000);
		Client c2=new Client(1,"Narimane",20000);
		Client c3=new Client(2,"Lamia",30000);
		Snippet.CustomerMap.put(Integer.toString(c1.getCustomerID()), c1);
		Snippet.CustomerMap.put(Integer.toString(c2.getCustomerID()), c2);
		Snippet.CustomerMap.put(Integer.toString(c3.getCustomerID()), c3);

		//Stock Items
		Film s1=new Film(0, "Film:_Pirates_of_the_caribbean", 100,"Johnny_Depp");
		Jeux s2=new Jeux(1, "Jeu:_Need_for_Speed", 250,"Steam");
		Film s3=new Film(2, "Film:_Harry_Potter", 90,"Daniel");
		Snippet.StockItemMap.put(Integer.toString(s1.getItemID()), s1);
		Snippet.StockItemMap.put(Integer.toString(s2.getItemID()), s2);
		Snippet.StockItemMap.put(Integer.toString(s3.getItemID()), s3);

		//Rented Items
		String date="17-12-2020";
		Date d1;
		try {
			d1 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			RentedItem r1=new RentedItem(c1.getCustomerID(), s1.getItemID(), d1);
			RentedItem r2=new RentedItem(c2.getCustomerID(), s3.getItemID(), d1);
			Snippet.RentedItems.add(r1);
			Snippet.RentedItems.add(r2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		}
	public static Store_GUIFilter main(String args) {
		// TODO Auto-generated method stub
	//Data Initialization
		init();
	
	//Pipe and Filter creation
	Pipe p1 = new BlockingQueue();//PipeIN du Store_GUI avec le Transaction Processor
	Pipe p2 = new BlockingQueue();//PipeOUT du Store_GUI avec le Transaction Processor
	Pipe p3 = new BlockingQueue();//PipeIN du Store_GUI avec le Query Processor
	Pipe p4 = new BlockingQueue();//PipeOUT du Store_GUI avec le Query Processor

	Store_GUIFilter Store_GUIFilter= new Store_GUIFilter(p3, p4, p1, p2,args);
	Filter QPFilter	=new QPFilter(p4,p3);
	Filter TPFilter	=new TransactProcessorFilter(p2,p1);
	 
	Thread th1 = new Thread( Store_GUIFilter );
	Thread th2 = new Thread( QPFilter );
    Thread th3 = new Thread( TPFilter );
	
	th1.start();
	th2.start();  
	th3.start();

	return Store_GUIFilter;
	
	}
}