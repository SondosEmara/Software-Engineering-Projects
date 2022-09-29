
package phase_2;

public class wallet {
    private int balance;
    wallet(){
        balance=0;
    }
    public void deposit(int depositValue){
      balance+=depositValue;
    }
    
    public void withdrawal(int withdrawalValue){

          balance=balance-withdrawalValue;
      //else 
    }
    public int getCurrentBalance(){return balance;}
    
}
