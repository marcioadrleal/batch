package br.com.batch.senac.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.batch.senac.service.MigracaoService;

@Component
@EnableScheduling
public class MigracaoScheduler {

	@Autowired
    private MigracaoService migracaoService;
   

    @Scheduled(cron = "0 0 2 * * ?")
    public void executar() {
        migracaoService.executarMigracao();
    }
}