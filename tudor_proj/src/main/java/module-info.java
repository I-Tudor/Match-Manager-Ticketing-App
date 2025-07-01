module Project.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires io.grpc.stub;
    requires io.grpc;
    requires io.grpc.protobuf;
    requires com.google.common;
    requires protobuf.java;
    requires java.annotation;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires spring.web;
    requires java.net.http;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.beans;
    requires spring.core;
    requires spring.aop;
    requires spring.context;
    requires com.fasterxml.jackson.databind;
    requires jakarta.annotation;


    opens org.example.test to spring.core, spring.beans, spring.context, spring.web;

    opens org.example.GUI;
    opens org.example.repository;
    exports org.example.GUI;
    exports org.example.repository;
    exports org.example.domain;
}