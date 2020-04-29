package gRPCClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.proto.greet.GreetServiceGrpc;
import org.proto.prime.PrimeNoDecompositionGrpc;
import org.proto.prime.PrimeNoDecompositionRequest;

public class PrimeClient {

    public static void main(String[] args) {
        System.out.println("i am a grpc client");

        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        System.out.println("Creating a stub");

        PrimeNoDecompositionGrpc.PrimeNoDecompositionBlockingStub primeClient = PrimeNoDecompositionGrpc.newBlockingStub(managedChannel);
        Integer number = 567890;
        primeClient.prime(PrimeNoDecompositionRequest.newBuilder()
                .setPno(number)
                .build()).forEachRemaining(primeNoDecompositionesponse -> {
            System.out.println(primeNoDecompositionesponse.getPrimeFactor());
        });


        System.out.println("shutting down channel");

        managedChannel.shutdown();
    }
}
