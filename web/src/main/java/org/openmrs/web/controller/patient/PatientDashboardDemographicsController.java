/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.web.controller.patient;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.PersonAddress;
import org.openmrs.PersonName;
import org.openmrs.module.web.extension.provider.Link;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Controller
public class PatientDashboardDemographicsController {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * render the patient dashboard model and direct to the view
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/patientDashboardDemographics.form")
	protected String renderDashboard(@RequestParam(required = true, value = "patientId") Integer patientId, ModelMap map)
	        throws Exception {
		Patient patient = (Patient) RequestContextHolder.currentRequestAttributes().getAttribute(
		    WebConstants.AJAX_DASHBOARD_PATIENT + patientId, RequestAttributes.SCOPE_SESSION);
		String patientVariation = (String) RequestContextHolder.currentRequestAttributes().getAttribute(
		    WebConstants.AJAX_DASHBOARD_PATIENT_VARIATION + patientId, RequestAttributes.SCOPE_SESSION);
		PatientIdentifier identifier = (PatientIdentifier) RequestContextHolder.currentRequestAttributes().getAttribute(
		    WebConstants.AJAX_DASHBOARD_IDENTIFIER + patientId, RequestAttributes.SCOPE_SESSION);
		PersonName name = (PersonName) RequestContextHolder.currentRequestAttributes().getAttribute(
		    WebConstants.AJAX_DASHBOARD_NAME + patientId, RequestAttributes.SCOPE_SESSION);
		PersonAddress address = (PersonAddress) RequestContextHolder.currentRequestAttributes().getAttribute(
		    WebConstants.AJAX_DASHBOARD_ADDRESS + patientId, RequestAttributes.SCOPE_SESSION);
		String causeOfDeath = (String) RequestContextHolder.currentRequestAttributes().getAttribute(
		    WebConstants.AJAX_DASHBOARD_CAUSE_OF_DEATH + patientId, RequestAttributes.SCOPE_SESSION);
		Set<Link> links = (Set<Link>) RequestContextHolder.currentRequestAttributes().getAttribute(
		    WebConstants.AJAX_DASHBOARD_ADD_ENCOUNTER_TO_VISIT_LINKS + patientId, RequestAttributes.SCOPE_SESSION);
		
		map.put("patient", patient);
		map.put("patientVariation", patientVariation);
		map.put("emptyIdentifier", identifier);
		map.put("emptyName", name);
		map.put("emptyAddress", address);
		map.put("causeOfDeathOther", causeOfDeath);
		map.put("allAddEncounterToVisitLinks", links);
		
		return "patientDashboardDemographicsForm";
	}
	
}
