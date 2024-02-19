package peaksoft.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import peaksoft.enamus.Country;
import peaksoft.enamus.HouseType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "house_gen")
    @SequenceGenerator(name = "house_gen", sequenceName = "house_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private HouseType houseType;

    private int room;
    private String address;

    @Column(length = 200000)
    private String image;

    private double price;

    @Enumerated(EnumType.STRING)
    private Country country;

    private String description;
    private boolean isBooked;
    private LocalDateTime bookingDate;
    private LocalDateTime releaseDate;

    @OneToOne(mappedBy = "house", cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    private Booking booking;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "agency_id")
    private Agency houseAgency;

    public House() {
    }
    public void setBooked(boolean booked) {
        isBooked = booked;
        if (booked) {
            this.bookingDate = LocalDateTime.now();
        } else {
            this.bookingDate = null;
            this.releaseDate = null;
        }
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

}