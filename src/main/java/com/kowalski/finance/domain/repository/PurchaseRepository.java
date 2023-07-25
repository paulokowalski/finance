package com.kowalski.finance.domain.repository;

import com.kowalski.finance.api.v1.response.PurchaseResponse;
import com.kowalski.finance.domain.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {


    @Query(value = " SELECT p.nm_person_purchase as nomePessoa, sum(ip.vl_installment) as valor, date_part('month', ip.dt_installment) as mes " +
            " from installment_purchase ip " +
            " join purchase p on p.purchase_id = ip.purchase_id " +
            " where date_part('month', ip.dt_installment) = :mounth " +
            " group by p.nm_person_purchase, date_part('month', ip.dt_installment) " +
            " order by p.nm_person_purchase, date_part('month', ip.dt_installment) ",
            nativeQuery = true)
    List<PurchaseResponse> findAllByMounth(Double mounth);


}
