package com.kowalski.finance.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Purchase {

    @Id
    @Column(name = "purchase_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "nm_product")
    private String nameProduct;

    @Column(name = "vl_product")
    private BigDecimal valueProduct;

    @Column(name = "dt_purchase")
    private LocalDate datePurchase;

    @Column(name = "nr_installment")
    private Integer numberInstallment;

    @Column(name = "nm_person_purchase")
    private String namePersonPurchase;

    @Column(name = "nm_card")
    private String nameCard;

}
