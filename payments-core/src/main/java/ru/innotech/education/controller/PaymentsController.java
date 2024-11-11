package ru.innotech.education.controller;

import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import ru.innotech.education.model.Product;
import ru.innotech.education.service.PaymentsInfo;
import ru.innotech.education.service.PaymentsService;

import java.util.List;

@RestController
public class PaymentsController {

    private final PaymentsService service;

    public PaymentsController(PaymentsService service) {
        this.service = service;
    }

    @GetMapping(value = "/getproducts/{id}")
    public List<Product> getAllUserProducts(@PathVariable long id) {
        return service.getProducts(id);
    }

    @PostMapping(value = "/pay")
    public void pay(RequestEntity<PaymentsInfo> request) throws Exception {
        service.pay(request.getBody());
    }
}
