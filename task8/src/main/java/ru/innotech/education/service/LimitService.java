package ru.innotech.education.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.innotech.education.config.LimitServiceConfiguration;
import ru.innotech.education.dto.PaymentsInfo;
import ru.innotech.education.dto.UserLimit;
import ru.innotech.education.repository.UserLimitRepository;

import java.util.List;

@Service
@EnableScheduling
public class LimitService {

    private final UserLimitRepository repository;
    private final LimitServiceConfiguration configuration;
    /* Логичным было бы сделать мапу в которой хранить лимит для каждого пользователя
    * ,инициализировать ее из БД при запуске.
    * В задании явно не указано, не стал усложнять, лимит для всех пользователя 1000 */
    private double limit;

    public LimitService(UserLimitRepository repository, LimitServiceConfiguration configuration) {
        this.repository = repository;
        this.configuration = configuration;
        limit = configuration.getLimit();
    }

    /* Изменяем дневной лимит */
    public void setLimit(double limit) {
        this.limit = limit;
    }

    /* Шедулер для сброса дневного лимита */
    @Scheduled(cron = "0 0 0 * * *")
    public void update() {
        List<UserLimit> userLimits = repository.findAll();
        userLimits.forEach(e -> e.setLimit(limit));
        repository.saveAll(userLimits);
    }

    /* Получаем лимит пользователя, если такого нет то создаем его с дефолтным лимитом */
    public Double getLimitById(long id){
        return repository.findById(id).orElseGet(
                () -> repository.save(new UserLimit(id, limit)))
                .getLimit();
    }

    /* Уменьшение лимита на сумму платежа */
    public void limitReduction(PaymentsInfo paymentsInfo){
        long id = paymentsInfo.getId_user();
        double userlimit = getLimitById(id);
        if (userlimit - paymentsInfo.getAmount() >= 0){
            repository.save(new UserLimit(id, userlimit - paymentsInfo.getAmount()));
        } else {
            throw new LimitServiceException("Превышение дневного лимита");
        }
    }

    /* Восстановление лимита на сумму платежа */
    public void limitRollback(PaymentsInfo paymentsInfo){
        long id = paymentsInfo.getId_user();
        double userlimit = repository.findById(id).get().getLimit();
        repository.save(new UserLimit(id, userlimit + paymentsInfo.getAmount()));
    }

}
