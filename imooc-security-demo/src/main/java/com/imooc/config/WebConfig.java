package com.imooc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author zhengquan
 * @date 2019/8/25
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

//    @Autowired
//    private TimeIntercepter timeIntercepter;

//    @Bean
//    public FilterRegistrationBean timeFilter() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new TimeFilter());
//        List<String> urls = CollUtil.newArrayList();
//        urls.add("/*");
//        registrationBean.setUrlPatterns(urls);
//        return registrationBean;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
//        registry.addInterceptor(timeIntercepter);
//    }
}
