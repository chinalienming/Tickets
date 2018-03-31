package nju.dao;

import nju.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettlementRepository extends JpaRepository<Settlement,Integer> {
}
