package com.lcwd.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class UserServiceApplication {
//	
//	@Autowired
//	private ClientRegistrationRepository clientRegistrationRepository;
//	
//	@Autowired
//	private OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
//		
//		RestTemplate restTemplate=new RestTemplate();
//		List<ClientHttpRequestInterceptor> interceptors=new ArrayList<>();
//		interceptors.add(new RestTemplateInterceptor(manager(clientRegistrationRepository,auth2AuthorizedClientRepository)));
//		restTemplate.setInterceptors(interceptors);
		return new RestTemplate();
	}
	
	//Declaring the bean of OAuth2 Authorized client manager
//	@Bean
//	public OAuth2AuthorizedClientManager manager(ClientRegistrationRepository clientRegistrationRepository,OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository) {
//		
//		OAuth2AuthorizedClientProvider provider=OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
//		DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager=new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,auth2AuthorizedClientRepository);
//		defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
//		
//		return defaultOAuth2AuthorizedClientManager;
//		
//	}

}
