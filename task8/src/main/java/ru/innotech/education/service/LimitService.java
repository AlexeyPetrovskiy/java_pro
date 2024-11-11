package ru.innotech.education.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.innotech.education.dto.PaymentsInfo;
import ru.innotech.education.dto.UserLimit;
import ru.innotech.education.repository.UserLimitRepository;

@Service
@EnableScheduling
public class LimitService {

    private final UserLimitRepository repository;
    /* Логичным было бы сделать мапу в которой хранить лимит для каждого пользователя
    * ,инициализировать ее из БД при запуске.
    * В задании явно не указано, не стал усложнять, лимит для всех пользователя 1000 */
    private long limit = 10000;

    public LimitService(UserLimitRepository repository) {
        this.repository = repository;
    }

    /* Изменяем дневной лимит */
    public void setLimit(long limit) {
        this.limit = limit;
    }

    /* Шедулер для сброса дневного лимита */
    @Scheduled(cron = "0 0 0 * * *")
    public void update() {
        System.out.println(repository.findAll());
        repository.findAll().forEach(e -> {
                    e.setLimit(limit);
                    repository.save(e);
                }
        );
    }

    /* Получаем лимит пользователя, если такого нет то создаем его с дефолтным лимитом */
    public Long getLimitById(long id){
        return repository.findById(id).orElseGet(
                () -> repository.save(new UserLimit(id, limit)))
                .getLimit();
    }

    /* Уменьшение лимита на сумму платежа */
    public void limitReduction(PaymentsInfo paymentsInfo){
        long id = paymentsInfo.getId_user();
        long userlimit = getLimitById(id);
        if (userlimit - paymentsInfo.getAmount() >= 0){
            repository.save(new UserLimit(id, userlimit - paymentsInfo.getAmount()));
        } else {
            throw new LimitServiceException("Превышение дневного лимита");
        }
    }

    /* Восстановление лимита на сумму платежа */
    public void limitRollback(PaymentsInfo paymentsInfo){
        long id = paymentsInfo.getId_user();
        long userlimit = repository.findById(id).get().getLimit();
        repository.save(new UserLimit(id, userlimit + paymentsInfo.getAmount()));
    }

}
