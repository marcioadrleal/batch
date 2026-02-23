package br.com.batch.senac.client;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.batch.senac.dto.CargosDto;
import br.com.batch.senac.dto.ColaboradorResumoDTO;
import br.com.batch.senac.dto.DadosBancariosDto;
import br.com.batch.senac.dto.DependentesDto;
import br.com.batch.senac.dto.PessoaisDto;
import br.com.batch.senac.dto.UnidadeDto;

@Service
public class ApiClient {

	@Autowired
    private WebClient webClient;

    // API principal
    public List<ColaboradorResumoDTO> buscarColaboradores() {
        return webClient.get()
                .uri("/api/RH/Dossie/Colaborador/Colaboradores")
                .retrieve()
                .bodyToFlux(ColaboradorResumoDTO.class)
                .collectList()
                .block();
    }

    public CompletableFuture<PessoaisDto> buscarPessoais(Long id) {
        return webClient.get()
                .uri("/api/RH/Dossie/Colaborador/Pessoais/{id}", id)
                .retrieve()
                .bodyToMono(PessoaisDto.class)
                .toFuture();
    }

    public CompletableFuture<DadosBancariosDto> buscarBancarios(Long id) {
        return webClient.get()
                .uri("/api/RH/Dossie/Colaborador/bancarios/{id}", id)
                .retrieve()
                .bodyToMono(DadosBancariosDto.class)
                .toFuture();
    }

    public CompletableFuture<UnidadeDto> buscarUnidade(Long idUnidade) {
        return webClient.get()
                .uri("/api/RH/Dossie/unidade/{id}", idUnidade)
                .retrieve()
                .bodyToMono(UnidadeDto.class)
                .toFuture();
    }
    
    public CompletableFuture<DependentesDto> buscarDependentes(Long id) {
        return webClient.get()
                .uri("/api/RH/Dossie/dependentes/{id}", id)
                .retrieve()
                .bodyToMono(DependentesDto.class)
                .toFuture();
    }
    
    public CompletableFuture<CargosDto> buscarCargos(Long idCargo) {
        return webClient.get()
                .uri("/api/RH/Dossie/cargos/{id}", idCargo)
                .retrieve()
                .bodyToMono(CargosDto.class)
                .toFuture();
    }
}
