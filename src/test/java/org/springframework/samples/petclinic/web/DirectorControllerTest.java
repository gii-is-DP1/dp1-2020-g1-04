package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.service.DirectorService;

@WebMvcTest(controllers= DirectorController.class)
public class DirectorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DirectorService directorService;

}
