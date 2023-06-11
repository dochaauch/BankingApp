package util;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.dto.ProductDTO;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class DtoCreator {
    public static final String CREATEDAT = "14.03.2023";
    public static ProductDTO getProductDto() {
        return new ProductDTO("aaf2bc95-1403-4831-b8b5-8969445548ec",
                "Savings Account");
    }

    public static AccountDTO getTestAccountDto() {
        return new AccountDTO(
                "Checking_AccountTest",
                "CURRENT",
                "ACTIVE",
                "1000.00",
                "EUR",
                "106ebf87-6fd0-474a-af49-cb9910af7f47",
                LocalDateTime.parse("2023-03-14T00:00:00"),
                LocalDateTime.parse("2023-03-14T00:00:00"),
                "Jane"
                //null
        );
    }

    public static AccountDTO getValidAccountDto() {
        return new AccountDTO(
                "Checking_Account1",
                "CURRENT",
                "ACTIVE",
                "1000.00",
                "EUR",
                "611195b6-c02b-44cd-a8a9-92a85a521262",
                LocalDateTime.parse("2023-03-14T00:00:00"),
                LocalDateTime.parse("2023-03-14T00:00:00"),
                "John"
        );
    }
}