import com.sun.net.httpserver.*;
// import org.apache.commons.text.StringEscapeUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.*;
import endpoint.Endpoint;

public class Server {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(45555), 0);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(1);
        HttpContext context = server.createContext("/", new Endpoint());
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("Server started");
    }



}