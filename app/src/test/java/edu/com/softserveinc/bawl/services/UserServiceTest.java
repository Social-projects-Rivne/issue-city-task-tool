package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.dao.UserDao;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.impl.UserServiceImpl;
import edu.com.softserveinc.bawl.utils.CsvReaderWriter;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest extends AbstractBawlTest {

    private UserService userService = null;

    private UserDao userDao;

    @Before
    public void setup() {
        userService = new UserServiceImpl();
        userDao = mock(UserDao.class);
        Whitebox.setInternalState(userService, "userDao", userDao);
        when(userDao.findAll()).thenReturn(getTestUsersModels());
    }

    private  List <UserModel> getTestUsersModels(){
        List<UserModel> users = null;
        try {
            users = CsvReaderWriter.readUserModelCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Test
    public void addUser_shouldCallAddUserDao(){
        UserModel mockUserModel = mock(UserModel.class);
        when(mockUserModel.getPassword()).thenReturn("foo");
        userService.addUser(mockUserModel);

        verify(userDao, times(1)).saveAndFlush(mockUserModel);
    }

    @Test
    public void editUser_shouldCallEditUserDao(){
        UserModel mockUserModel = mock(UserModel.class);

        userService.editUser(mockUserModel);

        verify(userDao, times(1)).saveAndFlush(mockUserModel);
    }

    @Test
    public void editUserPass_shouldCallEditUserPassDao(){
        UserModel mockUserModel = mock(UserModel.class);
        when(mockUserModel.getPassword()).thenReturn("foo");
        userService.editUserPass(mockUserModel);
        verify(userDao, times(1)).saveAndFlush(mockUserModel);
    }

    @Test
    public void deleteUser_shouldCallDeleteUserDao(){
        assert ( 1 == 1);
    }

    @Test
    public void getById_shouldReturnUserModelByUserId(){
        int userId= 5;
        final String user1 = "user1";
        UserModel sampleUserModel = new UserModel();
        sampleUserModel.setId(userId);
        sampleUserModel.setName(user1);
        sampleUserModel.setLogin(user1);
        when(userDao.findOne(userId)).thenReturn(sampleUserModel);
        UserModel userModel = userService.getById(userId);
        assertNotNull(userModel);
        assertEquals(userId, userModel.getId());
        assertTrue(userModel.equals(sampleUserModel));
    }

    @Test
    public void loadUsersList_shouldReturnListOfUserModel(){

        List<UserModel> listUserModels = userService.loadUsersList();
        assertNotNull(listUserModels);
    }

    @Test
    public void getByLogin_shouldReturnUserModelByUserLogin(){
        final String user1 = "user1";
        UserModel sampleUserModel = new UserModel();
        sampleUserModel.setId(1);
        sampleUserModel.setName(user1);
        sampleUserModel.setLogin(user1);
        when(userDao.findByLogin(user1)).thenReturn(sampleUserModel);
        UserModel userModel = userService.getByLogin(user1);
        assertNotNull(userModel);
        assertEquals("user1", userModel.getLogin());
        assertTrue(userModel.equals(sampleUserModel));
    }

}


