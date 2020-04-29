package gRPCSever;

import io.grpc.stub.StreamObserver;
import org.proto.prime.PrimeNoDecompositionGrpc;
import org.proto.prime.PrimeNoDecompositionRequest;
import org.proto.prime.PrimeNoDecompositionResponse;

public class PrimeServerImpl extends PrimeNoDecompositionGrpc.PrimeNoDecompositionImplBase {

    @Override
    public void prime(PrimeNoDecompositionRequest request, StreamObserver<PrimeNoDecompositionResponse> responseObserver) {
        Integer number = request.getPno();
        Integer divisor = 2;

        while (number > 1) {
            if (number % divisor == 0) {
                number = number / divisor;
                responseObserver.onNext(PrimeNoDecompositionResponse.newBuilder()
                        .setPrimeFactor(divisor)
                        .build());
            } else {
                divisor = divisor + 1;
            }
        }
        responseObserver.onCompleted();
    }
}
