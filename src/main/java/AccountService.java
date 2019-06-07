public interface AccountService {

    Double checkBalance(Integer accountIdentifier);

    boolean withdrawAmount(Integer accountIdentifier, Integer quantity);
}
