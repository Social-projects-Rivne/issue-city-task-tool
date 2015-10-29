package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.dao.SubscriptionDao;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.services.impl.SubscriptionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SubscriptionServiceTest extends AbstractBawlTest {


    private SubscriptionService subscriptionService;
    private SubscriptionDao subscriptionDao;

    @Before
    public void setup() {
        subscriptionService = new SubscriptionServiceImpl();
        subscriptionDao = mock(SubscriptionDao.class);
        Whitebox.setInternalState(subscriptionService, "SubscriptionDao",subscriptionDao);
    }

    @Test
    public void testCreate() throws Exception {
        SubscriptionModel mockSubscriptionModel = mock(SubscriptionModel.class);
        subscriptionService.create(mockSubscriptionModel);
        verify(subscriptionDao,times(1)).saveAndFlush(mockSubscriptionModel);
    }

    @Test
    public void testRead() throws Exception {


    }


//
//    @Test
//    public void testRead1() throws Exception {
//        SubscriptionModel subscriptionModel = new SubscriptionModel();
//        int IssueId = 4;
//        subscriptionModel.setIssueId(IssueId);
//        when(subscriptionDao.findByIssueId(IssueId)).thenReturn(Collections.singleton(subscriptionModel));
//        List<SubscriptionModel> actualModel = (List<SubscriptionModel>) subscriptionService.listByIssueId(IssueId);
//        SubscriptionModel expectedModel = new SubscriptionModel();
//        expectedModel.setId(IssueId);
//        verify(subscriptionDao).findByIssueId(IssueId);
//        assertEquals(Collections.singleton(expectedModel),(actualModel));
//    }

    @Test
    public void testDelete() throws Exception {

        SubscriptionModel mockSubscriptionModel = mock(SubscriptionModel.class);
  //      subscriptionService.delete(mockSubscriptionModel);
        verify(subscriptionDao,times(1)).saveAndFlush(mockSubscriptionModel);
    }


    @Test
    public void testListByIssueId() throws Exception {

        int IssueId = 2;

        Collection <SubscriptionModel> findSubscription = subscriptionService.listByIssueId(IssueId);

        for(SubscriptionModel model :findSubscription){
            assertEquals(IssueId, model.getIssueId());
        }

    }


}