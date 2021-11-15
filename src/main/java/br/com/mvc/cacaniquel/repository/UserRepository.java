package br.com.mvc.cacaniquel.repository;

import br.com.mvc.cacaniquel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	User findByUsername(String username);

	
}
