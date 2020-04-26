package gRPCSever;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingSever {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(50051)
                .build();
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("server recived a shutdown request");
            server.shutdown();
            System.out.println("server is successfully shutdown");

        }));

        server.awaitTermination();

    }
}
