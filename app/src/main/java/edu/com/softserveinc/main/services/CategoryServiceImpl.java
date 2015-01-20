package edu.com.softserveinc.main.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.com.softserveinc.main.dao.DaoImpl;
import edu.com.softserveinc.main.interfaces.CategoryService;
import edu.com.softserveinc.main.models.CategoryModel;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public void addCategory(CategoryModel category) {
		new DaoImpl().addInDB(category);
	}

	@Override
	public void deleteCategory(CategoryModel category) {
		new DaoImpl().deleteFromDB(category);
	}

	@Override
	public void editCategory(CategoryModel category) {
		new DaoImpl().editInDB(category);
	}

	@Override
	public CategoryModel getCategoryByID(int id) {
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		CategoryModel category = (CategoryModel)session.get(CategoryModel.class, id);
		session.close();
		return category;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List loadCategoriesList() {
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		return session.createQuery("FROM CategoryModel").list();
	}

}
