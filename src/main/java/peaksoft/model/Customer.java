package peaksoft.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.intellij.lang.annotations.Pattern;
import peaksoft.enamus.Gender;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_gen")
    @SequenceGenerator(name = "customer_gen", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String surname;
    @Column(length = 200000)
    private String image;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private String phoneNumber;
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "customer", cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Booking> bookings;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    private Agency customerAgency;


    public Customer() {
    }

}
