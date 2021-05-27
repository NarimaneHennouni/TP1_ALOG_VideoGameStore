package Store;

public class Jeux extends StockItem{
    private String platform;
    
    public Jeux(int i,String t,int p,String paltform){
        super(p, t, i);
        this.platform=platform;
    }
    /*setters & getters*/
    public void setPlatform(String paltform) {
        this.platform=platform;
    } 
    public String getPlatform() {
        return this.platform;
    } 
}