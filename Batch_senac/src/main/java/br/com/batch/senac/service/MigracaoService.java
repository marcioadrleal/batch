package br.com.batch.senac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batch.senac.client.ApiClient;
import br.com.batch.senac.domain.Colaborador;
import br.com.batch.senac.dto.CargosDto;
import br.com.batch.senac.dto.ColaboradorResumoDTO;
import br.com.batch.senac.dto.DadosBancariosDto;
import br.com.batch.senac.dto.DependentesDto;
import br.com.batch.senac.dto.PessoaisDto;
import br.com.batch.senac.dto.UnidadeDto;
import br.com.batch.senac.repository.ColaboradorRepository;

@Service
public class MigracaoService {

	@Autowired
    private  ApiClient apiClient;
	
	@Autowired
    private ColaboradorRepository colaboradorRepository;
    
    @Autowired
    private  ExecutorService executor;

    public void executarMigracao() {

        List<ColaboradorResumoDTO> colaboradores =
                apiClient.buscarColaboradores();

        List<List<ColaboradorResumoDTO>> chunks =
                partition(colaboradores, 200);

        for (List<ColaboradorResumoDTO> chunk : chunks) {
            processarChunk(chunk);
        }
    }

    private void processarChunk(List<ColaboradorResumoDTO> chunk) {

        List<CompletableFuture<Colaborador>> futures = new ArrayList<>();

        for (ColaboradorResumoDTO resumo : chunk) {
            futures.add(
                    CompletableFuture.supplyAsync(
                            () -> processarColaborador(resumo),
                            executor
                    )
            );
        }

        List<Colaborador> colaboradores = new ArrayList<>();

        for (CompletableFuture<Colaborador> future : futures) {
            colaboradores.add(future.join());
        }

        /* Salvar colaboradores e local de trabalho
        colaboradorRepository.saveAll(colaboradores);
        */ 
        
    }

    private Colaborador processarColaborador(ColaboradorResumoDTO resumo) {

        CompletableFuture<PessoaisDto> pessoais =
                apiClient.buscarPessoais(resumo.getIdColaborador());

        CompletableFuture<DadosBancariosDto> bancarios =
                apiClient.buscarBancarios(resumo.getIdColaborador());

        CompletableFuture<UnidadeDto> unidade =
                apiClient.buscarUnidade(resumo.getIdUnidade());

        CompletableFuture<DependentesDto> dependente =
                apiClient.buscarDependentes(resumo.getIdColaborador());
        
        CompletableFuture<CargosDto> cargo =
                apiClient.buscarCargos(resumo.getIdCargo());
        

        CompletableFuture.allOf(pessoais, bancarios, unidade, dependente , cargo ).join();
        
        return null;

      /*  return montarEntity(
                resumo,
                pessoais.join(),
                bancarios.join(),
                unidade.join(),
                localt.join()
        ); */
    }

  /* montar entity de colaborador e local de trabalho 
   * private Colaborador montarEntity(
            ColaboradorResumoDTO resumo,
            PessoaisDTO pessoais,
            BancariosDTO bancarios,
            UnidadeDTO unidade,
            LocalTrabalhoDTO localDto
    ) {

        Colaborador c = new Colaborador();
        c.setIdExterno(resumo.getId());
        c.setNome(pessoais.getNome());
        c.setCpf(pessoais.getCpf());
        c.setBanco(bancarios.getBanco());
        c.setAgencia(bancarios.getAgencia());
        c.setUnidadeNome(unidade.getNome());
        c.setEnderecoLocalTrabalho(localDto.getEndereco());

        return c;
    }
 */
    private <T> List<List<T>> partition(List<T> list, int size) {
        List<List<T>> parts = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            parts.add(list.subList(i, Math.min(i + size, list.size())));
        }
        return parts;
    }
}
