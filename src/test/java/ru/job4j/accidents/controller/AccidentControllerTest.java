package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accidents.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class AccidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenViewCreateAccident() throws Exception {
        this.mockMvc.perform(get("/accidents/createForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("accident/createAccident"))
                .andExpect(model().attributeExists("user", "types", "rules"));
    }

    @Transactional
    @Test
    @WithMockUser
    public void whenViewUpdateAccident() throws Exception {
        this.mockMvc.perform(get("/accidents/updateForm?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("accident/editAccident"))
                .andExpect(model().attributeExists("user", "types", "rules", "accident"));
    }
}