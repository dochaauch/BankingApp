package util;

import com.telran.bankingapp.entity.Account;
import com.telran.bankingapp.entity.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EntityCreator {
    public static final String UUID_EXAMPLE = "tes72405-ce50-49d9-9b5b-d6cf0cd61346";

    public static Account getAccount() {
        return new Account();
    }

    public static Product getProduct() {
        return new Product();
    }
}