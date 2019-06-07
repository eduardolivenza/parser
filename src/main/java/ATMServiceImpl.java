public class ATMServiceImpl {


    private AccountService service;

    public ATMServiceImpl(){
        this.service = new AccountServiceImpl();
    }

    public void replenish(){}

    public String checkBalance(Integer accountId)
    {
        Double value = service.checkBalance(accountId);
        return " ACCOUNT: " + accountId + " --> " + value + "GBP";
    }

    public void withdrawal(){}

    /*
    * - Replenish:

o Sets up the service with currency notes of denominations 5, 10, 20 and 50

- Check balance:

o Returns a formatted string to display

- Withdraw:

o Returns notes of the correct denominations

o Allow withdrawals between 20 and 250 inclusive, in multiples of 5 o Disburse smallest number of notes

o Always disburse at least one 5 note, if possible
    * */


}
