package pipeandfilter;

import java.util.*;

import Store.Client;
import Store.Film;
import Store.RentedItem;
import Store.StockItem;

public  class Store_GUIFilter extends Filter {
 
    Pipe _QPdataOUTPipe;//va vers QP
	Pipe _TPdataOUTPipe;//va vers TP
	Pipe _QPdataINPipe;//vient du QP
	Pipe _TPdataINPipe;//vient du TP
	String req;
	

    public Store_GUIFilter(Pipe  _QPpipein,Pipe  _QPpipeout, Pipe  _TPpipein,  Pipe _TPpipeout, String req) {
		super();
		this._QPdataINPipe=_QPpipein;
		this._QPdataOUTPipe = _QPpipeout;
		this._TPdataINPipe=_TPpipein;
		this._TPdataOUTPipe = _TPpipeout;
		this.req=req;
	}
    
    public void sendData(String req){
		String[] params=req.split(",");
		int req_num=Integer.parseInt(params[0]);
		if (req_num>=1 && req_num<=5){ //requête destinée au QueryProcessor
			_QPdataOUTPipe.dataIN(req);
		}else{ //requête destinée au TransactProcessor
			_TPdataOUTPipe.dataIN(req);
		}
    }
	public String getData(String req){
		String[] params=req.split(",");
		int req_num=Integer.parseInt(params[0]);
		if (req_num>=1 && req_num<=5){ //requête destinée au QueryProcessor
			return _QPdataINPipe.dataOUT();
		}else{ //requête destinée au TransactProcessor
			return _TPdataINPipe.dataOUT();
		}
    }

    @Override
	public void run() {
		// TODO Auto-generated method stub
		execute();
	}
	@Override
	synchronized void execute() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int choice=-1;
		// while (choice!=0){
		// 	//dans cette partie, on récupère les paramètres des requêtes pour envoyer le tout aux processors (num_req,params)
			
		// System.out.println("\nVeuillez choisir la requête à executer: \n*************************************************");
		// System.out.println("1-Quels sont tous les films dans lesquels a joué un acteur donné ? \n2- Y a-t-il un jeu ou un film avec un titre donné qui est disponible à la location? \n3- Quels sont tous les films qu'un client particulier a loués, avec leurs dates d’échéance ?\n4- Quel est le solde du compte d'un client?\n5- Quels sont tous les articles en retard ?\n6- Louer un article par un client \n7- Remettre un article par un client  \n8- Ajouter des amendes à son solde (pénalités de retard)\n9- Ajouter un article au stock et mise à jour du catalogue\n10- Ajouter un client\n0-QUITTER\n*************************************************");
		// choice=sc.nextInt();
		// String req="";

		// 	switch(choice){
		// 		case 0:
		// 		System.exit(0);				
		// 		case 1:
		// 		System.out.println("Veuillez introduire le nom de l'acteur:\n");
		// 		String name=sc.next();		
		// 		req=Integer.toString(choice)+','+name; 
		// 		break;

		// 		case 2:
		// 		System.out.println("Veuillez introduire le titre de l'article: \n");
		// 		String title=sc.next();
		// 		req=Integer.toString(choice)+','+title;
		// 		break;

		// 		case 3:
		// 		System.out.println("Veuillez introduire l'identifiant du client: \n");
		// 		String id=sc.next();
		// 		req=Integer.toString(choice)+','+id;
		// 		break;

		// 		case 4:
		// 		System.out.println("Veuillez introduire l'identifiant du client: \n");
		// 		String idc=sc.next();
		// 		req=Integer.toString(choice)+','+idc;
		// 		break;

		// 		case 5:
		// 		req=Integer.toString(choice)+',';
		// 		break;

		// 		case 6:
		// 		System.out.println("Veuillez introduire l'identifiant du client,l'identifiant de l'article,la date de location: \n");
		// 		String params=sc.next();
		// 		req=Integer.toString(choice)+','+params;
		// 		break;

		// 		case 7:
		// 		System.out.println("Veuillez introduire l'identifiant de l'article à remettre: \n");
		// 		String params2=sc.next();
		// 		req=Integer.toString(choice)+','+params2;				
		// 		break;

		// 		case 8:
		// 		System.out.println("Veuillez introduire l'identifiant du client,le montant de l'amende: \n");
		// 		String params3=sc.next();
		// 		req=Integer.toString(choice)+','+params3;
		// 		break;

		// 		case 9:
		// 		System.out.println("Veuillez introduire le titre de l'article,son prix de location: \n");
		// 		//on récupère juste ces 2 params, l'identifiant sera affecté automatiquement à travers un attribut static de la classe Transaction Processor
		// 		String params4=sc.next();
		// 		req=Integer.toString(choice)+','+params4;
		// 		break;

		// 		case 10:
		// 		System.out.println("Veuillez introduire le nom du client,son solde: \n");
		// 		String params5=sc.next();
		// 		req=Integer.toString(choice)+','+params5;
		// 		break;

		// 	}

			this.sendData(req);
			notifyAll();
			
			String[] params=req.split(",");
			int req_num=Integer.parseInt(params[0]);
			String result;
			if (req_num>=1 && req_num<=5){ //resultat du QueryProcessor

				result=this.getData(req);
				switch(req_num){
					case 1:
					System.out.println(result);
					break;

					case 2:
					if(Boolean.parseBoolean(result)){
						System.out.println("Cet article est disponible à la location\n");
					}else {System.out.println("Cet article n'est pas disponible à la location\n");}
					break;

					case 3:
					System.out.println(result);
					break;

					case 4:
					System.out.println("Le solde du client est: "+result);
					break;

					case 5:
					System.out.println("Les articles en retard sont: \n"+result);
					break;
				}

			}else{ //resultat du TransactProcessor
				result= (String) getData(req);//juste pour que le thread se débloque, sinon si on synchronise pas, le programme affiche un résultat qui n'est pas mis à jour (les listes des clients, articles et articles loués)
				switch (req_num){
					case 6:
					System.out.println("\nListe des articles loués:\n");
					for (RentedItem item: Snippet.RentedItems){
						System.out.println("Item "+item.getItemID()+" | par le client :"+item.getCustomerID()+" | le "+item.getDueDate()+"\n");
					}
					break;

					case 7:
					System.out.println("\nListe des articles loués mise à jour:\n");
					for (RentedItem item: Snippet.RentedItems){
						System.out.println("Item "+item.getItemID()+" | par le client :"+item.getCustomerID()+" | le "+item.getDueDate()+"\n");
					}
					break;

					case 8:
					System.out.println("Solde du client mise à jour: "+Snippet.CustomerMap.get(params[1]).getAccountBalance()+"\n");
					break;

					case 9:
					System.out.println("\nListe des articles en stock mise à jour:\n");
					Set entrySet = Snippet.StockItemMap.entrySet();

					// Obtaining an iterator for the entry set
					Iterator it = entrySet.iterator();
				
					// Iterate through HashMap entries(Key-Value pairs)
					while(it.hasNext()){
					Map.Entry me = (Map.Entry)it.next();
					System.out.println("Titre :"+((StockItem)me.getValue()).getTitle()+" | Prix de location "+((StockItem)me.getValue()).getRentalPrice()+"\n");
					}
					break;

					case 10:
					System.out.println("\nListe des clients mise à jour:\n");
					Set entrySet2 = Snippet.CustomerMap.entrySet();

					// Obtaining an iterator for the entry set
					Iterator it2 = entrySet2.iterator();
				
					// Iterate through HashMap entries(Key-Value pairs)
					while(it2.hasNext()){
					Map.Entry me2 = (Map.Entry)it2.next();
						System.out.println("Nom: "+((Client)me2.getValue()).getName()+" | Solde: "+((Client)me2.getValue()).getAccountBalance());
					}
					break;
				}

			}

			
			
			req="";
			
			
		}
		//sc.close();


	//}
}
 