package gRPCSever;

import io.grpc.stub.StreamObserver;
import org.proto.greet.GreetServiceGrpc;
import org.proto.greet.Greeting;
import org.proto.greet.GreetingRequest;
import org.proto.greet.GreetingResponse;

public class GreetServiceImpl  extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {
        //extract the fields we  need
        Greeting greeting=request.getGreeting();
        String firstName=greeting.getFirstName();

        //create the response
        String result="Hello" + firstName;
        GreetingResponse greetingResponse=GreetingResponse.newBuilder()
                .setResult(result)
                .build();

        //send the response
        responseObserver.onNext(greetingResponse);
        responseObserver.onCompleted();

        //super.greet(request, responseObserver);
    }
}
