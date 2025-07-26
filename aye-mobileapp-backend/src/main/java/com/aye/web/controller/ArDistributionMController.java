package com.aye.web.controller;


import com.aye.web.exception.GeneralExceptions;
import com.aye.web.service.ArDistributionMService;
import com.aye.web.model.ar.ArDistributionM;
import com.aye.web.model.common.ModuleType;
import com.aye.web.utill.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/distribution")
public class ArDistributionMController {

    private final ArDistributionMService arDistributionMService;


    @Autowired
    public ArDistributionMController(ArDistributionMService arDistributionMService) {
        this.arDistributionMService = arDistributionMService;
    }

    @PostMapping
    public ResponseEntity<?> saveDistribution(@RequestBody ArDistributionM arDistributionM, @RequestParam("module") ModuleType moduleType) {
        if (moduleType.equals(ModuleType.ORD)) {
            System.out.println("ORD");
        }
        if (moduleType.equals(ModuleType.C)) {
            System.out.println("Collection");
        }

        ArDistributionM distributionM = null;

        distributionM = arDistributionMService.saveArDistribution(arDistributionM);
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Distribution saved successfully", distributionM));
    }

    @GetMapping
    public ResponseEntity<?> getAllDistribution() {
        return ResponseEntity.ok(
                ResponseUtils.success(HttpStatus.OK.value(), "Distribution retrieved successfully", arDistributionMService.getAllDistribution()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getDistributionById(@PathVariable("id") String id) {
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Distribution retrieved successfully", arDistributionMService.findDistributionById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDistribution(@PathVariable("id") String id, @RequestBody ArDistributionM arDistributionM) {
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Distribution updated successfully", arDistributionMService.updateDistribution(arDistributionM)));
    }


}
