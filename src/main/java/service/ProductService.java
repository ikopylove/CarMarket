package service;

import dao.ProductRepo;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService{

    @Autowired
    private ProductRepo productRepo;

    @Transactional
    public List<Product> getAllProducts() {
        return productRepo.getAllProducts();
    }

    @Transactional
    public void saveProduct(Product product) {
        productRepo.saveProduct(product);
    }

    @Transactional
    public Product getProduct(int id) {
        return productRepo.getProduct(id);
    }

    @Transactional
    public void deleteProduct(int id) {
        productRepo.deleteProduct(id);
    }
}
