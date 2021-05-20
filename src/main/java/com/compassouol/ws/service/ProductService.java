package com.compassouol.ws.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.compassouol.ws.model.Product;
import com.compassouol.ws.repository.ProductRepository;


@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	
	//Negocios
	public Collection<Product> findAll(){
		return productRepository.findAll(Sort.by("id"));
	}
	
	public Optional<Product> findById(Integer id) {
		return productRepository.findById(id);
	}
	
	public Product save(Product usuario) {
		return productRepository.save(usuario);
	}
	
	public void delete(Product usuario){
		productRepository.delete(usuario);
	}
	
	public List<Product> search(String q, Double min_price, Double max_price) {	
		Specification<Product> product = Specification
				.where(min_price == null ? null : priceGreaterThanOrEqual(min_price))
				.and(max_price == null ? null : lessGreaterThanOrEqual(max_price))
				.and(q == null ? null : like("name", q).or(like("description", q)));
		return productRepository.findAll(product);
	}
	
	public static Specification<Product> priceGreaterThanOrEqual(Double price) {
	    return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("price"), price);
	}
	
	public static Specification<Product> lessGreaterThanOrEqual(Double price) {
	    return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("price"), price);
	}
	
	public static Specification<Product> like(String column, String value) {
	    return (root, query, builder) -> builder.like(builder.upper(root.get(column)), "%"+value.toUpperCase()+"%");
	}
}
