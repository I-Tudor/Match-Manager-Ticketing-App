package org.example.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.72.0)",
    comments = "Source: refresh.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class RefreshServiceGrpc {

  private RefreshServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "RefreshService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.example.grpc.RefreshRequest,
      org.example.grpc.RefreshReply> getSendRefreshMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendRefresh",
      requestType = org.example.grpc.RefreshRequest.class,
      responseType = org.example.grpc.RefreshReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.example.grpc.RefreshRequest,
      org.example.grpc.RefreshReply> getSendRefreshMethod() {
    io.grpc.MethodDescriptor<org.example.grpc.RefreshRequest, org.example.grpc.RefreshReply> getSendRefreshMethod;
    if ((getSendRefreshMethod = RefreshServiceGrpc.getSendRefreshMethod) == null) {
      synchronized (RefreshServiceGrpc.class) {
        if ((getSendRefreshMethod = RefreshServiceGrpc.getSendRefreshMethod) == null) {
          RefreshServiceGrpc.getSendRefreshMethod = getSendRefreshMethod =
              io.grpc.MethodDescriptor.<org.example.grpc.RefreshRequest, org.example.grpc.RefreshReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendRefresh"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.grpc.RefreshRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.grpc.RefreshReply.getDefaultInstance()))
              .setSchemaDescriptor(new RefreshServiceMethodDescriptorSupplier("SendRefresh"))
              .build();
        }
      }
    }
    return getSendRefreshMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RefreshServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RefreshServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RefreshServiceStub>() {
        @java.lang.Override
        public RefreshServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RefreshServiceStub(channel, callOptions);
        }
      };
    return RefreshServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static RefreshServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RefreshServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RefreshServiceBlockingV2Stub>() {
        @java.lang.Override
        public RefreshServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RefreshServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return RefreshServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RefreshServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RefreshServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RefreshServiceBlockingStub>() {
        @java.lang.Override
        public RefreshServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RefreshServiceBlockingStub(channel, callOptions);
        }
      };
    return RefreshServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RefreshServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RefreshServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RefreshServiceFutureStub>() {
        @java.lang.Override
        public RefreshServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RefreshServiceFutureStub(channel, callOptions);
        }
      };
    return RefreshServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void sendRefresh(org.example.grpc.RefreshRequest request,
        io.grpc.stub.StreamObserver<org.example.grpc.RefreshReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendRefreshMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service RefreshService.
   */
  public static abstract class RefreshServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return RefreshServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service RefreshService.
   */
  public static final class RefreshServiceStub
      extends io.grpc.stub.AbstractAsyncStub<RefreshServiceStub> {
    private RefreshServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RefreshServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RefreshServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendRefresh(org.example.grpc.RefreshRequest request,
        io.grpc.stub.StreamObserver<org.example.grpc.RefreshReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendRefreshMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service RefreshService.
   */
  public static final class RefreshServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<RefreshServiceBlockingV2Stub> {
    private RefreshServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RefreshServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RefreshServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public org.example.grpc.RefreshReply sendRefresh(org.example.grpc.RefreshRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendRefreshMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service RefreshService.
   */
  public static final class RefreshServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<RefreshServiceBlockingStub> {
    private RefreshServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RefreshServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RefreshServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.example.grpc.RefreshReply sendRefresh(org.example.grpc.RefreshRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendRefreshMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service RefreshService.
   */
  public static final class RefreshServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<RefreshServiceFutureStub> {
    private RefreshServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RefreshServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RefreshServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.example.grpc.RefreshReply> sendRefresh(
        org.example.grpc.RefreshRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendRefreshMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_REFRESH = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_REFRESH:
          serviceImpl.sendRefresh((org.example.grpc.RefreshRequest) request,
              (io.grpc.stub.StreamObserver<org.example.grpc.RefreshReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSendRefreshMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.example.grpc.RefreshRequest,
              org.example.grpc.RefreshReply>(
                service, METHODID_SEND_REFRESH)))
        .build();
  }

  private static abstract class RefreshServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RefreshServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.example.grpc.Refresh.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RefreshService");
    }
  }

  private static final class RefreshServiceFileDescriptorSupplier
      extends RefreshServiceBaseDescriptorSupplier {
    RefreshServiceFileDescriptorSupplier() {}
  }

  private static final class RefreshServiceMethodDescriptorSupplier
      extends RefreshServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    RefreshServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RefreshServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RefreshServiceFileDescriptorSupplier())
              .addMethod(getSendRefreshMethod())
              .build();
        }
      }
    }
    return result;
  }
}
