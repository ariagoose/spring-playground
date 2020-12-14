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

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public void testUpdate() throws Exception {

        Lesson lesson = new Lesson();
        lesson.setTitle("Spring 101");
        repository.save(lesson);
        long id = lesson.getId();

        MockHttpServletRequestBuilder request = patch("/lessons/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Spring 201\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo(lesson.getTitle())));

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

    @Test
    @Transactional
    @Rollback
    public void testListByTitle() throws Exception{
        Lesson lesson = new Lesson();
        lesson.setTitle("Spring 101");
        repository.save(lesson);
        String title = lesson.getTitle();

        MockHttpServletRequestBuilder request = get("/lessons/find/" + title)
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(lesson.getId().intValue())))
                .andExpect(jsonPath("$.title", equalTo(lesson.getTitle())))
                .andExpect(jsonPath("$.deliveredOn", equalTo(lesson.getDeliveredOn())));
    }

    @Test
    @Transactional
    @Rollback
    public void testBetween() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setTitle("Daniel");
        lesson.setDeliveredOn(new Date(2020, Calendar.FEBRUARY, 25));
        repository.save(lesson);

        Lesson lesson2 = new Lesson();
        lesson2.setTitle("Tristan");
        lesson2.setDeliveredOn(new Date(2012, Calendar.JULY, 21));
        repository.save(lesson2);

        Lesson lesson3 = new Lesson();
        lesson3.setTitle("Hannah");
        lesson3.setDeliveredOn(new Date(2019, Calendar.MARCH, 9));
        repository.save(lesson3);

        Lesson lesson4 = new Lesson();
        lesson4.setTitle("Amelia");
        lesson4.setDeliveredOn(new Date(2014, Calendar.FEBRUARY, 19));
        repository.save(lesson4);

        Lesson lesson5 = new Lesson();
        lesson5.setTitle("Jamie");
        lesson5.setDeliveredOn(new Date(2017, Calendar.NOVEMBER, 13));
        repository.save(lesson5);

        MockHttpServletRequestBuilder request = get("/lessons/between?date1=2012-01-01&date2=2017-12-31")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo(lesson2.getTitle())));
        //.andExpect(jsonPath("$.title", equalTo(lesson2.getTitle())));
        //.andExpect(jsonPath("$.title", equalTo(lesson4.getTitle())))
        //.andExpect(jsonPath("$.title", equalTo(lesson5.getTitle())));
    }
}