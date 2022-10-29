package appsprototyping.controllers;

import appsprototyping.model.SampleEntry;
import appsprototyping.repository.SampleEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class Welcome {

    @Autowired
    private SampleEntryRepository entryRepository;

    @GetMapping("/welcome")
    public String welcome() {
        SampleEntry entry = new SampleEntry();
        entry.setValue("Hit Welcome on "+new Date());
        entryRepository.save(entry);
        return "Welcome";
    }
}
