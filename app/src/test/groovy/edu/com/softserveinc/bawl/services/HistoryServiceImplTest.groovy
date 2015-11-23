package edu.com.softserveinc.bawl.services

import edu.com.softserveinc.bawl.models.HistoryModel
import edu.com.softserveinc.bawl.models.IssueModel
import edu.com.softserveinc.bawl.models.UserModel
import edu.com.softserveinc.bawl.models.enums.IssueStatus
import edu.com.softserveinc.bawl.models.enums.UserRole
import edu.com.softserveinc.bawl.services.HistoryService
import edu.com.softserveinc.bawl.services.IssueService
import edu.com.softserveinc.bawl.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = "classpath*:test-root-context.xml")
class HistoryServiceImplTest extends Specification {

  @Autowired
  HistoryService historyService;
  @Autowired
  IssueService issueService;
  @Autowired
  UserService userService;

  def "history creation"() {
    setup:
    UserModel user = new UserModel("name", "email@email.org", "login", UserRole.ADMIN.id, "password", null)
    user = userService.addUser(user)
    Authentication authentication = new UsernamePasswordAuthenticationToken("login", "password");
    SecurityContextHolder.getContext().setAuthentication(authentication)
    IssueModel issueModel = issueService.addIssue("name", "description", "mapPointer", "attachments", "category", 1)
    when:
    HistoryModel historyModel = new HistoryModel().withIssue(issueModel).withUserId(user.getId()).withStatus(IssueStatus.APPROVED)
    historyService.addHistory(historyModel)
    then:
    List<HistoryModel> hist = historyService.getLastUniqueIssues()
    assert hist.size() == 1
  }

}
