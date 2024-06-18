package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping(path = "")
    public List<Product> getProducts(@RequestParam Optional<Integer> min, @RequestParam Optional<Integer> max) {
        var list = new ArrayList<Product>();

        if (min.isEmpty() && max.isEmpty()) {
            var sort = Sort.by(Sort.Order.asc("price"));
            list.addAll(productRepository.findAll(sort));
        } else if (min.isPresent() && max.isPresent()) {
            list.addAll(productRepository.findAllByPriceeee(min.get(), max.get()));
        } else {
            if (min.isPresent()) {
                list.addAll(productRepository.findByPriceGreaterThanOrderByPriceAsc(min.get()));
            }

            if (max.isPresent()) {
                list.addAll(productRepository.findByPriceLessThanOrderByPriceAsc(max.get()));
            }
        }

        return list;
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
