package peaksoft.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import peaksoft.enamus.Country;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agency_gen")
    @SequenceGenerator(name = "agency_gen", sequenceName = "agency_seq", allocationSize = 1)
    private Long id;

    private String name;

    @Column(length = 20000)
    private String image;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Column
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "customerAgency", cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Customer> agencyCustomers;

    @OneToMany(mappedBy = "houseAgency", cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    private List<House> agencyHouses;



    public Agency() {

    }

    @Override
    public String toString() {
        return "Agency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", country=" + country +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
