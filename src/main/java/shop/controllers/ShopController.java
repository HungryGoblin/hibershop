package shop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import shop.repositories.CustomerRepository;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/shop")
public class ShopController {

    Logger logger = LoggerFactory.getLogger(ShopController.class);

//    CustomerRepository customerRepository;

//    @PostConstruct
//    public void init() {
//        customerRepository = new CustomerRepository();
//    }

    @RequestMapping(method = RequestMethod.GET)
    public String shop() {
        return "shop";
    }

    @RequestMapping("/customers")
    public String customers() {
        return "customers";
    }

}
