package com.spring.Uben.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.Uben.DAO.HomeDAO;
import com.spring.Uben.model.HomeModel;

@Controller
public class HomeController {
	
	@Autowired
	HomeDAO DAO;

	@RequestMapping("home")
	public String redirection() {
		return "index.jsp";
	}
	
	@RequestMapping("addData") 
	public String addUser(HomeModel homeModel) {
		DAO.save(homeModel);
		return "user.jsp";
	}
	
	@RequestMapping("show")
	public String showUser() {
		return "user.jsp";
	}
	
	@RequestMapping("showUser") 
	@ResponseBody
    public List<HomeModel> getUsers() {
        return (List<HomeModel>) DAO.findAll(); // Retrieve list of users from DAO
    }
}
