package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@WebMvcTest(EndpointsController.class)
public class EndpointsControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void testFlights() throws Exception {
        this.mvc.perform(
                get("/flights/flight")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.tickets[0].cost", is(200)))
                .andExpect(jsonPath("$.tickets[0].passenger.firstName", is("some name")));
    }

    @Test
    public void testFlights2() throws Exception {
        this.mvc.perform(
                get("/flights")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[1].departs", is("2017-04-21 14:34")));
    }

    @Test
    public void testPi() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.get("/math/pi");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void testCalc() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=add&x=4&y=6"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAdd() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=add&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }

    @Test
    public void testSubtract() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=subtract&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 - 6 = -2"));
    }

    @Test
    public void testMultiply() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=multiply&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 * 6 = 24"));
    }

    @Test
    public void testDivide() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=divide&x=30&y=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("30 / 5 = 6"));
    }
    @Test
    public void testEmpty() throws Exception {
        this.mvc.perform(get("/math/calculate?x=4&y=6"))
                .andExpect(content().string("4 + 6 = 10"));
    }

    @Test
    public void testSum() throws Exception {
        this.mvc.perform(post("/math/sum?n=4&n=5&n=6"))
                .andExpect(content().string("4 + 5 + 6 = 15"));
    }

    @Test
    public void testVolume() throws Exception {
        int length = 3;
        int width = 4;
        int height = 5;

        this.mvc.perform(get(String.format("/math/volume/%d/%d/%d", length, width, height)))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));

        this.mvc.perform(post(String.format("/math/volume/%d/%d/%d", length, width, height)))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));

        this.mvc.perform(patch(String.format("/math/volume/%d/%d/%d", length, width, height)))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));

        this.mvc.perform(put(String.format("/math/volume/%d/%d/%d", length, width, height)))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));

        this.mvc.perform(delete(String.format("/math/volume/%d/%d/%d", length, width, height)))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));

    }

    @Test
    public void testAreaCircle() throws Exception {
        double area;
        String type;

        MockHttpServletRequestBuilder request1 = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "circle")
                .param("radius", "5");

        area = (5^2) * Math.PI;
        area = Math.round(area);
        type = "circle";

        this.mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("The area of a %s with a radius of 5 is %.1f", type, area)));
    }

    @Test
    public void testAreaRectangle() throws Exception {
        int area;
        int width;
        int height;

        MockHttpServletRequestBuilder request1 = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "rectangle")
                .param("width", "5")
                .param("height", "4");

        width = 5;
        height = 4;
        area = width * height;

        this.mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("The area of a %dx%d rectangle is %d", width, height, area)));
    }

    @Test
    public void testAreaRectangleError() throws Exception {
        MockHttpServletRequestBuilder request1 = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "rectangle")
                .param("radius", "5");

        this.mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }

    @Test
    public void testAreaCircleError() throws Exception {
        MockHttpServletRequestBuilder request1 = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "circle")
                .param("width", "5")
                .param("height", "4");

        this.mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }
}
