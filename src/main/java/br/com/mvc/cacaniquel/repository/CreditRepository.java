package br.com.mvc.cacaniquel.repository;

import br.com.mvc.cacaniquel.model.Credit;
import br.com.mvc.cacaniquel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CreditRepository extends JpaRepository<Credit, Long> {

    @Query("SELECT C"
            +  "  FROM Credit C"
            + "  WHERE C.user = :user")
    Credit findByUser(@Param("user") User user);
}
