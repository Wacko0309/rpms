package com.inext.rpms.config;

//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// front get api session
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		//spring boot v2.4後使用
		.allowedOriginPatterns("*")
		.allowCredentials(true)
		.allowedMethods("POST")
		.allowedHeaders("*");
	}
	
	// Filterがある場合に使える
	//implements WebMvcConfigurerの必要がない
	//addCorsMappings と corsFilter　一つを選ぶ
//	@Bean
//	public CorsFilter corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", corsConfig());
//		return new CorsFilter(source);
//	}
//	
//	private CorsConfiguration corsConfig() {
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.addAllowedOriginPattern("*");
//		corsConfiguration.addAllowedHeader("*");
//		corsConfiguration.addAllowedMethod("*");
//		corsConfiguration.setAllowCredentials(true);
//		return corsConfiguration;
//	}
}
