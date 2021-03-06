package com.example.demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RequestsResponsesDemoController.class)
public class RequestResponsesDemoTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    private MathService mathservice;
    @MockBean
    private ShapeService shapeService;

//////////////////////////Spring Math: Pi test case
    @Test
    public void testMathPi() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/pi");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    ////////////////////Spring Math: Calculate test cases
    @Test
    public void calculateAddQueryString() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/calculate?operation=add&x=4&y=6");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }
    @Test
    public void calculateMultiplyQueryString() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/calculate?operation=multiply&x=4&y=6");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 x 6 = 24"));
    }
    @Test
    public void calculateSubtractQueryString() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/calculate?operation=subtract&x=4&y=6");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 - 6 = -2"));
    }
    @Test
    public void calculateDivideQueryString() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/calculate?operation=divide&x=30&y=5");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("30 / 5 = 6"));
    }
    @Test
    public void calculateQueryStringWithoutOperationReturnSum() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/calculate?x=30&y=5");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("30 + 5 = 35"));
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
        when(mathservice.calculate("divide",30,5)).thenReturn("30 / 5 = 6");
    }

        ////////////////////////Spring Math:Volume with Path Variables test cases
    @Test
    public void testGetVolumeWithPathVariable() throws Exception {
        RequestBuilder request=MockMvcRequestBuilders.get("/math/volume/3/4/5");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));

    }
    @Test
    public void testPostVolumeWithPathVariable() throws Exception {
        RequestBuilder request=MockMvcRequestBuilders.post("/math/volume/3/4/5");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
    }
    @Test
    public void testPatchVolumeWithPathVariable() throws Exception {
        RequestBuilder request=MockMvcRequestBuilders.patch("/math/volume/6/7/8");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 6x7x8 rectangle is 336"));
    }
//////////Spring Math: test case for calculating area using form data
    @Test
    public void testAreaOfCircle() throws Exception {
        Shape shape = new Shape();
        shape.setType("circle");
        shape.setRadius(4.0);
        when(shapeService.calculateArea(shape)).thenReturn("Area of a circle with a radius of 4.0 is 50.26548245743669");
        RequestBuilder request=MockMvcRequestBuilders.patch("/math/volume/6/7/8");
        mvc.perform(((MockHttpServletRequestBuilder) request)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }

    @Test
    public void testAreaOfRectangle() throws Exception {
        Shape shape = new Shape();
        shape.setType("rectangle");
        shape.setWidth(5.0);
        shape.setHeight(5.0);
        when(shapeService.calculateArea(shape)).thenReturn("Area of a rectangle with a width of 5.0 and height of 5.0 is 25.0");
        RequestBuilder request=MockMvcRequestBuilders.patch("/math/volume/6/7/8");
        mvc.perform(((MockHttpServletRequestBuilder) request)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }

}
