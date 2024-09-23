package com.portly.backend;

import com.portly.backend.dto.ProjectDto;
import com.portly.backend.dto.ProjectSectionDto;
import com.portly.backend.entities.Portfolio;
import com.portly.backend.entities.Project;
import com.portly.backend.entities.ProjectSection;
import com.portly.backend.entities.User;
import com.portly.backend.repositories.PortfolioRepository;
import com.portly.backend.services.impls.PortfolioServiceImpl;
import com.portly.backend.services.impls.ProjectSectionServiceImpl;
import com.portly.backend.services.impls.ProjectServiceImpl;
import com.portly.backend.services.impls.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
class BackendApplicationTests {


	@Mock
	private UserServiceImpl userService;

	@Mock
	private ProjectServiceImpl projectService;

	@Mock
	private ProjectSectionServiceImpl projectSectionService;

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private PortfolioRepository portfolioRepository;

	@InjectMocks
	private PortfolioServiceImpl portfolioService;

	private ProjectSectionDto projectSectionDto;
	private ProjectSection projectSection;
	private User user;
	private Portfolio portfolio;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this); // Updated for compatibility

		// Set up test data
		user = new User();
		user.setEmail("testUser"); // Ensure the user has a username

		portfolio = new Portfolio();
		projectSection = new ProjectSection();
		projectSection.setId(1L);

		// Create test projects
		List<Project> projects = new ArrayList<>();
		projects.add(Project.builder()
				.id(1L)
				.image(Arrays.asList(101, 102, 103))
				.tags(Arrays.asList("Java", "Spring Boot"))
				.title("Project Management App")
				.description("A project management app built using Spring Boot and React.")
				.projectUrl("https://github.com/user/project-management-app")
				.build());
		projects.add(Project.builder()
				.id(2L)
				.image(Arrays.asList(201, 202, 203))
				.tags(Arrays.asList("Python", "Django"))
				.title("E-commerce Website")
				.description("An e-commerce platform for buying and selling products, developed using Django.")
				.projectUrl("https://github.com/user/e-commerce-website")
				.build());
		projectSection.setProjects(projects);

		portfolio.setProjectSection(projectSection);

		projectSectionDto = new ProjectSectionDto();
		List<ProjectDto> projectDtos = new ArrayList<>();
		projectDtos.add(new ProjectDto(
				Arrays.asList(101, 102, 103),
				Arrays.asList("Java", "Spring Boot"),
				"Project Management App",
				"A project management app built using Spring Boot and React.",
				"https://github.com/user/project-management-app"
		));
		projectDtos.add(new ProjectDto(
				Arrays.asList(201, 202, 203),
				Arrays.asList("Python", "Django"),
				"E-commerce Website",
				"An e-commerce platform for buying and selling products, developed using Django.",
				"https://github.com/user/e-commerce-website"
		));
		projectSectionDto.setProjects(projectDtos);
		projectSectionDto.setTextColour("#FFFFFF");
		projectSectionDto.setBackgroundColour("#000000");

		// Mock behaviors
		when(userService.getCurrentUser()).thenReturn(user);
		when(portfolioRepository.findByUser(user)).thenReturn(Optional.of(portfolio)); // Ensure this returns the portfolio
		when(projectSectionService.updateProjectSection(any(ProjectSection.class)))
				.thenReturn(projectSection);

		// Adjusted mapping from ProjectDto to Project
		when(modelMapper.map(any(ProjectDto.class), eq(Project.class)))
				.thenAnswer(invocation -> {
					ProjectDto dto = invocation.getArgument(0);
					return Project.builder()
							.image(dto.getImage())
							.tags(dto.getTags())
							.title(dto.getTitle())
							.description(dto.getDescription())
							.projectUrl(dto.getProjectUrl())
							.build();
				});

		// Mapping from ProjectSection to ProjectSectionDto
		when(modelMapper.map(any(ProjectSection.class), eq(ProjectSectionDto.class)))
				.thenReturn(projectSectionDto);
	}


	@Test
	public void testEditProjectSection_UpdateAndAddProjects() {
		// Mock updated project response from projectService
		Project updatedProject1 = Project.builder()
				.id(1L)
				.image(Arrays.asList(101, 102, 103))
				.tags(Arrays.asList("Java", "Spring Boot"))
				.title("Updated Project 1")
				.description("Updated description for Project 1")
				.projectUrl("https://github.com/user/updated-project-1")
				.build();

		Project newProject3 = Project.builder()
				.id(3L)
				.image(Arrays.asList(201, 202, 203))
				.tags(Arrays.asList("Python", "Django"))
				.title("New Project 3")
				.description("Description for New Project 3")
				.projectUrl("https://github.com/user/new-project-3")
				.build();

		// Mocking behavior for service methods
		when(projectService.updateProject(any(Project.class))).thenReturn(updatedProject1);
		when(projectService.addProject(any(Project.class))).thenReturn(newProject3);

		// Call the method under test
		ProjectSectionDto result = portfolioService.editProjectSection(projectSectionDto);

		// Verify the interactions and result
		assertNotNull(result);
		assertEquals(2, result.getProjects().size());
		assertEquals("Updated Project 1", result.getProjects().get(0).getTitle());
		assertEquals("New Project 3", result.getProjects().get(1).getTitle());
		assertEquals("https://github.com/user/updated-project-1", result.getProjects().get(0).getProjectUrl());
		assertEquals("https://github.com/user/new-project-3", result.getProjects().get(1).getProjectUrl());

		// Verifying the service interactions
		verify(projectService, times(1)).updateProject(any(Project.class));
		verify(projectService, times(1)).addProject(any(Project.class));
	}

	@Test
	public void testEditProjectSection_RemoveProjects() {
		// Prepare test data with fewer projects (removing second project)
		List<ProjectDto> projectDtos = new ArrayList<>();
		projectDtos.add(new ProjectDto(
				Arrays.asList(101, 102, 103),  // Image list
				Arrays.asList("Java", "Spring Boot"),  // Tags
				"Updated Project 1",  // Title
				"Updated description for Project 1",  // Description
				"https://github.com/user/updated-project-1"  // Project URL
		));

		projectSectionDto.setProjects(projectDtos);  // Simulate removing the second project

		// Mocking behavior for projectService
		when(projectService.updateProject(any(Project.class)))
				.thenReturn(Project.builder()
						.id(1L)
						.image(Arrays.asList(101, 102, 103))
						.tags(Arrays.asList("Java", "Spring Boot"))
						.title("Updated Project 1")
						.description("Updated description for Project 1")
						.projectUrl("https://github.com/user/updated-project-1")
						.build());

		// Call the method under test
		ProjectSectionDto result = portfolioService.editProjectSection(projectSectionDto);

		// Verify the interactions and result
		assertNotNull(result);
		assertEquals(1, result.getProjects().size());
		assertEquals("Updated Project 1", result.getProjects().get(0).getTitle());
		assertEquals("https://github.com/user/updated-project-1", result.getProjects().get(0).getProjectUrl());

		// Verifying service interactions
		verify(projectService, times(1)).updateProject(any(Project.class));
		verify(projectService, times(1)).deleteProject(any(Project.class));
	}

	@Test
	public void testEditProjectSection_UpdateProjectSectionColours() {
		// Only update the section colors, no project changes
		projectSectionDto.setProjects(new ArrayList<>());  // No projects, only color update

		// Call the method under test
		ProjectSectionDto result = portfolioService.editProjectSection(projectSectionDto);

		// Verify the result
		assertNotNull(result);
		assertEquals("#FFFFFF", result.getTextColour());
		assertEquals("#000000", result.getBackgroundColour());

		// Verify that no projects were updated or added/removed
		verify(projectService, never()).updateProject(any(Project.class));
		verify(projectService, never()).addProject(any(Project.class));
		verify(projectService, never()).deleteProject(any(Project.class));
	}

}
