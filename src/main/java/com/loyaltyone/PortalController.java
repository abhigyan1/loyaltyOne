
package com.loyaltyone;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PortalController {

	@RequestMapping(value="/")
	public String indexPage() {
		return "index";
	}
}
