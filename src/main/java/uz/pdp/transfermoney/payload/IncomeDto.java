package uz.pdp.transfermoney.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class IncomeDto {

    private Long id;
    @NotNull(message = "from card can not be empty")
    private CardDto fromCard;
    @NotNull(message = "to card can not be empty")
    private CardDto toCard;
    @NotNull(message = "amount can not be empty")
    private Double amount;
    private LocalDateTime date;
    private Long userId;
}
