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
    public List<ColaboradorResumoDTO> buscarColaboradores(String token) {
        return webClient.get()
                .uri("/api/v1/RH/Adm/ConsultasExternas/K_SENAC_COLABORADORES")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToFlux(ColaboradorResumoDTO.class)
                .collectList()
                .block();
    }

    public CompletableFuture<PessoaisDto> buscarPessoais(Long id, String token) {
        return webClient.get()
                .uri("/api/RH/Dossie/Colaborador/Pessoais/{id}", id)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(PessoaisDto.class)
                .toFuture();
    }

    public CompletableFuture<DadosBancariosDto> buscarBancarios(Long id, String token) {
        return webClient.get()
                .uri("/api/RH/Dossie/Colaborador/bancarios/{id}", id)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(DadosBancariosDto.class)
                .toFuture();
    }

    public CompletableFuture<UnidadeDto> buscarUnidade(Long idUnidade, String token) {
        return webClient.get()
                .uri("/api/RH/Dossie/unidade/{id}", idUnidade)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(UnidadeDto.class)
                .toFuture();
    }
    
    public CompletableFuture<DependentesDto> buscarDependentes(Long id, String token) {
        return webClient.get()
                .uri("/api/RH/Dossie/dependentes/{id}", id)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(DependentesDto.class)
                .toFuture();
    }
    
    public CompletableFuture<CargosDto> buscarCargos(Long idCargo, String token) {
        return webClient.get()
                .uri("/api/RH/Dossie/cargos/{id}", idCargo)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(CargosDto.class)
                .toFuture();
    }
}
