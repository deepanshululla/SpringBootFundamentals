package ttl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import ttl.larku.domain.Track;
import ttl.larku.service.TrackService;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"ttl"})
public class BootdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootdemoApplication.class, args);
	}

}


@Component
class StartUpRunner implements CommandLineRunner {

	@Autowired
	private TrackService trackService;
	@Override
	public void run(String... args) throws Exception  {
		System.out.println("Starting up...");
//		List<Track> tracks = trackService.getAllTracks();
//		tracks.forEach(System.out::println);
	}
}
