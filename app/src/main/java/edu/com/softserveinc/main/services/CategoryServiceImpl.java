package edu.com.softserveinc.main.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.com.softserveinc.main.dao.DaoImpl;
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
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		CategoryModel category = (CategoryModel) session.get(
				CategoryModel.class, id);
		session.close();
		return category;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List loadCategoriesList() {
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		return session.createQuery("FROM CategoryModel").list();
	}
	
	/**
	 * 
	 * @param issueCategory
	 * @return true if category exists in table if else - false
	 */
	public boolean isExixis(String categoryName) {

		boolean result = false;

		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		if (session.createQuery(
						"select 'name' FROM CategoryModel" )
				.list().size() == 0) {
			result = true;
			System.out.println("category isn't exist !!!!!!!!!");
		} else {
			result = false;

			System.out.println("category exists");
		}

		return result;
	}

	public CategoryModel getCategoryByName(String name){
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM CategoryModel WHERE name = :name ");
		query.setParameter("name", name);
		 CategoryModel categoryModel = null;
		for(int i=0; i<	query.list().size(); i++){
			 categoryModel = (CategoryModel)query.list().get(i);
			 if(categoryModel.getName()==name) {
				
				 break;
			 }
		}
		return categoryModel;
	}
}
