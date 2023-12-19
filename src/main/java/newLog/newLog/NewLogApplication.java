package newLog.newLog;

import newLog.newLog.service.InitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class NewLogApplication  implements CommandLineRunner {

	@Autowired
	InitDbService initDbService;

	public static void main(String[] args) {
		SpringApplication.run(NewLogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//initDbService.clearDb();
		initDbService.initDb();
	}



}
