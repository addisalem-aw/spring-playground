package com.example.demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RequestsResponsesDemoController.class)
public class RequestResponsesDemoTest {
//    @InjectMocks
//    MathService mathService;
    @Autowired
    MockMvc mvc;
    @MockBean
    private MathService mathservice;

    @Test
    public void testMathPi() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/pi");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }
    @Test
    public void calculateAddQueryString() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/calculate?operation=add&x=4&y=6");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4+6=10"));
    }
    @Test
    public void calculateMultiplyQueryString() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/calculate?operation=multiply&x=4&y=6");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4*6=24"));
    }
    @Test
    public void calculateSubtractQueryString() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/calculate?operation=subtract&x=4&y=6");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4-6=-2"));
    }
    @Test
    public void calculateDivideQueryString() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/calculate?operation=divide&x=30&y=5");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("30/5=6"));
    }
    @Test
    public void calculateQueryStringWithoutOperationReturnSum() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/calculate?x=30&y=5");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("30+5=35"));
    }
    @Test
    public void postSum() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.post("/math/sum?n=4&n=5&n=6");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4+5+6=15"));
    }
    @Test
    public void testMathServiceClassAdditionOperation() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/calculateuseservice?operation=divide&x=30&y=5");

        when(mathservice.calculate("divide",30,5)).thenReturn("30/5=6");
        this.mvc.perform(request).andExpect(status().isOk()); }

}
