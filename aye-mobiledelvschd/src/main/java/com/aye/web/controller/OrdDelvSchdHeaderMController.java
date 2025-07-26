package com.aye.web.controller;

import com.aye.web.mobileBase.exception.GeneralExceptions;
import com.aye.web.mobileBase.model.schedule.OrdDelvScheduleHeaderM;
import com.aye.web.mobileBase.model.schedule.OrdDelvScheduleLineM;
import com.aye.web.service.OrdDelvSchdHeaderMService;
import com.aye.web.service.OrdDelvSchdLineMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrdDelvSchdHeaderMController {
    private final OrdDelvSchdHeaderMService ordDelvSchdHeaderMService;
    private final OrdDelvSchdLineMService ordDelvSchdLineMService;

    @Autowired
    public OrdDelvSchdHeaderMController(OrdDelvSchdHeaderMService ordDelvSchdHeaderMService, OrdDelvSchdLineMService ordDelvSchdLineMService) {
        this.ordDelvSchdHeaderMService = ordDelvSchdHeaderMService;
        this.ordDelvSchdLineMService = ordDelvSchdLineMService;
    }

    @PostMapping("/saveSchdHeader")
    public ResponseEntity<?> saveOrdDelvSchdHeader(@RequestBody OrdDelvScheduleHeaderM ordDelvScheduleHeaderM){
        try {
            return ResponseEntity.ok(ordDelvSchdHeaderMService.saveDlvSchdHeader(ordDelvScheduleHeaderM));
        } catch (GeneralExceptions e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllSchdHeader")
    public ResponseEntity<?> getAllSchdHeader(){
        return ResponseEntity.ok(ordDelvSchdHeaderMService.getAllDelvSchdHeader());
    }

    @GetMapping("/getSchdHeaderById/{id}")
    public ResponseEntity<?> getSchdHeaderById(@PathVariable("id") String id){
        return ResponseEntity.ok(ordDelvSchdHeaderMService.getDelvSchdHeaderById(id));
    }

    @PutMapping("/updateSchdHeader/{id}")
    public ResponseEntity<?> updateDelvSchdHeader(@PathVariable("id") String id, @RequestBody OrdDelvScheduleHeaderM ordDelvScheduleHeaderM){
        return ResponseEntity.ok(ordDelvSchdHeaderMService.updateDlvSchdHeader(ordDelvScheduleHeaderM));
    }

    @PostMapping("/saveSchdLine")
    public ResponseEntity<?> saveOrdDelvSchdLine(@RequestBody OrdDelvScheduleLineM ordDelvScheduleLineM) {
        try {
            return ResponseEntity.ok(ordDelvSchdLineMService.saveDlvSchdLine(ordDelvScheduleLineM));
        } catch (GeneralExceptions e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllSchdLine")
    public ResponseEntity<?> getAllDelvSchdLine(){
        return ResponseEntity.ok(ordDelvSchdLineMService.getAllDelvSchdLine());
    }

    @GetMapping("/getSchdLineById/{id}")
    public ResponseEntity<?> getSchdLineById(@PathVariable("id") String id){
        return ResponseEntity.ok(ordDelvSchdLineMService.getDelvSchdLineById(id));
    }

    @PutMapping("/updateSchdLine/{id}")
    public ResponseEntity<?> updateDelvSchdLine(@PathVariable("id") String id, @RequestBody OrdDelvScheduleLineM ordDelvScheduleLineM){
        return ResponseEntity.ok(ordDelvSchdLineMService.updateDlvSchdLine(ordDelvScheduleLineM));
    }





}
