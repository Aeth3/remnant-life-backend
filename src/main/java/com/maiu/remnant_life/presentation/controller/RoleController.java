package com.maiu.remnant_life.presentation.controller;

import com.maiu.remnant_life.application.service.RoleService;
import com.maiu.remnant_life.domain.model.Role;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }
    @PostMapping
    public Role create(@RequestBody Role role){
        return roleService.createRole(role);
    }
    @GetMapping
    public List<Role>  getAll(){
        return roleService.getRoles();
    }

}
