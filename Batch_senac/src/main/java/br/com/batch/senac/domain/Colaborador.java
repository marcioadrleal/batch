package br.com.batch.senac.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "XXRH_BENNER_FUNCIONARIO_SENAC")
public class Colaborador implements Domain {
	
	public Colaborador() {
		
	}
	
	public Colaborador(Long id) {
	  this.id = id;	
	}

	@Column(name = "COD_UO", nullable = false, length = 10)
	private String codUo;

	@Column(name = "DESC_UNIDADE", nullable = false, length = 45)
	private String descUnidade;

	@Id
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NOME", length = 45, nullable = false)
	private String nome;

	@Column(name = "RG_NRO", length = 13)
	private String rgNro;

	@Column(name = "RG_COMPLEMENTO", length = 2)
	private String rgComplemento;

	@Column(name = "RG_ORGAO_EMISSOR", length = 20)
	private String rgOrgaoEmissor;

	@Column(name = "RG_ESTADO_EMISSOR", length = 3)
	private String rgEstadoEmissor;

	@Column(name = "RG_DATA_EXPEDICAO")
	private Date rgDataExpedicao;

	@Column(name = "CPF_NRO", length = 9)
	private String cpfNro;

	@Column(name = "CPF_COMPL", length = 2)
	private String cpfCompl;

	@Column(name = "DATA_ADMISSAO")
	private Date dataAdmissao;

	@Column(name = "DATA_DEMISSAO")
	private Date dataDemissao;

	@Column(name = "ATIVO", length = 1)
	private String situacao;

	@Column(name = "E_MAIL", length = 255)
	private String email;

	@Column(name = "SIGLA", length = 20)
	private String sigla;

	@Column(name = "PIS_NRO")
	private Long pisNro;

	@Column(name = "DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Column(name = "TIPO_COLAB", length = 1)
	private String tipoColab;

	@Column(name = "ID_CARGO")
	private Long idCargo;

	@Column(name = "DC_CARGO", length = 45)
	private String dcCargo;

	@Column(name = "NRO_DEPENDENTES_IR")
	private Integer nrDependentesIr;

	@Column(name = "PAIS_NASCIMENTO", length = 5)
	private String paisNascimento;

	@Column(name = "ESTADO_NASCIMENTO", length = 3)
	private String estadonascimento;

	@Column(name = "CIDADE_NASCIMENTO", length = 25)
	private String cidadeNascimento;

	@Column(name = "DATA_NASCIMENTO")
	private Date dataNascimento;

	@Column(name = "NACIONALIDADE", length = 5)
	private String nacionalidade;

	@Column(name = "ESTADO_CIVIL", length = 1)
	private String estadoCivil;

	@Column(name = "SEXO", length = 1)
	private String sexo;

	@Column(name = "NOME_USUAL", length = 30)
	private String nomeUsual;

	@Column(name = "TELEFONE", length = 20)
	private String telefone;

	@Column(name = "CODIGO_DDD")
	private Long codigoDDD;

	@Column(name = "CELULAR_DDD")
	private Long celularDDD;

	@Column(name = "CELULAR_NUMERO", length = 10)
	private String celularNumero;

	@Column(name = "CEP", length = 9)
	private String cep;

	@Column(name = "ENDERECO", length = 40)
	private String endereco;

	@Column(name = "NRO_END", length = 5)
	private String nroEnd;

	@Column(name = "COMPLEMENTO_END", length = 25)
	private String complementoEnd;

	@Column(name = "CIDADE", length = 25)
	private String cidade;

	@Column(name = "BAIRRO", length = 25)
	private String bairro;

	@Column(name = "ESTADO", length = 3)
	private String estado;

	@Column(name = "CODIGO_BANCO	NUMBER", length = 22)
	private String codigoBanco;

	@Column(name = "NOM_BANCO	VARCHAR2", length = 30)
	private String nomBanco;

	@Column(name = "CODIGO_AGENCIA", length = 9)
	private String codigoAgencia;

	@Column(name = "CONTA_NUMERO", length = 20)
	private String contaNumero;

	@Column(name = "CONTA_DIGITO", length = 3)
	private String contaDigito;

	@Column(name = "ID_PADRAO")
	private Long idPadrao;

	@Column(name = "DESC_PADRAO", length = 45)
	private String descPadrao;

	@Column(name = "GRUPO_REEMB", length = 45)
	private String grupoReeb;

	@Column(name = "IDENTIFICACAO_USUARIO", length = 50)
	private String identificacaoUsuario;

	@Column(name = "NOME_SOCIAL", length = 45)
	private String nomeSocial;

	@Column(name = "ATUALIZACAO_CADASTRAL	CHAR", length = 1)
	private String atualizacaoCadastral;

	public String getCodUo() {
		return codUo;
	}

	public void setCodUo(String codUo) {
		this.codUo = codUo;
	}

	public String getDescUnidade() {
		return descUnidade;
	}

	public void setDescUnidade(String descUnidade) {
		this.descUnidade = descUnidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRgNro() {
		return rgNro;
	}

	public void setRgNro(String rgNro) {
		this.rgNro = rgNro;
	}

	public String getRgComplemento() {
		return rgComplemento;
	}

	public void setRgComplemento(String rgComplemento) {
		this.rgComplemento = rgComplemento;
	}

	public String getRgOrgaoEmissor() {
		return rgOrgaoEmissor;
	}

	public void setRgOrgaoEmissor(String rgOrgaoEmissor) {
		this.rgOrgaoEmissor = rgOrgaoEmissor;
	}

	public String getRgEstadoEmissor() {
		return rgEstadoEmissor;
	}

	public void setRgEstadoEmissor(String rgEstadoEmissor) {
		this.rgEstadoEmissor = rgEstadoEmissor;
	}

	public Date getRgDataExpedicao() {
		return rgDataExpedicao;
	}

	public void setRgDataExpedicao(Date rgDataExpedicao) {
		this.rgDataExpedicao = rgDataExpedicao;
	}

	public String getCpfNro() {
		return cpfNro;
	}

	public void setCpfNro(String cpfNro) {
		this.cpfNro = cpfNro;
	}

	public String getCpfCompl() {
		return cpfCompl;
	}

	public void setCpfCompl(String cpfCompl) {
		this.cpfCompl = cpfCompl;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Long getPisNro() {
		return pisNro;
	}

	public void setPisNro(Long pisNro) {
		this.pisNro = pisNro;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getTipoColab() {
		return tipoColab;
	}

	public void setTipoColab(String tipoColab) {
		this.tipoColab = tipoColab;
	}

	public Long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}

	public String getDcCargo() {
		return dcCargo;
	}

	public void setDcCargo(String dcCargo) {
		this.dcCargo = dcCargo;
	}

	public Integer getNrDependentesIr() {
		return nrDependentesIr;
	}

	public void setNrDependentesIr(Integer nrDependentesIr) {
		this.nrDependentesIr = nrDependentesIr;
	}

	public String getPaisNascimento() {
		return paisNascimento;
	}

	public void setPaisNascimento(String paisNascimento) {
		this.paisNascimento = paisNascimento;
	}

	public String getEstadonascimento() {
		return estadonascimento;
	}

	public void setEstadonascimento(String estadonascimento) {
		this.estadonascimento = estadonascimento;
	}

	public String getCidadeNascimento() {
		return cidadeNascimento;
	}

	public void setCidadeNascimento(String cidadeNascimento) {
		this.cidadeNascimento = cidadeNascimento;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNomeUsual() {
		return nomeUsual;
	}

	public void setNomeUsual(String nomeUsual) {
		this.nomeUsual = nomeUsual;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Long getCodigoDDD() {
		return codigoDDD;
	}

	public void setCodigoDDD(Long codigoDDD) {
		this.codigoDDD = codigoDDD;
	}

	public Long getCelularDDD() {
		return celularDDD;
	}

	public void setCelularDDD(Long celularDDD) {
		this.celularDDD = celularDDD;
	}

	public String getCelularNumero() {
		return celularNumero;
	}

	public void setCelularNumero(String celularNumero) {
		this.celularNumero = celularNumero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNroEnd() {
		return nroEnd;
	}

	public void setNroEnd(String nroEnd) {
		this.nroEnd = nroEnd;
	}

	public String getComplementoEnd() {
		return complementoEnd;
	}

	public void setComplementoEnd(String complementoEnd) {
		this.complementoEnd = complementoEnd;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getNomBanco() {
		return nomBanco;
	}

	public void setNomBanco(String nomBanco) {
		this.nomBanco = nomBanco;
	}

	public String getCodigoAgencia() {
		return codigoAgencia;
	}

	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	public String getContaNumero() {
		return contaNumero;
	}

	public void setContaNumero(String contaNumero) {
		this.contaNumero = contaNumero;
	}

	public String getContaDigito() {
		return contaDigito;
	}

	public void setContaDigito(String contaDigito) {
		this.contaDigito = contaDigito;
	}

	public Long getIdPadrao() {
		return idPadrao;
	}

	public void setIdPadrao(Long idPadrao) {
		this.idPadrao = idPadrao;
	}

	public String getDescPadrao() {
		return descPadrao;
	}

	public void setDescPadrao(String descPadrao) {
		this.descPadrao = descPadrao;
	}

	public String getGrupoReeb() {
		return grupoReeb;
	}

	public void setGrupoReeb(String grupoReeb) {
		this.grupoReeb = grupoReeb;
	}

	public String getIdentificacaoUsuario() {
		return identificacaoUsuario;
	}

	public void setIdentificacaoUsuario(String identificacaoUsuario) {
		this.identificacaoUsuario = identificacaoUsuario;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

	public String getAtualizacaoCadastral() {
		return atualizacaoCadastral;
	}

	public void setAtualizacaoCadastral(String atualizacaoCadastral) {
		this.atualizacaoCadastral = atualizacaoCadastral;
	}

}
