package ru.innotech.education.controller;

import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import ru.innotech.education.dto.PaymentsInfo;
import ru.innotech.education.service.LimitService;

@RestController
public class LimitController {

    private final LimitService service;

    public LimitController(LimitService service) {
        this.service = service;
    }

   /* Получение лимита пользователя */
    @GetMapping(value = "/limit/{id}")
    public Long getUserLimit(@PathVariable long id) {
        return service.getLimitById(id);
    }

    /* Уменьшение лимита пользователя на сумму платежа */
    @PostMapping(value = "/limitReduction")
    public void limitReduction(@RequestBody PaymentsInfo request) throws Exception {
        service.limitReduction(request);
    }

    /* Восстановление лимита пользователя на сумму платежа */
    @PostMapping(value = "/rollback")
    public void limitRollback (@RequestBody PaymentsInfo request) throws Exception {
        service.limitRollback(request);
    }


    /* Изменение дневного лимита */
    @PostMapping(value = "/changeLimit")
    public void changeLimit(@PathVariable long limit) throws Exception {
        service.setLimit(limit);
    }

}
