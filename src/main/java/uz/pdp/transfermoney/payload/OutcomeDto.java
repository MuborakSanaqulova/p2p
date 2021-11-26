package uz.pdp.transfermoney.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutcomeDto {

    private Long id;
    @NotNull(message = "from card can not be empty")
    private CardDto fromCard;
    @NotNull(message = "to card can not be empty")
    private CardDto toCard;
    @NotNull(message = "amount can not be empty")
    private Double amount;
    private LocalDateTime date;
    private Double commissionAmount = 0.0035;
    private Long userId;

}
