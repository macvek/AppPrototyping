package appsprototyping.controllers;

import appsprototyping.model.SampleEntry;
import appsprototyping.repository.SampleEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class Welcome {
    public static final Logger log = LoggerFactory.getLogger(Welcome.class);


    @Autowired
    private SampleEntryRepository entryRepository;

    @GetMapping("/welcome")
    public String welcome() {
        log.info("Hit welcome");
        SampleEntry entry = new SampleEntry();
        entry.setValue("Hit Welcome on "+new Date());
        entryRepository.save(entry);
        return "Welcome";
    }
}
