package com.hsbc.userlogin.service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.hsbc.userlogin.domain.User;


@Repository
@Transactional
public class UserService {

	@PersistenceContext
	private EntityManager entityManager;

	
	public User getByUserName(String name) {
		try {
			return (User) entityManager.createQuery("from User where name = :name").setParameter("name", name)
					.getSingleResult();
		} catch (Exception e) {
		}
		return null;

	}


}
