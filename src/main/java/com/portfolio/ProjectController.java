package com.portfolio;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.models.Project;
import com.portfolio.services.ProjectServise;

@CrossOrigin
@RestController("/")
public class ProjectController {
	@Autowired
	ProjectServise projectServise;

	@GetMapping("/projectList")
	List<Project> projectList() {
		return projectServise.projectList();
	}

	@GetMapping("/project/{id}")
	Project project(@PathVariable int id) {
		return projectServise.projectList().get(id);
	}

	@GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImage(@PathVariable int id) throws IOException {
		URI image = projectServise.getImage(id).toURI();

		return Files.readAllBytes(Paths.get(image));
	}

	@PostMapping("/start/{id}/delete")
	public String deleteProject(@PathVariable int id, Model model) {
		projectServise.deleteProject(id);
		return "deleted";
	}

	@PostMapping("/start/{id}/edit/name")
	public void editName(@PathVariable int id, @RequestParam(value = "name") String name) {
		Project project = projectServise.project(id);
		project.setName(name);
		projectServise.addProjectToRepository(project);
	}

	@PostMapping("/start/{id}/edit/description")
	public void editDescription(@PathVariable int id, @RequestParam(name = "description") String description) {
		Project project = projectServise.project(id);
		project.setDescription(description);
		projectServise.addProjectToRepository(project);
	}

	@PostMapping("/start/{id}/edit/tags")
	public void editTags(@PathVariable int id, @RequestParam(name = "tags") String[] tags) {
		Project project = projectServise.project(id);
		project.setTags(tags);
		projectServise.addProjectToRepository(project);
	}

	@PostMapping("/start/{id}/edit/image")
	public void editImage(@PathVariable int id, @RequestParam(value = "file") MultipartFile file) {
		Project project = projectServise.project(id);
		if (file.getSize() != 0) {
			File image = projectServise.file(file);
			project.addImage(image);
		}
		projectServise.addProjectToRepository(project);
	}

	@PostMapping("/start/{id}/edit/url")
	public void editUrl(@PathVariable int id, @RequestParam(name = "url") String url) {
		Project project = projectServise.project(id);
		project.setUrl(url);
		projectServise.addProjectToRepository(project);
	}
}
