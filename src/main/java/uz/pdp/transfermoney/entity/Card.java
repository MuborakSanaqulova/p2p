package uz.pdp.transfermoney.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String number;

    private Double balance = 0.0d;

    @Column(nullable = false)
    @JsonFormat(pattern = "MM/yy")
    private LocalDate expireDate;

    @Column(nullable = false)
    private Long userId;

    private boolean active = true;

}
