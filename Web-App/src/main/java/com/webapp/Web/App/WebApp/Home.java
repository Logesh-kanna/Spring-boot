package com.webapp.Web.App.WebApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class Home {
	
	public class Data {
		private String name;
		private String course;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCourse() {
			return course;
		}
		public void setCourse(String course) {
			this.course = course;
		}
		
	}

	@RequestMapping("home")
	public ModelAndView home(Data data) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("data", data);
		mv.setViewName("home");
		return mv;
	}
	
}
