package com.k8.ms.repository;

import com.k8.ms.entity.CoinTrade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sujith T
 *
 * <!In God We Trust>
 */
@Repository
public interface CoinTradeDao extends MongoRepository<CoinTrade, String> {

    List<CoinTrade> findByCurrency(String code);
}
