package com.ty.task_management.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.task_management.dto.Task;
import com.ty.task_management.dto.UserInfo;

public class TaskDao {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ankit");
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();
	private static EntityTransaction entityTransaction = entityManager.getTransaction();

	public Task saveUser(Task info) {
		entityTransaction.begin();
		info.setStatus("Active");
		entityManager.persist(info);
		entityTransaction.commit();
		return info;
	}

	public Task updateUser(int id, String status) {
		Task info2 = entityManager.find(Task.class, id);
		if (info2 != null) {
			entityTransaction.begin();
			info2.setStatus(status);
			entityManager.merge(info2);
			entityTransaction.commit();
			return info2;
		}
		return null;
	}

	public boolean deleteTask(int id) {
		Task info2 = entityManager.find(Task.class, id);
		if (info2 != null) {
			entityTransaction.begin();
			entityManager.remove(info2);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	public List<Task> findAllTask() {
		Query query = entityManager.createQuery("Select u from Userinfo u");
		List<Task> list = query.getResultList();
		if (list != null) {
			return list;
		}
		return null;

	}

	public UserInfo findTaskById(int id) {
		UserInfo info = entityManager.find(UserInfo.class, id);
		if (info != null) {
			return info;
		}
		return null;
	}
}
