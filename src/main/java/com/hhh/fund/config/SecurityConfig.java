package com.hhh.fund.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.hhh.security.ShiroDBRealm;


/**
 * Shiro配置
 * @author 3hzl
 *
 */
@Configuration
public class SecurityConfig implements EnvironmentAware  {
	private final static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	private Environment env;
	
	@Override
    public void setEnvironment(final Environment environment) {
        this.env = environment;
    }
	
	
	@Bean(name="securityManager")
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(realms());
		
		securityManager.setSessionManager(sessionManager());
		
		return securityManager;
	}
	
	protected SessionManager sessionManager() {
		return new ServletContainerSessionManager();
	}
	
	@Bean
	public AuthorizingRealm shiroDBRealm() {
		return new ShiroDBRealm();
	}
	
	@Bean
	public Collection<Realm> realms() {
		List<Realm> realms = new LinkedList<Realm>();
		realms.add(shiroDBRealm());
		return realms;
	}
	
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	@Bean(name="shiroFilter")
	public Filter shiroFilter() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		
		shiroFilterFactoryBean.setLoginUrl(env.getProperty("loginURL"));
		shiroFilterFactoryBean.setSuccessUrl(env.getProperty("successURL"));
		shiroFilterFactoryBean.setUnauthorizedUrl(env.getProperty("unauthorizedURL"));
		
		Map<String, Filter> filters = new HashMap<String, Filter>();
		filters.put("anon", new AnonymousFilter());
		filters.put("anthc", new FormAuthenticationFilter());
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl("/login");
		filters.put("logout", logoutFilter);
		shiroFilterFactoryBean.setFilters(filters);
		
		Map<String, String> filterChainDefMap = new LinkedHashMap<String, String>();
		filterChainDefMap.put("/login", "anon");
		filterChainDefMap.put("/logout", "logout");
		filterChainDefMap.put("/main/toRegister", "anon");
		filterChainDefMap.put("/main/getSecondRegions", "anon");
		filterChainDefMap.put("/main/getThirdRegions", "anon");
		filterChainDefMap.put("/main/register", "anon");
		filterChainDefMap.put("/main/getUsername", "anon");
		filterChainDefMap.put("/main/**", "anthc");
		filterChainDefMap.put("/buy/**", "anthc");
		filterChainDefMap.put("/admin/**", "anthc");
		filterChainDefMap.put("/fund/**", "anthc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefMap);
		try {
			return (Filter)shiroFilterFactoryBean.getObject();
		} catch(Exception ex) {
			throw new BeanCreationException("shiroFilter", "FactoryBean throw exception on object creation", ex);
		}
	}
}
