public class Account {

    private Integer accountId;

    private Double balance;

    public Account(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public Account setBalance(Double balance) {
        this.balance = balance;
        return this;
    }
}
