package com.portly.backend.services;

import com.portly.backend.dto.*;
import com.portly.backend.dto.output.PortfolioDto;
import com.portly.backend.entities.Portfolio;
import com.portly.backend.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface PortfolioService {

    PortfolioDto getPortFolio(String username);

    Portfolio getPortfolioByUser(User user);

    WorkExperienceDto editWorkExperienceSection(WorkExperienceDto workExperienceDto);

    SkillDto editSkillSection(SkillDto skillDto);

    ContactDto editContactSection(ContactDto contactDto);

    ProjectSectionDto editProjectSection(ProjectSectionDto projectSectionDto);

    AppBarDto editAppBarSection(AppBarDto appBarDto);

    HeaderDto editHeaderSection(HeaderDto headerDto);

    AchievementDto editAchievementSection(AchievementDto achievementDto);

    Portfolio createPortfolio(User user);

    Portfolio savePortfolio(Portfolio portfolio);


}
