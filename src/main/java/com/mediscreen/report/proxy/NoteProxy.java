package com.mediscreen.report.proxy;


import com.mediscreen.report.entity.PatientNote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "note", url = "${note.proxy.url}")
public interface NoteProxy {
    @RequestMapping(method = RequestMethod.GET, value = "/patient-note/{id}", produces = "application/json")
    PatientNote getNoteById(@PathVariable("id") int id);
}
