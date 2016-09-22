package com.hhh.web.controller.Fenfa;

import java.awt.image.BufferedImage;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.web.model.LoginLogBean;
import com.hhh.fund.web.model.UserBean;

@Controller
public class FenfaLoginController {
	@Autowired
	private UserCenterService uconterService;
	@Autowired
	private Producer captchaProducer;
	
	/**
	 * 跳转到登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model,HttpServletRequest request) {
		String result = request.getParameter("result");
		if(result != null){
			if(result.equals("0")){
				model.addAttribute("result", "fail");
			}
		}
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
	public String adminLogin(UserBean user, HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
		token.setRememberMe(user.isRememberMe());
		try {
			subject.login(token);
			Session session = subject.getSession();
			UserBean userBean = uconterService.findByUsername(user.getUsername());
			session.setAttribute("loginUser",userBean);
			LoginLogBean log = new LoginLogBean();
			log.setCustomerId(user.getCustomerId());
			log.setIp(StringUtil.getIpAddress(request));
			log.setLoginName(user.getUsername());
			log.setLoginTime(StringUtil.dateFormat(new Date()));
			uconterService.saveLoginLog(log);
			return "redirect:/main/goToPage";
		}catch (AuthenticationException e) {
			e.printStackTrace();
			token.clear();
			return "redirect:/login?result=0";
		}
	}
	
	/**
	 * 生成验证码图片
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/captcha-image")  
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        response.setDateHeader("Expires", 0); 
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg");  
        String capText = captchaProducer.createText();  
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);  
        BufferedImage bi = captchaProducer.createImage(capText);  
        ServletOutputStream out = response.getOutputStream();  
        ImageIO.write(bi, "jpg", out);  
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }  
        return null;  
    }  
	
	/**
	 * 校验验证码
	 * @param verifyCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "checkVerificationCode")
    @ResponseBody
    public boolean checkVerificationCode(@RequestParam("verifyCode")String verifyCode,
       HttpServletRequest request){
        String kaptchaExpected = (String)request.getSession()
               .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        String kaptchaReceived = verifyCode;
        //万能验证码3hmis
        if(kaptchaReceived.equals("3hmis")){
        	return true;
        }
        if  (kaptchaReceived == null  || !kaptchaReceived.equalsIgnoreCase(kaptchaExpected))
         {
                return false;
         }
         return true;
      }
}
