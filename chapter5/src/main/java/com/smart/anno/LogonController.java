package com.smart.anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LogonController {
    /*
      @Autowired默认按类型(byType)匹配的方式查找匹配的Bean,当有且仅有一个匹配的Bean时,Spring将其注入到@Autowired变量中
     */
    @Autowired
	private LogonService logonService;
}
