package com.nithi.restapi.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	// static filter

//	@GetMapping("/filtering")
//	public SomeBean filtering() {
//		
//		return new SomeBean("v1","v2","v3");	
//	}
//	
//	
//	@GetMapping("/filtering-list")
//	public List<SomeBean> filteringList() {
//		
//		return Arrays.asList(new SomeBean("v1","v2","v3"),
//				new SomeBean("v4","v5","v6"));
//				
//	}

	// dynamic filter
	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {

		SomeBean someBean = new SomeBean("v1", "v2", "v3");

		MappingJacksonValue mjv = new MappingJacksonValue(someBean);

		SimpleBeanPropertyFilter filters = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filter = new SimpleFilterProvider().addFilter("dynamicFilterForSomeBean", filters);
		mjv.setFilters(filter);
		return mjv;
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringList() {

		List<SomeBean> list = Arrays.asList(new SomeBean("v1", "v2", "v3"), new SomeBean("v4", "v5", "v6"));

		MappingJacksonValue mjv = new MappingJacksonValue(list);

		SimpleBeanPropertyFilter filters = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		FilterProvider filter = new SimpleFilterProvider().addFilter("dynamicFilterForSomeBean", filters);
		mjv.setFilters(filter);

		return mjv;

	}

}
