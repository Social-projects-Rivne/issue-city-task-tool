package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.AbstractBawlIntegrationTest;
import edu.com.softserveinc.bawl.dto.pojo.UserDTO;
import edu.com.softserveinc.bawl.models.enums.UserRole;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

public class UserControllerIntegrationTest extends AbstractBawlIntegrationTest {

    public static final  String MEDIA_TYPE = "application/json;charset=UTF-8";
    public static final String EMPTY_COLLECTION = "[]";
    public static final int USER_ID = 1;

    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Before
    public void setup() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("admin", "admin");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
    }


    @Test
    public void userController_getUserIssuesHistories() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("admin@admin");
        userDTO.setName("admin");
        userDTO.setLogin("admin");
        userDTO.setPassword("admin");
        userDTO.setAvatar("_");
        userDTO.setRoleId(UserRole.ADMIN.getId());
        final String s = mapper.writeValueAsString(userDTO);
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).body(s.getBytes())).andExpect(status().isOk());
        mockMvc.perform(get(String.format("/users/user/history", USER_ID)))
                .andExpect(status().isOk())
                .andExpect(content().mimeType(MEDIA_TYPE));
    }
}
