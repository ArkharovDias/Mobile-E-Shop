package com.springshell.eshop.service.impls;

import com.springshell.eshop.dao.ProductDao;
import com.springshell.eshop.domain.entity.Product;
import com.springshell.eshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public void create(Product entity) {
        productDao.create(entity);
    }

    @Override
    public void update(Product entity, Long id) {
        productDao.update(entity, id);
    }

    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }
}
