package util;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.dto.ClientDTO;
import com.telran.bankingapp.dto.ManagerDTO;
import com.telran.bankingapp.dto.ProductDTO;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class DtoCreator {
    public static final String CREATEDAT = "14.03.2023";
    public static final String CREATEDATISO = "2023-03-14T00:00:00Z";

    public static ProductDTO getProductDto() {
        return new ProductDTO("aaf2bc95-1403-4831-b8b5-8969445548ec", "Savings Account");
    }


    public static AccountDTO getTestAccountDto() {
        return new AccountDTO("Checking_AccountTest",
                "CURRENT",
                "ACTIVE",
                "1000.00",
                "EUR",
                "106ebf87-6fd0-474a-af49-cb9910af7f47",
                LocalDateTime.parse("2023-03-14T00:00:00"),
                LocalDateTime.parse("2023-03-14T00:00:00"),
                "Jane"
        );
    }


    public static AccountDTO getValidAccountDto() {
        return new AccountDTO("Checking_Account1",
                "CURRENT",
                "ACTIVE",
                "1000.00",
                "EUR",
                "611195b6-c02b-44cd-a8a9-92a85a521262",
                LocalDateTime.parse("2023-03-14T00:00:00"),
                LocalDateTime.parse("2023-03-14T00:00:00"),
                "John");
    }


    public static ClientDTO getValidClientDto() {
        return new ClientDTO("611195b6-c02b-44cd-a8a9-92a85a521262",
                "John",
                "Doe",
                "11cf2217-b9d0-4d58-9ebb-f7f61e7206a6",
                "ACTIVE",
                "ABCD1234",
                "johndoe@example.com",
                "123 Main St",
                "555-1234",
                LocalDateTime.of(2023, 03, 14, 0, 0, 0),
                LocalDateTime.of(2023, 03, 14, 0, 0, 0)
        );
    }

    public static ManagerDTO getValidManagerDto() {
        return new ManagerDTO("11cf2217-b9d0-4d58-9ebb-f7f61e7206a6",
                "Jane",
                "Doe",
                "ACTIVE",
                LocalDateTime.of(2023, 03, 14, 0, 0, 0),
                LocalDateTime.of(2023, 03, 14, 0, 0, 0)
        );
    }
}