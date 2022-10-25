package br.com.diegomarques.cryptoApp.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Repository;

import br.com.diegomarques.cryptoApp.domain.Coin;
import br.com.diegomarques.cryptoApp.dto.CoinDTO;

@Repository
@EnableAutoConfiguration
public class CoinRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Transactional
	public Coin insert(Coin coin) {
		entityManager.persist(coin);
		return coin;
	}
	
	public List<CoinDTO> getAll(){
		String jpql = "select new br.com.diegomarques.cryptoApp.dto.CoinDTO(c.name, sum(c.quantity)) from Coin c group by c.name";
		TypedQuery<CoinDTO> query = entityManager.createQuery(jpql, CoinDTO.class);
		
		return query.getResultList();
	}
	

	public List<Coin> getByName(String name){
		String jpql = "select c from Coin c where c.name Like :name";
		TypedQuery<Coin> query = entityManager.createQuery(jpql, Coin.class);
		query.setParameter("name", "%" + name + "%");
		
		return query.getResultList();
	
	}
	
	@Transactional
	public boolean delete(int id) {
		Coin coin = entityManager.find(Coin.class, id);
		if (entityManager.contains(coin)) {
			coin = entityManager.merge(coin);
		}
		
		entityManager.remove(coin);
		return true;
	}
	
	@Transactional
	public Coin update(Coin coin) {
		entityManager.merge(coin);
		return coin;
	}

}
