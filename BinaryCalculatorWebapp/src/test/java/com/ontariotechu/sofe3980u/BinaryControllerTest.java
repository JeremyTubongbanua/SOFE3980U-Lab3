package com.ontariotechu.sofe3980u;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }

    @Test
    public void postParamter1() throws Exception { // 0 + 0 = 0
        this.mvc.perform(post("/").param("operand1","0").param("operator","+").param("operand2","0"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", "0"))
            .andExpect(model().attribute("operator", "+"))
            .andExpect(model().attribute("operand2", "0"));
    }

    @Test
    public void postParamter2() throws Exception { // 001 (1) + 001 (1) = 10 (2 in decimal)
        this.mvc.perform(post("/").param("operand1","1").param("operator","+").param("operand2","1"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "10"))
            .andExpect(model().attribute("operand1", "1"))
            .andExpect(model().attribute("operator", "+"))
            .andExpect(model().attribute("operand2", "1"));
    }

    @Test
    public void postParamter3() throws Exception { // 001 (1) + 001 (1) = 10 (2 in decimal)
        this.mvc.perform(post("/").param("operand1","001").param("operator","+").param("operand2","001"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "10"))
            .andExpect(model().attribute("operand1", "001"))
            .andExpect(model().attribute("operator", "+"))
            .andExpect(model().attribute("operand2", "001"));
    }

    @Test
    public void postMultiplication() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","*").param("operand2","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "110001"));
    }

    @Test
    public void postMultiplication2() throws Exception {
        this.mvc.perform(post("/").param("operand1","0").param("operator","*").param("operand2","0"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"));
    }

    @Test
    public void postAnd() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","&").param("operand2","010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "10"));
    }

    @Test
    public void postAnd2() throws Exception {
        this.mvc.perform(post("/").param("operand1","000").param("operator","&").param("operand2","000"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"));
    }

    @Test
    public void postOr() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","|").param("operand2","010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "111"));
    }

    @Test
    public void postOr2() throws Exception {
        this.mvc.perform(post("/").param("operand1","000").param("operator","|").param("operand2","000"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"));
    }

}