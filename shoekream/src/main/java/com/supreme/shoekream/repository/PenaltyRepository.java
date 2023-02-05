package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Penalty;
import com.supreme.shoekream.model.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PenaltyRepository extends JpaRepository<Penalty,Long> {

}
