package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accidents.Application;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class AccidentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccidentService accidentService;

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
        when(accidentService.findById(1)).thenReturn(Optional.of(new Accident()));
        this.mockMvc.perform(get("/accidents/updateForm?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("accident/editAccident"))
                .andExpect(model().attributeExists("user", "types", "rules", "accident"));
    }

    @Test
    @WithMockUser
    public void whenSave() throws Exception {
        List<Integer> rIds = Arrays.asList(1, 2, 3);
        Accident accident = new Accident();
        accident.setNumber("123");
        this.mockMvc.perform(post("/accidents/create")
                        .flashAttr("accident", accident)
                        .param("rIds", "1")
                        .param("rIds", "2")
                        .param("rIds", "3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService, times(1)).save(argument.capture(), eq(rIds));
        assertThat(argument.getValue().getNumber()).isEqualTo("123");
    }

    @Test
    @WithMockUser
    public void whenUpdate() throws Exception {
        List<Integer> rIds = Arrays.asList(1, 2, 3);
        Accident accident = new Accident();
        accident.setNumber("123");
        this.mockMvc.perform(post("/accidents/update")
                        .flashAttr("accident", accident)
                        .param("rIds", "1")
                        .param("rIds", "2")
                        .param("rIds", "3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService, times(1)).update(argument.capture(), eq(rIds));
        assertThat(argument.getValue().getNumber()).isEqualTo("123");
    }
}