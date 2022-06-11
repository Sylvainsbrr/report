package com.mediscreen.report.proxy;

import com.mediscreen.report.entity.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "patient", url = "${patient.proxy.url}")
public interface PatientProxy {
    @RequestMapping(method = RequestMethod.GET, value = "/patient-info/{id}", produces = "application/json")
    Patient getPatientById(@PathVariable("id") int id);
}
