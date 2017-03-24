/**
 * 
 */
package com.yls.bus.sys.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yls.bus.sys.service.GeneratorService;

/**
 * @author Lian Shan Yang
 *
 */
@Controller
@RequestMapping(value="/generator")
public class GeneratorCtrl {

	@Autowired
	private GeneratorService generatorService;
	
}
