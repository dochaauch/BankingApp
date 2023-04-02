package com.telran.bankingapp.controller;

import com.telran.bankingapp.dto.ManagerDTO;
import com.telran.bankingapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/managers")
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping("")
    public List<ManagerDTO> getAllManagers() {
        return managerService.getAllManagers();
    }


    @GetMapping("/{uuid}")
    public ManagerDTO getById(@PathVariable String uuid) {
        return managerService.getManagerById(uuid);
    }


    @PostMapping("/new/{managerId}")
    public ResponseEntity<ManagerDTO> createManager(@PathVariable("managerId") String managerId, @RequestBody ManagerDTO managerDto) {
        ManagerDTO createdManager = managerService.createManager(UUID.fromString(managerId), managerDto);
        return ResponseEntity.ok(createdManager);
    }

    @PostMapping("/new")
    public ResponseEntity<ManagerDTO> createManagerEmpty(@RequestBody ManagerDTO managerDto) {
        ManagerDTO createdManager = managerService.createManagerEmpty(managerDto);
        return ResponseEntity.ok(createdManager);
    }

}
