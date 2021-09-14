package projeto.spring.data;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.dao.InterfaceSpringTelefoneData;
import projeto.spring.data.dao.InterfaceSpringUsuarioData;
import projeto.spring.data.model.TelefoneSpringData;
import projeto.spring.data.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTeste {
	
	@Autowired
	private InterfaceSpringUsuarioData interfaceSpringUsuarioData;
	
	@Autowired
	private InterfaceSpringTelefoneData interfaceSpringTelefoneData;
	
	@Test
	public void testeInsert() {
		UsuarioSpringData usuario = new UsuarioSpringData();
		
		usuario.setEmail("maria@outlook.com");
		usuario.setIdade(21);
		usuario.setLogin("asilva");
		usuario.setSenha("123456");
		usuario.setNome("Arthur Silva - testando alterando a tabelea telefones");
		
		interfaceSpringUsuarioData.save(usuario);
		
		System.out.println("Usuários cadastrados: " + interfaceSpringUsuarioData.count());
	}
	
	@Test
	public void testeConsulta() {
		
		UsuarioSpringData usuario = interfaceSpringUsuarioData.findById(1L).get();
		System.out.println(usuario.getId());
		System.out.println(usuario.getEmail());
		System.out.println(usuario.getNome());
		System.out.println(usuario.getSenha());
		System.out.println(usuario.getIdade());
		System.out.println(usuario.getLogin());
		
		for (TelefoneSpringData telefone : usuario.getTelefones()) {
			System.out.println(telefone);
		}
	}
	
	@Test
	public void testeConsultaTodos() {
		List<UsuarioSpringData> lista = interfaceSpringUsuarioData.findAll();
		
		for (UsuarioSpringData usuarioSpringData : lista) {
			System.out.println(usuarioSpringData);
		}
	}
	
	@Test
	public void testeUpdate() {
		UsuarioSpringData usuario = interfaceSpringUsuarioData.findById(3L).get();
		
		usuario.setLogin("maria.silva");
		
		usuario = interfaceSpringUsuarioData.save(usuario);
		
		System.out.println("Usuário alterado: " + usuario);
	}
	
	@Test
	public void testeDelete() {
		UsuarioSpringData usuario = interfaceSpringUsuarioData.findById(6L).get();
		
		interfaceSpringUsuarioData.deleteById(usuario.getId());
	}

	@Test
	public void testeFindByNomeLike() {
		List<UsuarioSpringData> usuario = interfaceSpringUsuarioData.findByNomeLike("Arthur");

		for (UsuarioSpringData usuarioSpringData : usuario) {
			System.out.println(usuarioSpringData);
		}
	}
	
	@Test
	public void testeFindByNome() {
		UsuarioSpringData usuario = interfaceSpringUsuarioData.findByNome("Arthur Silva");

		System.out.println(usuario);
	}
	
	@Test
	public void testeDeleteByNome() {
		interfaceSpringUsuarioData.deleteByNome("Arthur Silva- manipulado no save");
	}

	@Test
	public void testeUpdateEmailByNome() {
		interfaceSpringUsuarioData.updateEmailByNome("biapinho@gmail.com", "Beatriz Silva");
	}
	
	@Test
	public void insertTelefone() {
		TelefoneSpringData telefone = new TelefoneSpringData();
		
		telefone.setUsuarioSpringData(new UsuarioSpringData());
		telefone.getUsuarioSpringData().setId(1L);
		telefone.setNumero("(71) 98222-2413");
		telefone.setTipo("Residencial");
		telefone = interfaceSpringTelefoneData.save(telefone);
		
		System.out.println(telefone);
	}
}
