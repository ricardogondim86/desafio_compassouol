package com.compassouol.ws.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.dto.ProductDTO;
import com.compassouol.ws.model.Product;
import com.compassouol.ws.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
	
	@Autowired
	ProductService productService;


	@PostMapping
	public ResponseEntity<Product> registerProduct(@RequestBody @Validated ProductDTO productDto) {
		
		Product product = new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		
		Product registeredProduct = productService.save(product);
		
		return new ResponseEntity<Product>(registeredProduct, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<Product> changeProduct(@RequestBody Product product, @PathVariable("id") Integer id) {
		
		Optional<Product> productDb = productService.findById(id);
		
		if (productDb==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		product.setId(id);
		
		productService.save(product);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value="{id}")
	public ResponseEntity<Product> findProductById(@PathVariable("id") Integer id) {
		
		Optional<Product> productDb = productService.findById(id);
		
		if (!productDb.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(productDb.get(), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Collection<Product>> findAllProducts() {
		
		Collection<Product> listProducts = productService.findAll();
		return new ResponseEntity<>(listProducts, HttpStatus.OK);
	}
	
	@GetMapping(path = "/search")
	public ResponseEntity<Collection<Product>> search(
			@RequestParam(required = false, name = "q") String q,
			@RequestParam(required = false, name = "min_price") Double min_price,
			@RequestParam(required = false, name = "max_price") Double max_price) {
		
		Collection<Product> listProducts = productService.search(q, min_price, max_price);
		return new ResponseEntity<>(listProducts, HttpStatus.OK);
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") Integer id) {
		
		Optional<Product> productDb = productService.findById(id);
		
		if (productDb==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		productService.delete(productDb.get());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
