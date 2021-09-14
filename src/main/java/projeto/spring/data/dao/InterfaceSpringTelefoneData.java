package projeto.spring.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto.spring.data.model.TelefoneSpringData;

@Repository
public interface InterfaceSpringTelefoneData extends JpaRepository<TelefoneSpringData, Long>{

}
