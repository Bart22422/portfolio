package com.portfolio;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.models.DesignTags;
import com.portfolio.models.Project;
import com.portfolio.services.ProjectServise;

@Controller
@RequestMapping("/")
public class WebController {
	@Autowired
	ProjectServise projectServise;

	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public String page(Model model) {
		DesignTags[] designTags = DesignTags.values();
		List<Project> projects = projectServise.projectList();
		model.addAttribute("designTags", designTags);
		model.addAttribute("projectList", projects);
		return "page";
	}

	@GetMapping("/resourses")
	public String resourse(Model model) {
		List<Project> projects = projectServise.projectList();
		model.addAttribute("projectList", projects);
		return "index";
	}

	@PostMapping("/start")
	public String project(@RequestParam(value = "name") String projectName,
			@RequestParam(name = "descr") String projectDescr, @RequestParam(name = "url") String url,
			@RequestParam(name = "tags") String[] tags, @RequestParam(value = "file") MultipartFile file) {

		Project project = new Project();
		if (file.getSize() != 0) {

			File image = projectServise.file(file);
			project.setUrl(url);
			project.addImage(image);
			project.setDescription(projectDescr);
			project.setName(projectName);
			project.setDate();
			project.setTags(tags);
			String[] tags1 = project.getTags();
			System.out.println(Arrays.toString(tags1));
			projectServise.addProjectToRepository(project);
		}
		return "page";
	}

	@GetMapping("/start/{id}")
	public String progects(@PathVariable int id, Model model) {
		Project project = projectServise.project(id);
		System.out.println(project.getName());

		model.addAttribute("designTags", project.getTags());
		model.addAttribute("project", project);
		return "project";
	}

	@GetMapping("/start/{id}/edit")
	public String moreProject(@PathVariable int id, Model model) {
		Project project = projectServise.project(id);
		DesignTags[] designTags = DesignTags.values();
		model.addAttribute("project", project);
		model.addAttribute("designTags", designTags);
		return "edit_project";
	}

}
