package appsprototyping;

import java.io.IOException;
import java.net.URL;

public class MainOne {
    private static boolean alive = true;
    public static void main(String[] args) throws IOException, InterruptedException {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            alive = false;
            System.out.println("Received stop signal");
        }));


        while(alive) {
            URL resource = MainOne.class.getResource("/content.txt");
            System.out.println(
                    new String(resource.openStream().readAllBytes())
            );
            Thread.sleep(1000);
        }
    }
}
