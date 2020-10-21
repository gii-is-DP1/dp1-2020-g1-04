package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	
		  
		  List<Person> persons=new ArrayList<Person>();
		  Person person1= new Person();
		  person1.setFirstName("JUAN");
		  person1.setLastName("BUIZA NUÑEZ");
		  persons.add(person1);
		  
		  Person person2= new Person();
		  person2.setFirstName("EDUARDO");
		  person2.setLastName("CIEZAR LANZA");
		  persons.add(person2);
		  
		  Person person3= new Person();
		  person3.setFirstName("RAFAEL");
		  person3.setLastName("DIAZ GARCIA");
		  persons.add(person3);
		  
		  Person person4= new Person();
		  person4.setFirstName("JOSE MANUEL");
		  person4.setLastName("DURAN GARCIA");
		  persons.add(person4);
		  
		  Person person5= new Person();
		  person5.setFirstName("YESICA LEYDI");
		  person5.setLastName("GARATE FUENTES");
		  persons.add(person5);
		  
		  Person person6= new Person();
		  person6.setFirstName("CARLOS");
		  person6.setLastName("RUIZ BRIONES");
		  persons.add(person6);
		  
		  model.put("persons", persons);
		  model.put("title", "Nuevo Titulo");
		  model.put("group", "G1-04");

	    return "welcome";
	  }
}
