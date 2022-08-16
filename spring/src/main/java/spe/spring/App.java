package spe.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(App.class, args);
    String serverPort = context.getEnvironment().getProperty("server.port");
    System.out.println("App started at http://127.0.0.1:" + serverPort);
  }
}
