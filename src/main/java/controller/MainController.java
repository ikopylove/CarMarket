package controller;

import entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ProductService;
import service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/catalog")
    public String showAllProducts(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("allProducts", allProducts);
        return "catalog";
    }


    @RequestMapping("/add")
    public String addNewProduct(Model model)    {
        Product product = new Product();
        model.addAttribute("product", product);
        return "info";
    }

    @RequestMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult){
        productService.saveProduct(product);
        if (bindingResult.hasErrors()) {
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping("/edit")
    public String editProduct(@RequestParam("id") int id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "info";
    }

    @RequestMapping("/delete")
    public String deleteProduct(@RequestParam("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

}
