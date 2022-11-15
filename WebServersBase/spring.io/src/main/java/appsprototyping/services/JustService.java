package appsprototyping.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.transaction.Transactional;


@Service
@Slf4j
public class JustService {

    @Transactional
    public void anyPublicOperation()  {
        log.info("{}",TransactionSynchronizationManager.isActualTransactionActive());
    }

    public void otherTransaction()  {
        anyPublicOperation();
    }
}
