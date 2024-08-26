package hello.springtx.apply;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@SpringBootTest
public class InternalCallV2Test {
                //                  트랜잭션 AOP 주의 사항 - 프록시 내부 호출2 => 내부 호출을 외부 통해서...
    @Autowired
    CallService callService;

    @Test
    void printProxy() {
        log.info("callService class={}", callService.getClass());
    }

    @Test
    void externalCallV2() {
        callService.external();
    }

    @TestConfiguration      //          Test Bean 주입 설정 부분  ========>       해당 부분은 런타임때 실행됨
    static class InternalCallV1TestConfig {

        @Bean
        CallService callService() {
            log.info("internalService 반영된 callService 정의 완료");  // ------------------ 6
            return new CallService(internalService());

        }

        @Bean
        InternalService internalService() {
            log.info("internalService 정의 완료");  // ------------------ 5
            return new InternalService();
        }

    }

    @Slf4j
    @RequiredArgsConstructor
    static class CallService {

        private final InternalService internalService;

        public void external() {
            log.info("call external");  //                  ------------- 1
            printTxInfo();
            internalService.internal();
        }

        private void printTxInfo() {
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}", txActive);             // -------------- 2
        }
    }

    static class InternalService {

        @Transactional
        public void internal() {
            log.info("call internal");      // ----------------------- 3
            printTxInfo();
        }

        private void printTxInfo() {
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx Internal active={}", txActive);  // ---------------------- 4
        }
    }
}
