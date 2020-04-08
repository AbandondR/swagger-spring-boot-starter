#### a simple spring-boot-starter about swagger (online doc)
a completed starter contains by steps as follows:
> 1. a auto configuration file ,in this project is SwaggerAutoConfiguration. 
> 2. add this auto-configuration file to `META-INF/spring.factories`. the `org.springframework.boot.autoconfigure.EnableAutoConfiguration` as the key, and the value is `SwaggerAutoConfiguration`'s fully qualified name.
> 3. create a `spring-configuration-metadata.json` file to help IDE provide user a friendly hint  (of course ,this step can omit)