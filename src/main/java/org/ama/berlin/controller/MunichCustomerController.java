package org.ama.berlin.controller;

import lombok.RequiredArgsConstructor;
import org.ama.berlin.dto.MunichCustomer;
import org.ama.berlin.service.MunichCustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class MunichCustomerController {
    private final MunichCustomerService munichCustomerService;

    @GetMapping("/getCustomers")
    public ResponseEntity<List<MunichCustomer>> getAllCustomers() {
        return ResponseEntity.ok(munichCustomerService.getAllCustomers());
    }
}
