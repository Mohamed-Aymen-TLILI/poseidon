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
    private Integer id ;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Input has to be text")
    @NotBlank(message = "Moodys Rating is mandatory")
    @Column(name = "moodys_rating")
    private String moodysRating ;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Input has to be text")
    @NotBlank(message = "Sand Prating is mandatory")
    @Column(name = "sand_prating")
    private String sandPrating ;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Input has to be text")
    @NotBlank(message = "fitch Rating is mandatory")
    @Column(name = "fitch_rating")
    private String fitchRating ;

    @Digits(integer = 20, fraction = 0)
    @NotNull(message = "Order number is mandatory")
    @Min(value = 0, message = "The value must be positive")
    @Column(name = "order_number")
    private Integer orderNumber ;


    public Rating(String moodys_rating, String sand_pRating, String fitch_rating, int i) {
    }
}
