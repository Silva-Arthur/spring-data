package projeto.spring.data.dao;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projeto.spring.data.model.UsuarioSpringData;

@Repository
public interface InterfaceSpringUsuarioData extends JpaRepository<UsuarioSpringData, Long>{

	@Query(value = "select u from UsuarioSpringData u where u.nome like %?1%")
	public List<UsuarioSpringData> findByNomeLike(String nome);
	
	@Lock(LockModeType.READ) /*impedir que duas pessoas atualizem ao mesmo tempo um obj ou que um consulte e outro atualize*/
	@Transactional(readOnly = true) /*anotação opcional, mostrada somente para exemplificar o uso desta anotation em querys*/
	@Query(value = "select u from UsuarioSpringData u where u.nome = :nome")
	public UsuarioSpringData findByNome(@Param(value = "nome") String nome);
	
	default <S extends UsuarioSpringData> S saveEspecial(S entity) {

		/*Posso fazer minhas validações*/
		entity.setNome(entity.getNome().concat("- manipulado no save"));
		
		return save(entity);
	}
	
	@Modifying /*Necessário anotar com Modifying para indicar que estamos fazendo uma modificação no banco*/
	@Transactional /*Necessário anotar com Transactional para abrir uma transação no banco de dados*/
	@Query(value = "delete from UsuarioSpringData u where u.nome = :nomePessoa")
	public void deleteByNome(@Param(value = "nomePessoa") String nome);
	
	@Modifying
	@Transactional(rollbackFor = NullPointerException.class) /*Faz o rollback somente se houve algum erro de null*/
	@Query(value = "update UsuarioSpringData u set u.email = ?1 where u.nome = ?2")
	public void updateEmailByNome(String email, String nome);
}
