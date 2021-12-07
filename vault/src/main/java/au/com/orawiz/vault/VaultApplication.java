package au.com.orawiz.vault;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import au.com.orawiz.vault.services.FilesStorageService;


@SpringBootApplication
public class VaultApplication implements CommandLineRunner {
	  @Resource
	  FilesStorageService storageService;

	  public static void main(String[] args) {
			SpringApplication.run(VaultApplication.class, args);
		}
	  
	  @Override
	  public void run(String... arg) throws Exception {
		  storageService.deleteAll();
		  storageService.init();
	  }
	}
