package entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private String zipCode;

    @Column
    private int flatNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
