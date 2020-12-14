package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LessonControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    LessonRepository repository;

    @Test
    @Transactional
    @Rollback
    public void testCreate() throws Exception {
        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Spring 101\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class) ));
    }

    @Test
    @Transactional
    @Rollback
    public void testList() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setTitle("Spring 101");
        repository.save(lesson);
        long id = lesson.getId();

        MockHttpServletRequestBuilder request = get("/lessons/" + id)
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(lesson.getId().intValue())))
                .andExpect(jsonPath("$.title", equalTo(lesson.getTitle())));
    }
}