package br.com.diegomarques.cryptoApp.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping
		public ResponseEntity post(@RequestBody Coin coin) {
		try {
			coin.setDateTime(new Timestamp(System.currentTimeMillis()));
			return new ResponseEntity<>(coinRepository.insert(coin),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

}
