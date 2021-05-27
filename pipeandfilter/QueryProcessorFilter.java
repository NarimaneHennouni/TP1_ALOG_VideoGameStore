package pipeandfilter;
import QueryProcessor.*;

class QPFilter extends Filter {

	Pipe _dataINGuiPipe;
    Pipe _dataOUTGuiPipe;
    
    public QPFilter(Pipe _dataINGuiPip,Pipe _dataOUTGuiPipe) {
		super();
		this._dataINGuiPipe = _dataINGuiPip;
		this._dataOUTGuiPipe = _dataOUTGuiPipe;
	}
    public String getDataGui(){
        return _dataINGuiPipe.dataOUT();
    }
     
    public void sendDataGui( String tempData){
        _dataOUTGuiPipe.dataIN(tempData);
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
		// TODO Auto-generated method stub
		QueryProcessor QP = new QueryProcessor();
		String res=(String) this.getDataGui();
		String[] resSplit = res.split(",");
		int choix = Integer.parseInt(resSplit[0]);
		String result="";
		switch(choix){
			case 1:
			String param =resSplit[1];
			result =QP.NdByActor(param);
			sendDataGui(result);
			break;
			

			case 2:
			param =resSplit[1];
			result = QP.AvailableForRent(param);
			sendDataGui(result);
			break;
			

			case 3:
			String clientid=resSplit[1];
			result=QP.CustomerFilms(clientid);
			sendDataGui(result);
			
			break;

			case 4:
			clientid=resSplit[1];
			result = QP.Solde(clientid);
			sendDataGui(result);
			break;

			case 5:
			result=QP.OverdueItems();
			sendDataGui(result);
			break;

		}	
	}
}
 
 