package br.com.mvc.cacaniquel.repository;

import br.com.mvc.cacaniquel.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}
