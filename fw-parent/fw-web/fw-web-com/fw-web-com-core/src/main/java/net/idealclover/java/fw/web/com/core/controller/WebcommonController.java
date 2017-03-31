package net.idealclover.java.fw.web.com.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/com/core")
public class WebcommonController {
	
	
	@RequestMapping("/gotoDesktop")
	public ModelAndView gotoDesktop(ModelAndView mv) {
		mv.setViewName("/com/core/views/desktop");
		return mv;
	}

}
