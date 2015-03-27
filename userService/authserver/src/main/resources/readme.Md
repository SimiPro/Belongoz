GET TOKEN

curl curl:curl@localhost:8005/userservice/oauth/token -d grant_type=password -d client_id=curl -d username=user -d password=password
curl ios:simiproo@localhost:8005/userservice/oauth/token -d grant_type=password -d client_id=ios -d username=user -d password=password

curl localhost:9005/ios/image/ -H "Authorization: Bearer 229aadd2-fa52-4007-a0b1-79e7c6be9fd3"




 Create User:

 curl -v -X POST \
   -H "Content-Type: application/json" \
   -H "Authorization: Basic MzUzYjMwMmM0NDU3NGY1NjUwNDU2ODdlNTM0ZTdkNmE6Mjg2OTI0Njk3ZTYxNWE2NzJhNjQ2YTQ5MzU0NTY0NmM=" \
   -d '{"user":{"emailAddress":"user@example.com"}, "password":"password"}' \
   'http://localhost:8080/oauth2-provider/v1.0/users'

  {"apiUser":
     {"emailAddress":"user@example.com",
     "firstName":null,
     "lastName":null,
     "age":null,
     "id":"8a34d009-3558-4c8c-a8da-1ad2b2a393c7",
     "name":"user@example.com"},
     "oauth2AccessToken":
     {"access_token":"7e0e4708-7837-4a7e-9f87-81c6429b02ac",
     "token_type":"bearer",
     "refresh_token":"d0f248ab-e30f-4a85-860c-bd1e388a39b5",
     "expires_in":5183999,
     "scope":"read write"
     }
  }

 Request access token:

    curl curl:curl@localhost:8005/oauth/token \
    -d grant_type=password \
    -d client_id=curl \
    -d username=user@example.com \
    -d password=password


  curl -v -X POST \
    -H "Content-Type: application/json" \
    -H "Authorization: Basic Y2xpZW50OmNsaWVudA==" \
    'http://localhost:8005/oauth/token?grant_type=password&username=user@example.com&password=password'

  {
    "access_token":"a838780e-35ef-4bd5-92c0-07a45aa74948",
    "token_type":"bearer",
    "refresh_token":"ab06022f-247c-450a-a11e-2ffab116e3dc",
    "expires_in":5183999
  }

Refresh token:

> curl -v -X POST \
   -H "Content-Type: application/json" \
   -H "Authorization: Basic MzUzYjMwMmM0NDU3NGY1NjUwNDU2ODdlNTM0ZTdkNmE6Mjg2OTI0Njk3ZTYxNWE2NzJhNjQ2YTQ5MzU0NTY0NmM=" \
   'http://localhost:8080/oauth2-provider/oauth/token?grant_type=refresh_token&refresh_token=ab06022f-247c-450a-a11e-2ffab116e3dc'

{
   "access_token":"4835cd11-8bb7-4b76-b857-55c6e7f36fc4",
   "token_type":"bearer",
   "refresh_token":"ab06022f-247c-450a-a11e-2ffab116e3dc",
   "expires_in":5183999
}

Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException:
 Error creating bean with name 'securityFilterChainRegistration' defined in class path resource
[org/springframework/boot/autoconfigure/security/SpringBootWebSecurityConfiguration.class]:
Unsatisfied dependency expressed through constructor argument with index 0 of type [javax.servlet.Filter]: :
Error creating bean with name 'org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration':
Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException:
Could not autowire method:
public void org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration.setFilterChainProxySecurityConfigurer(
org.springframework.security.config.annotation.ObjectPostProcessor,java.util.List)
throws java.lang.Exception; nested exception is org.springframework.beans.factory.BeanExpressionException:
Expression parsing failed; nested exception is org.springframework.beans.factory.BeanCreationException:
Error creating bean with name 'org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration':
Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException:
Could not autowire field: private org.springframework.security.oauth2.provider.token.TokenStore
org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration.tokenStore;
nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'OAuthConfig':
Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException:
Could not autowire field: private org.springframework.security.authentication.dao.DaoAuthenticationProvider
com.belongo.services.user.OAuthConfig.provider; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException:
No qualifying bean of type [org.springframework.security.authentication.dao.DaoAuthenticationProvider] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire method: public void org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration.setFilterChainProxySecurityConfigurer(org.springframework.security.config.annotation.ObjectPostProcessor,java.util.List) throws java.lang.Exception; nested exception is org.springframework.beans.factory.BeanExpressionException: Expression parsing failed; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire field: private org.springframework.security.oauth2.provider.token.TokenStore org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration.tokenStore; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'OAuthConfig': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire field: private org.springframework.security.authentication.dao.DaoAuthenticationProvider com.belongo.services.user.OAuthConfig.provider; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [org.springframework.security.authentication.dao.DaoAuthenticationProvider] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency.
Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}