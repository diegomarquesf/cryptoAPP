package br.com.diegomarques.cryptoApp.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegomarques.cryptoApp.domain.Coin;
import br.com.diegomarques.cryptoApp.repositories.CoinRepository;

@RestController
@RequestMapping(value="/coin")
public class CoinController {
	
	@Autowired
	private CoinRepository coinRepository;
	
	@Bean
	public Coin init() {
		Coin coin = new Coin();
		coin.setName("BITCOIN");
		coin.setPrice(new BigDecimal(100));
		coin.setQuantity(new BigDecimal(0.0005));
		coin.setDateTime(new Timestamp(System.currentTimeMillis()));
		
		Coin coin2 = new Coin();
		coin2.setName("BITCOIN");
		coin2.setPrice(new BigDecimal(100));
		coin2.setQuantity(new BigDecimal(0.0025));
		coin2.setDateTime(new Timestamp(System.currentTimeMillis()));
		
		Coin coin3 = new Coin();
		coin3.setName("DOGECOIN");
		coin3.setPrice(new BigDecimal(1150));
		coin3.setQuantity(new BigDecimal(0.0000015));
		coin3.setDateTime(new Timestamp(System.currentTimeMillis()));
		
		coinRepository.insert(coin);
		coinRepository.insert(coin2);
		coinRepository.insert(coin3);
		
		return coin;
	}
	
	@PostMapping
		public ResponseEntity post(@RequestBody Coin coin) {
		try {
			coin.setDateTime(new Timestamp(System.currentTimeMillis()));
			return new ResponseEntity<>(coinRepository.insert(coin),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping
	public ResponseEntity get() {
		return new ResponseEntity<>(coinRepository.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity getName(@PathVariable String name) {
		try {
			return new ResponseEntity<>(coinRepository.getByName(name), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable int id) {
		try {
			return new ResponseEntity<>(coinRepository.delete(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
		}
	}
	}
