package br.com.mvc.cacaniquel.repository;

import br.com.mvc.cacaniquel.model.SlotMachineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotMachineRepository extends JpaRepository<SlotMachineModel, Long>{
}
