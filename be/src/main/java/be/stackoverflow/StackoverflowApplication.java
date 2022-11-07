package be.stackoverflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
@EnableJpaAuditing
public class StackoverflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(StackoverflowApplication.class, args);
	}

	/**
	 * 유현 : Audit 기능 추가
	 * 코드리뷰 후 적용 예정 부분
	 * 현재는 UUID로 생성자 작성자를 임의의 값으로 넣지만, 연관관계 매핑과 로그인 인증 이후 USER로 교체 예정
	 */
	@Bean
	public AuditorAware<String> auditorProvider() {
		return () -> Optional.of(UUID.randomUUID().toString());
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**")
//						.allowedOriginPatterns("http://pre-19.s3-website.ap-northeast-2.amazonaws.com")
//						.allowedMethods("*")
//						.allowedHeaders("*")
//						.exposedHeaders("Authorization", "Refresh")
//						.allowCredentials(true).maxAge(3600);
//			}
//		};

}
