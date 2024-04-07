package com.boa.crs.restclient;


import java.io.IOException;
import java.util.Collections;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.boa.crs.discovery.Discovery;

import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping("/Consumer")
@CrossOrigin
public class ConsumerController {

private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);
	
	/**
	 * Discovery class to handle the communication with server and produceer client
	 */
	@Autowired
	Discovery discoveryClass;
	
	/**
	 * @return
	 * @throws RestClientException
	 * @throws IOException
	 * Connects with producer to fetch all student details
	 */
	@RequestMapping(value = "/getEnrolledStudent", produces = "plain/text", method = RequestMethod.GET)
	public ResponseEntity<String> getEnrolledStudent() throws RestClientException, IOException {
		logger.info("Inside getEnrolledStudent method.");
		return discoveryClass.discoveryResult("boa-CRS-ProfessorProducer","/Professor/getEnrolledStudent", HttpMethod.GET);
	}
	
	/**
	 * @param courseMap
	 * @return
	 * @throws RestClientException
	 * @throws IOException
	 * Send the course details to the producer to store in database
	 */
	@RequestMapping(value = "/addCourse", produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST)
	public ResponseEntity<String> addCourse(@RequestBody Map<String,Object> courseMap) throws RestClientException, IOException {
		logger.info("Inside addCourse method.");
		return discoveryClass.discoveryResult("boa-CRS-AdminProducer","/Admin/addCourse", HttpMethod.POST,courseMap);
	}
	
	/**
	 * @param courseId
	 * @param courseName
	 * @return
	 * @throws RestClientException
	 * @throws IOException
	 * Send the course details to the producer to delete from database
	 */
	@RequestMapping(value = "/removeCourse/{id}", produces = MediaType.APPLICATION_JSON, method = RequestMethod.DELETE)
	public ResponseEntity<String> removeCourse(@PathVariable Long id) throws RestClientException, IOException {
		logger.info("Inside deleteCourse method.");
		return discoveryClass.discoveryResult("boa-CRS-AdminProducer","/Admin/removeCourse/{id}", HttpMethod.DELETE, Collections.singletonMap("id", id));
	}
}
