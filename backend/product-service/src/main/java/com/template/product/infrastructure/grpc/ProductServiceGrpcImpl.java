package com.template.product.infrastructure.grpc;

import com.template.product.application.port.in.ProductServicePort;
import com.template.product.domain.model.Product;
import com.template.product.grpc.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

@GrpcService
public class ProductServiceGrpcImpl extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductServicePort productServicePort;

    @Autowired
    public ProductServiceGrpcImpl(ProductServicePort productServicePort) {
        this.productServicePort = productServicePort;
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_product:read')")
    public void getProduct(GetProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        Product product = productServicePort.getProductById(java.util.UUID.fromString(request.getProductId()));
        ProductResponse response = ProductResponse.newBuilder()
                .setProductId(product.getId().toString())
                .setName(product.getName())
                .setDescription(product.getDescription())
                .setPrice(product.getPrice())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_product:write')")
    public void createProduct(CreateProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        Product product = new Product(request.getName(), request.getDescription(), request.getPrice());
        Product createdProduct = productServicePort.createProduct(product);
        ProductResponse response = ProductResponse.newBuilder()
                .setProductId(createdProduct.getId().toString())
                .setName(createdProduct.getName())
                .setDescription(createdProduct.getDescription())
                .setPrice(createdProduct.getPrice())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}