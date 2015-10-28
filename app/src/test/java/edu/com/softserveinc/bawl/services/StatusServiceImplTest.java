package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.dto.StatusDTO;
import edu.com.softserveinc.bawl.models.IssueStatus;
import edu.com.softserveinc.bawl.services.impl.StatusServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class StatusServiceImplTest extends AbstractBawlTest {

    @Mock private StatusServiceImpl statusService = mock(StatusServiceImpl.class);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(statusService.loadStatusList()).thenCallRealMethod();
    }


    @Test
    public void loadStatusList_shouldReturnListOfAllStatuses() {
        final List<StatusDTO> issueStatuses = statusService.loadStatusList();
        assertEquals(issueStatuses.get(0).getName(),IssueStatus.values()[0].name());
    }

}

