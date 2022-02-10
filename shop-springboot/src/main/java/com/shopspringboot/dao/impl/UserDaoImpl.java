package com.shopspringboot.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopspringboot.dao.UserDao;
import com.shopspringboot.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void addUser(User user) {
		entityManager.persist(user);
	}

	@Override
	public void updateUser(User user) {
		entityManager.merge(user);
	}
	
	@Override
	public void updateUserNoChangePassword(User user) {		
		entityManager.merge(user);
	}

	@Override
	public void deleteUser(User user) {
		entityManager.remove(user);
	}

	@Override
	public User getUserById(int id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public List<User> getUserByName(String name) {
		return null;
	}
	
	@Override
	public User getUserByUsername(String username) {
		String jpql = "SELECT u FROM User u WHERE u.username = :name"; // làm việc với class Entity chứ không làm việc với bảng trong CSDL
		
		return entityManager.createQuery(jpql, User.class).setParameter("name", username).getSingleResult();
	}

	@Override
	public List<User> getAllUser() {
		String jql = "SELECT u from User u";
		
		return entityManager.createQuery(jql, User.class).getResultList();
	}

	@Override
	public long countUser() {
		String jql = "SELECT COUNT(*) from User";
//		String jql = "SELECT COUNT(u) from User u";
		
		return entityManager.createQuery(jql, Long.class).getSingleResult().longValue();
	}

	@Override
	public Map<String, Long> countByAddress() {
		String jql1 = "SELECT DISTINCT address FROM User";
		List<String> list = entityManager.createQuery(jql1).getResultList();
		
		Map<String, Long> map = new TreeMap<>();
		
		for (String address : list) {
			String jql2 = "SELECT COUNT(address)"
						+ "FROM User WHERE address = :address";
			
			long number_address = entityManager.createQuery(jql2, Long.class).setParameter("address", address).getSingleResult().longValue();
			
			map.put(address, number_address);
		}
		
		return map;
	}

	
}
