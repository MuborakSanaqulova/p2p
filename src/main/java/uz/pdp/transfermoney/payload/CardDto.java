package uz.pdp.transfermoney.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

    private Long id;

    @NotNull(message = "Username can not be empty")
    private String username;

    @NotNull(message = "Number can not be empty")
    private String number;

    private Double balance;

    @NotNull(message = "Expired date can not be empty")
    @JsonFormat(pattern = "MM/yy")
    private LocalDate expiredDate;

    @NotNull(message = "user can not be empty")
    private Long userId;

    private boolean active;

}
