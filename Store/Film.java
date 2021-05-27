package Store;
public class Film extends StockItem{
    private String acteur;
    public Film (int i,String t,int p,String actor){
        super(p,t,i);
        this.acteur=actor;
    }
    public void setActor(String acteur) {
        this.acteur=acteur;
    }
    public String getActor() {
       return this.acteur;
    }
}