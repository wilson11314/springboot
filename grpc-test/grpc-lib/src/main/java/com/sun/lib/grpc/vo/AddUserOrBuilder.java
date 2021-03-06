// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user_vo.proto

package com.sun.lib.grpc.vo;

public interface AddUserOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.sun.lib.grpc.vo.AddUser)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>int32 age = 2;</code>
   * @return The age.
   */
  int getAge();

  /**
   * <code>string address = 3;</code>
   * @return The address.
   */
  java.lang.String getAddress();
  /**
   * <code>string address = 3;</code>
   * @return The bytes for address.
   */
  com.google.protobuf.ByteString
      getAddressBytes();

  /**
   * <code>repeated string phone = 4;</code>
   * @return A list containing the phone.
   */
  java.util.List<java.lang.String>
      getPhoneList();
  /**
   * <code>repeated string phone = 4;</code>
   * @return The count of phone.
   */
  int getPhoneCount();
  /**
   * <code>repeated string phone = 4;</code>
   * @param index The index of the element to return.
   * @return The phone at the given index.
   */
  java.lang.String getPhone(int index);
  /**
   * <code>repeated string phone = 4;</code>
   * @param index The index of the value to return.
   * @return The bytes of the phone at the given index.
   */
  com.google.protobuf.ByteString
      getPhoneBytes(int index);
}
