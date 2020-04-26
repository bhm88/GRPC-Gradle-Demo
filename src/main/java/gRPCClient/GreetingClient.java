package gRPCClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.proto.dummy.DummyServiceGrpc;

public class GreetingClient {
    public static void main(String[] args) {
        System.out.println("i am a grpc client");

        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        System.out.println("Creating a stub");
        //sync client
        DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(managedChannel);
        //async client
        // DummyServiceGrpc.DummyServiceFutureStub asyncClient=DummyServiceGrpc.newFutureStub(managedChannel);

        //do some thing here
        //syncClient.dosomeMethod();
        System.out.println("shutting down channel");

        managedChannel.shutdown();


    }
}
