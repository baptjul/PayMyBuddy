package com.PayMyBuddy.paymybuddy.controller;


import com.PayMyBuddy.paymybuddy.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addUserTest() throws Exception {
        User newUser = new User();
        newUser.setEmail("johndoe@gmail.com");
        newUser.setPassword("12345678910");
        newUser.setFirst_name("john");
        newUser.setLast_name("doe");
        newUser.setUser_name("johndoe1");
        mockMvc.perform(MockMvcRequestBuilders.post("/addUser").content(asJsonString(newUser)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getUsersTest() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk()).andDo(print());;
    }
}
