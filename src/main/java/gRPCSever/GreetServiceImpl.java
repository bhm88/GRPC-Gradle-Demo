package gRPCSever;

import io.grpc.stub.StreamObserver;
import org.proto.greet.*;

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

    @Override
    public void greetManyTimes(GreetManyRequest request, StreamObserver<GreetManyResponse> responseObserver) {
        String firstName=request.getGreeting().getFirstName();
        try{
for(int i=0;i<10;i++){
        String result="Hello " +firstName +" response number " + i;
    GreetManyResponse greetingResponse=GreetManyResponse.newBuilder()
                .setResult(result)
                .build();
        responseObserver.onNext(greetingResponse);
        Thread.sleep(1000);
}
}catch (InterruptedException ie){
        ie.printStackTrace();
        }
        finally {
            responseObserver.onCompleted();
        }
    }

}
