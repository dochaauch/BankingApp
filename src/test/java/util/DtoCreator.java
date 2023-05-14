package util;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.dto.ProductDTO;
import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@UtilityClass
public class DtoCreator {
    public static ProductDTO getProductDto() {
        return new ProductDTO("aaf2bc95-1403-4831-b8b5-8969445548ec",
                "Savings Account");
    }

    public static AccountDTO getAccountDto() {
        return new AccountDTO(
                "Checking_Account1",
                "CURRENT",
                "ACTIVE",
                "1000.00",
                "EUR",
                "106ebf87-6fd0-474a-af49-cb9910af7f47",
                Timestamp.valueOf("2023-03-14 00:00:00").toLocalDateTime(),
                Timestamp.valueOf("2023-03-14 00:00:00").toLocalDateTime(),
                "Jane",
                null
        );
    }
}