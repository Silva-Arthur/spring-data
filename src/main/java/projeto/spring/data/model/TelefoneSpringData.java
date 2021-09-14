package projeto.spring.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TelefoneSpringData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tipo;
	
	@Column(nullable = false)
	private String numero;
	
	@ManyToOne(optional = false)
	private UsuarioSpringData usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public UsuarioSpringData getUsuarioSpringData() {
		return usuario;
	}

	public void setUsuarioSpringData(UsuarioSpringData usuarioSpringData) {
		this.usuario = usuarioSpringData;
	}

	@Override
	public String toString() {
		return "TelefoneSpringData [id=" + id + ", tipo=" + tipo + ", numero=" + numero + ", usuario=" + usuario + "]";
	}
}
