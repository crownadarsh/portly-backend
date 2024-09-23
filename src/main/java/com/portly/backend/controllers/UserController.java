package com.portly.backend.controllers;

import com.portly.backend.dto.*;
import com.portly.backend.services.impls.PortfolioServiceImpl;
import com.portly.backend.services.impls.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;
    private final PortfolioServiceImpl portfolioService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getMyProfile(){
        return ResponseEntity.ok(userService.getMyProfile());
    }

    @PutMapping("/editProfile")
    public ResponseEntity<UserDto> editProfile(){
        return ResponseEntity.ok(userService.editProfile());
    }

    @PutMapping("/edit/achievement")
    public ResponseEntity<AchievementDto> editAchievementSection(@RequestBody AchievementDto achievementDto){
        return ResponseEntity.ok(portfolioService.editAchievementSection(achievementDto));
    }

    @PutMapping("/edit/header")
    public ResponseEntity<HeaderDto> editHeaderSection(@RequestBody HeaderDto headerDto){
        return ResponseEntity.ok(portfolioService.editHeaderSection(headerDto));
    }

    @PutMapping("/edit/appBar")
    public ResponseEntity<AppBarDto> editAppBarSection(@RequestBody AppBarDto appBarDto){
        return ResponseEntity.ok(portfolioService.editAppBarSection(appBarDto));
    }

    @PutMapping("/edit/project")
    public ResponseEntity<ProjectSectionDto> editProjectSection(@RequestBody ProjectSectionDto projectSectionDto){
        return ResponseEntity.ok(portfolioService.editProjectSection(projectSectionDto));
    }

    @PutMapping("/edit/contact")
    public ResponseEntity<ContactDto> editContactSection(@RequestBody ContactDto contactDto){
        return ResponseEntity.ok(portfolioService.editContactSection(contactDto));
    }

    @PutMapping("/edit/skill")
    public ResponseEntity<SkillDto> editSkillSection(@RequestBody SkillDto skillDto){
        return ResponseEntity.ok(portfolioService.editSkillSection(skillDto));
    }

    @PutMapping("/edit/workExperience")
    public ResponseEntity<WorkExperienceDto> editWorkExperienceSection(@RequestBody WorkExperienceDto workExperienceDto){
        return ResponseEntity.ok(portfolioService.editWorkExperienceSection(workExperienceDto));
    }



}
