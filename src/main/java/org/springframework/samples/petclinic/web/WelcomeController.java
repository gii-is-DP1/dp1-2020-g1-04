package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	
		  
		  Integer i=12;
		  
		  List<Person> persons=new ArrayList<Person>();
		  Person person1= new Person();
		  person1.setNombre("JUAN");
		  person1.setApellidos("BUIZA NUÑEZ");
		  persons.add(person1);
		  
		  Person person2= new Person();
		  person2.setNombre("EDUARDO");
		  person2.setApellidos("CIEZAR LANZA");
		  persons.add(person2);
		  
		  Person person3= new Person();
		  person3.setNombre("RAFAEL");
		  person3.setApellidos("DIAZ GARCIA");
		  persons.add(person3);
		  
		  Person person4= new Person();
		  person4.setNombre("JOSE MANUEL");
		  person4.setApellidos("DURAN GARCIA");
		  persons.add(person4);
		  
		  Person person5= new Person();
		  person5.setNombre("YESICA LEYDI");
		  person5.setApellidos("GARATE FUENTES");
		  persons.add(person5);
		  
		  Person person6= new Person();
		  person6.setNombre("CARLOS");
		  person6.setApellidos("RUIZ BRIONES");
		  persons.add(person6);
		  
		  model.put("id", i);
		  model.put("persons", persons);
		  model.put("title", "AnimalsAdoption");
		  model.put("group", "G1-04");

	    return "welcome";
	  }
}
