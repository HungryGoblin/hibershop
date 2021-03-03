package shop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.repositories.CustomerRepository;
import shop.repositories.CustOrderRepository;
import shop.repositories.ProductRepository;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/shop")
public class ShopController {

    Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Resource(name = "customerRepository")
    private CustomerRepository customerRepository;

    @Resource(name = "productRepository")
    private ProductRepository productRepository;

    @Resource(name = "custOrderRepository")
    private CustOrderRepository custOrderRepository;

    @RequestMapping(method = GET)
    public String shop() {
        return "shop";
    }

    @RequestMapping("/customers")
    public String customers(Model model) {
        model.addAttribute("customers", customerRepository.getAll());
        return "customers";
    }

    @RequestMapping("/custorders")
    public String ordersByCustId(Model model,
                                 @RequestParam(required = false, name = "custid") Integer custId,
                                 @RequestParam(required = false, name = "productid") Integer productId) {
        if (custId == null) custId = 0;
        if (productId == null) productId = 0;
        if (productId == 0 && custId == 0)
            model.addAttribute("custorders", custOrderRepository.getAll());
        else
            model.addAttribute("custorders", custOrderRepository.getFilteredOrders(custId, productId));
        return "custorders";
    }

}
