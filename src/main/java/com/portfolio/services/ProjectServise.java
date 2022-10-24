package com.portfolio.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.models.Project;
import com.portfolio.repositories.ProjectRepository;

@Service
public class ProjectServise {

	@Autowired
	ProjectRepository projectRepository;

	public List<Project> projectList() {
		Iterable<Project> projectIterable = projectRepository.findAll();
		List<Project> projects = new ArrayList<Project>();
		for (Project project : projectIterable) {
			projects.add(project);
		}
		return projects;
	}

//	public Project[] arr() {
//		Iterable<Project> projectIterable = projectRepository.findAll();
//		Project[] array = new Project[projectList().size()];
//		for (int i = 0; i < array.length; i++) {
//			array[i] = projectIterable.iterator();
//		}
//		return array;
//	}

	public File file(MultipartFile file) {
		File image = null;
		String folderName = "ImagesPreviewPortfolio";
		try {
			File folder = new File(folderName);
			folder.mkdir();
			if (folder.exists()) {
				String path = folderName + "\\" + file.getOriginalFilename();
				System.out.println(path);
				Path copied = Paths.get(path);
				image = new File(path);

				Files.copy(file.getInputStream(), copied, StandardCopyOption.REPLACE_EXISTING);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public void addProjectToRepository(Project project) {
		projectRepository.save(project);
	}

	public File getImage(int index) {

		File image = projectRepository.findById(index).orElseThrow().getImage();
		return image;
	}

	public Project project(int id) {

		return projectRepository.findById(id).orElseThrow();
	}

	public void deleteProject(int id) {
		Project project = projectRepository.getReferenceById(id);
		projectRepository.delete(project);
	}
}
