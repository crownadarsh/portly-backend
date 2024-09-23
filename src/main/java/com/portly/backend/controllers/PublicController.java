package com.portly.backend.controllers;

import com.portly.backend.dto.output.PortfolioDto;
import com.portly.backend.services.impls.PortfolioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/p")
public class PublicController {

    private final PortfolioServiceImpl portfolioService;

    @GetMapping("/{username}")
    public ResponseEntity<PortfolioDto> getPortFolio(@PathVariable String username){
        return ResponseEntity.ok(portfolioService.getPortFolio(username));
    }

}
