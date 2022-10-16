package com.example.backendbase.manager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager/renter")
public class RenterController {

    @GetMapping("/building/{buildingId}")
    public ResponseEntity<?> getAllRenterWithFilter(@PathVariable(name = "buildingId") Long buildingId,
                                                    @RequestParam(name = "filter", defaultValue = "all") String filter) {
        return null;
    }

    @GetMapping("/building/{buildingId}/get-renter-by-id/{renterId}")
    public ResponseEntity<?> getRenterById(@PathVariable(name = "renterId") Long renterId,
                                           @PathVariable(name = "buildingId") Long buildingId) {
        return null;
    }

    @PostMapping("building/{buildingId}/add-new-renter")
    public ResponseEntity<?> addNewRenter(@PathVariable(name = "buildingId") Long buildingId) {
        return null;
    }

    @PutMapping("/identity/{identityId}")
    public ResponseEntity<?> updateRenterIdentity(@PathVariable(name = "identityId") Long renterId) {
        return null;
    }

    @PutMapping("/{buildingId}/{renterId}")
    public ResponseEntity<?> updateRenterInformation(@PathVariable(name = "renterId") Long renterId,
                                                     @PathVariable(name = "buildingId") Long buildingId) {
        return null;
    }


}
