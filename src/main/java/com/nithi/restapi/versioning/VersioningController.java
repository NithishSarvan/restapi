package com.nithi.restapi.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

	// url versioning
	@GetMapping("/v1/person")
	public PersonV1 getUrlPersonV1() {
		return new PersonV1("Nithish Sarvan");
	}

	@GetMapping("/v2/person")
	public PersonV2 getUrlPersonV2() {
		return new PersonV2(new Name("Nithis", "Sarvan"));
	}

	// request param
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getParamPersonV1() {
		return new PersonV1("Nithish Sarvan");
	}

	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getParamPersonV2() {
		return new PersonV2(new Name("Nithis", "Sarvan"));
	}

	// headers
	@GetMapping(path = "/person/header", headers ="version=1")
	public PersonV1 getHeaderPersonV1() {
		return new PersonV1("Nithish Sarvan");
	}

	@GetMapping(path = "/person/header", headers = "version=2")
	public PersonV2 getHeaderPersonV2() {
		return new PersonV2(new Name("Nithis", "Sarvan"));
	}
	
	@GetMapping(path = "/person/accept", produces  ="application/vnd.company.app-v1+json")
	public PersonV1 getMediaPersonV1() {
		return new PersonV1("Nithish Sarvan");
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getMediaPersonV2() {
		return new PersonV2(new Name("Nithis", "Sarvan"));
	}


}
