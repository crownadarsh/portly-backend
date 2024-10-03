package com.portly.backend.controllers;

import com.portly.backend.dto.output.PortfolioDto;
import com.portly.backend.services.impls.PortfolioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/p")
@CrossOrigin
public class PublicController {

    private final PortfolioServiceImpl portfolioService;

    @GetMapping("/{username}")
    public ResponseEntity<PortfolioDto> getPortFolio(@PathVariable String username){
        return ResponseEntity.ok(portfolioService.getPortFolio(username));
    }

}
