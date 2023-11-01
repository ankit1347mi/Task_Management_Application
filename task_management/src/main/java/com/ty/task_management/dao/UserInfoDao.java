package com.ty.task_management.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.task_management.dto.Task;
import com.ty.task_management.dto.UserInfo;

public class UserInfoDao {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ankit");
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();
	private static EntityTransaction entityTransaction = entityManager.getTransaction();

	public UserInfo saveUser(UserInfo info) {
		entityTransaction.begin();
		info.setRole("Employee");
		entityManager.persist(info);
		entityTransaction.commit();
		return info;
	}

	public UserInfo updateUser(UserInfo info) {
		UserInfo info2 = entityManager.find(UserInfo.class, info.getId());
		if (info2 != null) {
			entityTransaction.begin();
			info2.setName(info.getName());
			info2.setRole(info.getRole());
			entityManager.merge(info2);
			entityTransaction.commit();
			return info2;
		}
		return null;
	}

	public boolean deleteUser(int id) {
		UserInfo info2 = entityManager.find(UserInfo.class, id);
		List<Task> list = info2.getTasks();
		if (info2 != null) {
			entityTransaction.begin();
			for (Task task : list) {
				entityManager.remove(task);
			}
			entityManager.remove(info2);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	public List<UserInfo> findAllUser() {
		Query query = entityManager.createQuery("Select u from UserInfo u where u.role=?1");
		query.setParameter(1, "Employee");
		List<UserInfo> list = query.getResultList();
		if (list != null) {
			return list;
		}
		return null;

	}

	public UserInfo findUserById(int id) {
		UserInfo info = entityManager.find(UserInfo.class, id);
		if (info != null) {
			return info;
		}
		return null;
	}

	public UserInfo findUserByEmail(String email) {
		Query query = entityManager.createQuery("select u from UserInfo u where u.email=?1");
		query.setParameter(1, email);
		UserInfo info = (UserInfo) query.getSingleResult();

		if (info != null) {
			return info;
		} else {
			return null;
		}
	}

}
