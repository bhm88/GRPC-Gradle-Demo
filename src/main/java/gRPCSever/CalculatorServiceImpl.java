package gRPCSever;

import io.grpc.stub.StreamObserver;
import org.proto.calculator.CalculatorServiceGrpc;
import org.proto.calculator.Sum;
import org.proto.calculator.SumRequest;
import org.proto.calculator.SumResponse;

public class CalculatorServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {


        @Override
        public void addition(SumRequest request, StreamObserver<SumResponse> responseObserver) {
            //extract the fields we  need,here 2 numbers a and b to be added
            Sum sum = request.getSum();
            int a = sum.getA();
            int b = sum.getB();

            //add the two numbers
            int result = a + b;
            SumResponse response = SumResponse.newBuilder()
                    .setResult(result)
                    .build();


            //send the response
            responseObserver.onNext(response);
            responseObserver.onCompleted();

        }
}
