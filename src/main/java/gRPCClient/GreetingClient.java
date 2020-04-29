package gRPCClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.proto.calculator.CalculatorServiceGrpc;
import org.proto.calculator.Sum;
import org.proto.calculator.SumRequest;
import org.proto.calculator.SumResponse;
import org.proto.dummy.DummyServiceGrpc;
import org.proto.greet.*;

public class GreetingClient {
    public static void main(String[] args) {
        System.out.println("i am a grpc client");

        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        System.out.println("Creating a stub");
        /*//sync client
        //dummy
        //DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(managedChannel);
        //async client
        // DummyServiceGrpc.DummyServiceFutureStub asyncClient=DummyServiceGrpc.newFutureStub(managedChannel);

        //do some thing here
        //syncClient.dosomeMethod();*/

        //craeted a greet client
        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(managedChannel);
/*
        //creating a greeting meaasge using protocol buffer
        //unary
        Greeting greeting = Greeting.newBuilder()
                .setFirstName("Bharat")
                .setLastName("HM")
                .build();

        GreetingRequest request = GreetingRequest.newBuilder()
                .setGreeting(greeting)
                .build();

        //calling rpc and getting ack response
        GreetingResponse response = greetClient.greet(request);
        System.out.println(response.getResult());*/

        //server streaming
        GreetManyRequest greetManyRequest=GreetManyRequest.newBuilder()
                .setGreeting(Greeting.newBuilder().setFirstName("Bharat")).build();

        greetClient.greetManyTimes(greetManyRequest).forEachRemaining(greetManyResponse -> {
            System.out.println(greetManyResponse.getResult());
        });

        //calculator service
        CalculatorServiceGrpc.CalculatorServiceBlockingStub calculatorClient=CalculatorServiceGrpc.newBlockingStub(managedChannel);

        Sum sum=Sum.newBuilder()
                .setA(10)
                .setB(20)
                .build();

        SumRequest sumRequest=SumRequest.newBuilder()
                .setSum(sum)
                .build();

        SumResponse sumResponse=calculatorClient.addition(sumRequest);
        System.out.println(sumResponse.getResult());

        System.out.println("shutting down channel");

        managedChannel.shutdown();


    }
}
