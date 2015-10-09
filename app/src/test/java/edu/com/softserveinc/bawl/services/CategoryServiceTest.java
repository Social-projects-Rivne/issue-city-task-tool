package edu.com.softserveinc.bawl.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.services.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration//(locations={"CategoryServiceTest-context.xml"})
public class CategoryServiceTest {

@Autowired
private CategoryService service; 
	
	@Test(expected = DataAccessException.class)
	public void whenAUniqueConstraintIsBroken_thenSpringSpecificExceptionIsThrown(){
	   String name = "Random category name";
	   service.addCategory(new CategoryModel(name));
	   service.addCategory(new CategoryModel(name));
	}

	
}
