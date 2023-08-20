package ru.skyeng.postoffice.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "postal_delivery")
public class PostalDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Enumerated(EnumType.STRING)
    DepartureType type;

    String index;

    String address;

    @Column(name = "recipients_name")
    String recipientsName;

}
