package com.telran.bankingapp.controller;

import com.telran.bankingapp.dto.ManagerAfterCreateDTO;
import com.telran.bankingapp.dto.ManagerDTO;
import com.telran.bankingapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<ManagerAfterCreateDTO> createManager(@PathVariable("managerId") String managerId, @RequestBody ManagerAfterCreateDTO managerDto) {
        ManagerAfterCreateDTO createdManager = managerService.createManager(managerId, managerDto);
        return ResponseEntity.ok(createdManager);
    }

    @PostMapping("/new")
    public ResponseEntity<ManagerAfterCreateDTO> createManagerEmpty(@RequestBody ManagerAfterCreateDTO managerDto) {
        ManagerAfterCreateDTO createdManager = managerService.createManagerEmpty(managerDto);
        return ResponseEntity.ok(createdManager);
    }

}
