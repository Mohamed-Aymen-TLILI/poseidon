package com.nnk.springboot.domain;


import lombok.*;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bidListId ;

    @Pattern(regexp="^[A-Za-z]*$", message = "Input has to be text")
    @NotBlank(message = "Account is mandatory")
    @Column(name = "account", unique = true)
    private String account ;

    @Pattern(regexp="^[A-Za-z]*$", message = "Input has to be text")
    @NotBlank(message = "Account is mandatory")
    @Column(name = "type")
    private String type ;

    @Digits(integer = 15, fraction = 2)
    @Min(value = 0, message = "The value must be positive")
    @NotNull(message = "This Field cannot be empty")
    @Column(name = "bidQuantity")
    private Double bidQuantity ;


    public BidList(String account, String type, Double bidQuantity) {
    }
}
