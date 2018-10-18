package com.duia.commodity.configurer;

import com.duia.commodity.common.realm.DuiaSessionClient;
import com.duia.commodity.common.realm.NormalRealm;
import com.duia.sso.client.filter.CookieSsoFilter;
import com.duia.sso.client.filter.SessionSsoFilter;
import com.duia.sso.client.service.AbstractSessionClient;
import com.duia.sso.client.service.path.BlackPathMatch;
import com.duia.sso.client.service.path.PathMatch;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuhao on 2017/7/17.
 */
@Configuration
public class ShiroConfigurer {
    /**
     * 配置shiroFilter
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filtersMap = new LinkedHashMap();
        filtersMap.put("cookie", cookieSsoFilter());
        filtersMap.put("session", sessionSsoFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap();
        filterChainDefinitionMap.put("/**", "cookie,session");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public PathMatch pathMatch() {
        PathMatch pathMatch = new BlackPathMatch();
        List<String> blackList = new ArrayList<>();
        blackList.add("/order/confirmRoute");
        blackList.add("/commodity/select");
        blackList.add("/order/pay");
        blackList.add("/order/payment");
        blackList.add("/order/batch");
        blackList.add("/order/createOrder");
        blackList.add("/order/createUpgradeOrder");
        blackList.add("/order/confirmNew");
        blackList.add("/order/upgradeConfirm");
        blackList.add("/pay/**");
        pathMatch.setUrls(blackList);
        return pathMatch;
    }

    @Bean(name = "cookie")
    public CookieSsoFilter cookieSsoFilter() {
        CookieSsoFilter cookieSsoFilter = new CookieSsoFilter();
        cookieSsoFilter.setPathMatch(pathMatch());
        List httpUrls = new ArrayList();
        httpUrls.add("/*");
        cookieSsoFilter.setHttpsUrls(httpUrls);
        return cookieSsoFilter;
    }

    @Bean(name = "session")
    public SessionSsoFilter sessionSsoFilter() {
        SessionSsoFilter sessionSsoFilter = new SessionSsoFilter();
        sessionSsoFilter.setPathMatch(pathMatch());
        sessionSsoFilter.setSessionClient(sessionClient());
        List httpUrls = new ArrayList();
        httpUrls.add("/*");
        sessionSsoFilter.setHttpsUrls(httpUrls);
        return sessionSsoFilter;
    }

    @Bean
    public AbstractSessionClient sessionClient() {
        AbstractSessionClient sessionClient = new DuiaSessionClient();
        return sessionClient;
    }

    /**
     * 设置securityManager
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /**
     * 设置myshiroRealm
     *
     * @return
     */
    @Bean
    public NormalRealm myShiroRealm() {
        NormalRealm myShiroRealm = new NormalRealm();
        return myShiroRealm;
    }

}
