package Store;

public class Client {

    private int accountBalance;
    private String name;
    private int customerID;

    public Client(int c, String n, int a){
        this.accountBalance=a;
        this.name=n;
        this.customerID=c;
    }
    public Client(){}
    public void setAccountBalance(int accountBalance) {
        this.accountBalance=accountBalance;
    } 
    public int getAccountBalance() {
        return this.accountBalance;
    } 
    public void setName(String name) {
        this.name=name;
    } 
    public String getName() {
        return this.name;
    } 
    public void setCustomerID(int customerID) {
        this.customerID=customerID;
    } 
    public int getCustomerID() {
        return this.customerID;
    } 
}