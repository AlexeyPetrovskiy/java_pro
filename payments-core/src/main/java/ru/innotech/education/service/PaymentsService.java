package ru.innotech.education.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.innotech.education.model.Product;

import java.util.List;

@Service
public class PaymentsService {

    private final RestTemplate paymentClient;

    public PaymentsService(RestTemplate paymentClient) {
        this.paymentClient = paymentClient;
    }

    public List<Product> getProducts(Long id) {
            return paymentClient.getForObject("/userproducts/" + id, List.class);
    }

    public void pay(PaymentsInfo paymentsInfo) {
        Product[] list = paymentClient.getForObject("/userproducts/" + paymentsInfo.getClientId(), Product[].class);
        if (list.length == 0) {
            throw new PaymentsServiceException("У клиента не найдено продуктов");
        }
        boolean flag = false;
        for (Product product: list){
            if (product.getId() == paymentsInfo.getProductId()){
                flag = Boolean.TRUE;
                if (product.getBalance() - paymentsInfo.getAmount() < 0){
                    throw new PaymentsServiceException("Недостаточно средств");
                }
            }
        }
        
        if (!flag){
            throw new PaymentsServiceException("Некорректно указан продукт клиента");
        }
        
    }
}
