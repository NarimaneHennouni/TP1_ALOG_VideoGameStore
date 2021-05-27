package pipeandfilter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import Store.Client;
import Store.RentedItem;
import Store.StockItem;
import TransactionProcessor.TransactionProcessor;

public  class TransactProcessorFilter extends Filter {
	Pipe _GUIdataINPipe;
	Pipe _GUIdataOUTPipe;
	//Pipe _QPdataOUTPipe; not needed cuz QueryProcessor  doesn't perform modifications
 
    public TransactProcessorFilter(Pipe _GUIpipein,Pipe _GUIpipeout){
		super();
		this._GUIdataINPipe=_GUIpipein;
		this._GUIdataOUTPipe=_GUIpipeout;
	}

    public String getDataGUI(){
        return _GUIdataINPipe.dataOUT();
    }
     
    public void sendDataGUI( String tempData){
        _GUIdataOUTPipe.dataIN(tempData);
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			execute();
		}
	}

	@Override
	synchronized void execute() {
		String request= (String) this.getDataGUI();
		String[] params=request.split(",");
		int req_num=Integer.parseInt(params[0]);
		TransactionProcessor transactprocessor=new TransactionProcessor();
		switch(req_num){
			case 6://Louer un article
			params=request.split(",");
			int custmID=Integer.parseInt(params[1]);
			int itemID=Integer.parseInt(params[2]);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				Date duedate=new Date();
				try {
					duedate = formatter.parse(params[3]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//créer le StockItem
			StockItem stockitem=new StockItem();
			stockitem.setItemID(itemID);
			Client client=new Client();
			client.setCustomerID(custmID);
			transactprocessor.CheckOut(stockitem, client, duedate);
			sendDataGUI("");
			break;

			case 7://Remettre un article
			params=request.split(",");
			String itemID2=params[1];
			transactprocessor.CheckIn(itemID2);
			sendDataGUI("");
			break;

			case 8://Ajouter une amende
			params=request.split(",");
			int custmID3=Integer.parseInt(params[1]);
			int montant=Integer.parseInt(params[2]);
			transactprocessor.AddFine(montant, custmID3);
			sendDataGUI("");
			break;

			case 9://Ajouter un article
			params=request.split(",");
			String title=params[1];
			int price=Integer.parseInt(params[2]);
			StockItem stckitm=new StockItem(price,title,TransactionProcessor.itemID);
			transactprocessor.AddStockItem(stckitm);
			sendDataGUI("");
			break;

			case 10://Ajouter un client
			params=request.split(",");
			String name=params[1];
			int solde=Integer.parseInt(params[2]);
			Client client2=new Client(TransactionProcessor.customerID,name,solde);
			transactprocessor.AddCustomer(client2);
			sendDataGUI("");
			break;
		}
	}
}
 