package br.com.batch.senac.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.batch.senac.client.ApiClient;
import br.com.batch.senac.domain.Colaborador;
import br.com.batch.senac.domain.Domain;
import br.com.batch.senac.domain.LocalTrabalho;
import br.com.batch.senac.dto.CargosDto;
import br.com.batch.senac.dto.ColaboradorResumoDTO;
import br.com.batch.senac.dto.DadosBancariosDto;
import br.com.batch.senac.dto.DependentesDto;
import br.com.batch.senac.dto.PessoaisDto;
import br.com.batch.senac.dto.UnidadeDto;
import br.com.batch.senac.repository.ColaboradorRepository;
import br.com.batch.senac.repository.LocalTrabalhoRepository;

@Service
public class MigracaoService {

	@Autowired
	private ApiClient apiClient;

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	@Autowired
	private LocalTrabalhoRepository localTrabalhoRepository;

	@Autowired
	private ExecutorService executor;

	@Autowired
	private ConnectionService connectionToken;

	public void executarMigracao() {

		String token = connectionToken.getToken();

		List<ColaboradorResumoDTO> colaboradores = apiClient.buscarColaboradores(token);

		List<List<ColaboradorResumoDTO>> chunks = partition(colaboradores, 200);

		for (List<ColaboradorResumoDTO> chunk : chunks) {
			processarChunk(chunk, token);
		}
	}

	private void processarChunk(List<ColaboradorResumoDTO> chunk, String token) {

		List<CompletableFuture<Domain[]>> futures = new ArrayList<>();

		for (ColaboradorResumoDTO resumo : chunk) {
			futures.add(CompletableFuture.supplyAsync(() -> processarColaborador(resumo, token), executor));
		}

		List<Colaborador> colaboradores = new ArrayList<>();
		List<LocalTrabalho> localTrabalho = new ArrayList<>();

		for (CompletableFuture<Domain[]> future : futures) {
			Colaborador colab = (Colaborador) future.join()[0];
			LocalTrabalho trab = (LocalTrabalho) future.join()[1];
			colaboradores.add(colab);
			localTrabalho.add(trab);
		}

		// Salvar colaboradores e local de trabalho
		colaboradorRepository.saveAll(colaboradores);
		localTrabalhoRepository.saveAll(localTrabalho);

	}

	private Domain[] processarColaborador(ColaboradorResumoDTO resumo, String token) {

		CompletableFuture<PessoaisDto> pessoais = apiClient.buscarPessoais(resumo.getIdColaborador(), token);

		CompletableFuture<DadosBancariosDto> bancarios = apiClient.buscarBancarios(resumo.getIdColaborador(), token);

		CompletableFuture<UnidadeDto> unidade = apiClient.buscarUnidade(resumo.getIdUnidade(), token);

		CompletableFuture<DependentesDto> dependente = apiClient.buscarDependentes(resumo.getIdColaborador(), token);

		CompletableFuture<CargosDto> cargo = apiClient.buscarCargos(resumo.getIdCargo(), token);

		CompletableFuture.allOf(pessoais, bancarios, unidade, dependente, cargo).join();

		return montarEntityColaborador(resumo, pessoais.join(), bancarios.join(), unidade.join(), dependente.join());

	}

	/*
	 * Metodo para montar o Entity
	 */
	public Domain[] montarEntityColaborador(ColaboradorResumoDTO resumo, PessoaisDto pessoais,
			DadosBancariosDto dadosBancarios, UnidadeDto unidade, DependentesDto dependentes) {
		Colaborador colaborador = findColaborador(resumo.getIdColaborador());
		colaborador.setAtualizacaoCadastral(null);
		colaborador.setBairro(pessoais.getBairro());
		colaborador.setCelularDDD(resumo.getCelularDdd());
		colaborador.setCelularNumero(resumo.getCelularNumero());
		colaborador.setCep(pessoais.getCep());
		colaborador.setCidade(pessoais.getCidade());
		colaborador.setCidadeNascimento(resumo.getCidadeNascimento());
		colaborador.setCodigoAgencia(dadosBancarios.getCodigoAgencia());
		colaborador.setCodigoBanco(dadosBancarios.getCodigoBanco());
		colaborador.setContaNumero(dadosBancarios.getContaNumero());
		colaborador.setContaDigito(dadosBancarios.getContaDigito());
		colaborador.setCodUo(unidade.getCodigoUnidade());
		colaborador.setDescUnidade(resumo.getUnidade());
		colaborador.setNome(resumo.getNome());
		colaborador.setRgNro(resumo.getNroRg());
		colaborador.setRgComplemento(resumo.getRgComplemento());
		colaborador.setRgOrgaoEmissor(resumo.getRgEmissor());
		colaborador.setRgEstadoEmissor(resumo.getRgEstado());
		colaborador.setRgDataExpedicao(resumo.getRgData());
		colaborador.setCpfNro(resumo.getNrCpf());
		colaborador.setCpfCompl(resumo.getCpfComplemento());
		colaborador.setDataAdmissao(resumo.getDataAdmissao());
		colaborador.setDataDemissao(resumo.getDataDemissao());
		colaborador.setSituacao(resumo.getSituacao());
		colaborador.setEmail(resumo.getEmail());
		colaborador.setSigla(resumo.getSigla());
		colaborador.setPisNro(resumo.getNroPis());
		colaborador.setDataAtualizacao(resumo.getDataAtualizacao());
		colaborador.setTipoColab(resumo.getTipoColab());
		colaborador.setIdCargo(resumo.getIdCargo());
		colaborador.setDcCargo(resumo.getDcCargo());
		colaborador.setNrDependentesIr(dependentes.getNro_dependentes_ir());
		colaborador.setPaisNascimento(resumo.getPaisNascimento());
		colaborador.setEstadonascimento(resumo.getEstadoNascimento());
		colaborador.setDataNascimento(pessoais.getDataNascimento());
		colaborador.setNacionalidade(resumo.getNacionalidade());
		colaborador.setEstadoCivil(pessoais.getEstadoCivil());
		colaborador.setSexo(pessoais.getSexo());
		colaborador.setNomeUsual(resumo.getNomeUsual());
		colaborador.setTelefone(resumo.getTelefone());
		colaborador.setCodigoDDD(resumo.getCodigoDdd());
		colaborador.setEndereco(pessoais.getEndereco());
		colaborador.setNroEnd(pessoais.getNroEnd());
		colaborador.setComplementoEnd(pessoais.getComplementoEnd());
		colaborador.setEstado(pessoais.getEstado());
		colaborador.setIdPadrao(null); // Observação
		colaborador.setDescPadrao(null); // ~Observação
		colaborador.setGrupoReeb(null); // Observação
		colaborador.setIdentificacaoUsuario(resumo.getIdentificacaoUsuario());
		colaborador.setNomeSocial(null); // Observação
		LocalTrabalho localT = findLocalTrabalho(resumo.getIdColaborador());
		localT.setData(new Date());
		localT.setDescricao(null); // obs
		localT.setId(resumo.getIdColaborador());
		localT.setValor(null); // verificar
		Domain[] valor = { colaborador, localT };

		return valor;
	}

	@Cacheable("colaboradorCache")
	public Colaborador findColaborador(Long id) {
		return colaboradorRepository.findById(id).orElse(new Colaborador(id));
	}

	@Cacheable("localTrabalhoCache")
	public LocalTrabalho findLocalTrabalho(Long id) {
		return localTrabalhoRepository.findById(id).orElse(new LocalTrabalho(id));
	}

	private <T> List<List<T>> partition(List<T> list, int size) {
		List<List<T>> parts = new ArrayList<>();
		for (int i = 0; i < list.size(); i += size) {
			parts.add(list.subList(i, Math.min(i + size, list.size())));
		}
		return parts;
	}
}
