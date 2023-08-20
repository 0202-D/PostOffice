package ru.skyeng.postoffice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToOne
    @JoinColumn(name = "postal_delivery_id")
    PostalDelivery postalDelivery;

    @OneToOne
    @JoinColumn(name = "post_office_id")
    PostOffice postOffice;

    @Enumerated(EnumType.STRING)
    Status status;

    LocalDateTime localDateTime;
}
