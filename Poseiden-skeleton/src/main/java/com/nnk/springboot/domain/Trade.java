package com.nnk.springboot.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Trade")
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tradeId;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Input has to be text")
    @NotBlank(message = "Account is mandatory")
    @Column(name = "account")
    private String account;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Input has to be text")
    @NotBlank(message = "Type is mandatory")
    @Column(name = "type")
    private String type;

    @Digits(integer = 15, fraction = 2)
    @Min(value = 0, message = "The value must be positive")
    @NotNull(message = "This Field cannot be empty")
    @Column(name = "buy_quantity")
    private Double buyQuantity;


    public Trade(String trade_account, String type, double v) {
    }
}
