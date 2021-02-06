package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.CentroDeAdopcion;
import org.springframework.samples.petclinic.service.CentroDeAdopcionService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = CentroDeAdopcionController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class CentroDeAdopcionControllerTest {
	
	private static final int TEST_CENTRO_ID = 1;

	@MockBean
	private CentroDeAdopcionService centroDeAdopcionService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		given(this.centroDeAdopcionService.findById(TEST_CENTRO_ID)).willReturn(new CentroDeAdopcion());
	}
	
	
	//HU-6
	@WithMockUser(value = "spring")
      @Test
	void testFindCentros() throws Exception {
		mockMvc.perform(get("/centros",TEST_CENTRO_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("centros"))
				.andExpect(view().name("centrosDeAdopcion/centrosListing"));
	}

}
