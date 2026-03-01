package br.com.batch.senac.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "XXRH_BENNER_LOCAL_TRABALHO")
public class LocalTrabalho implements Domain {

	public LocalTrabalho(Long id) {
		this.id = id;
	}

	public LocalTrabalho() {

	}

	@Id
	@Column(name = "ID_FUNCIONARIO")
	private Long id;

	@Column(name = "VALOR")
	private String valor;

	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "DATA")
	private Date data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
