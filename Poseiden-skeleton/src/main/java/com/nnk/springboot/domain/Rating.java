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
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Pattern(regexp="^[A-Za-z]*$", message = "Input has to be text")
    @NotBlank(message = "Moodys Rating is mandatory")
    private String moodysRating;

    @Pattern(regexp="^[A-Za-z]*$", message = "Input has to be text")
    @NotBlank(message = "SendP Rating is mandatory")
    private String sandPRating;

    @Pattern(regexp="^[A-Za-z]*$", message = "Input has to be text")
    @NotBlank(message = "Fitch Rating is mandatory")
    private String fitchRating;

    @Digits(integer = 20, fraction = 0)
    @Min(value = 0L, message = "The value must be positive")
    @NotNull(message = "Numbers has to be present")
    private Integer orderNumber;

    public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
    }
}
