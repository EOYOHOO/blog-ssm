package com.yoyling.controller;

import com.yoyling.domain.Content;
import com.yoyling.domain.File;
import com.yoyling.utils.LogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FileController extends BaseController {
	@RequestMapping("/file")
	public String showFiles(Model model) {

		//插入日志
		logService.insert(LogUtil.insertLog(request,"/file"));

		List<Content> contents = contentService.selectAllContent();
		for (Content c : contents) {
			c.setCategorySlug(categoryService.selectByPrimaryKey(c.getCgid()).getCgSlug());
		}

		List<File> files = fileService.selectAllFile();
		model.addAttribute("files",files);
		for (File file : files) {
			System.out.println(file);
		}

		model.addAttribute("contents",contents);

		List<Content> recommendContents = contentService.selectRecommendContent();
		model.addAttribute("recommendContents",recommendContents);
		return "file";

	}

	@RequestMapping("/getFileList")
	public String getFileList(Model model) {


		return "file";

	}
}