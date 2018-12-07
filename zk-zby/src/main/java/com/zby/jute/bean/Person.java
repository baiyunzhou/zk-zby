// File generated by hadoop record compiler. Do not edit.
/**
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.zby.jute.bean;

import org.apache.jute.*;
public class Person implements Record {
  private String username;
  private int age;
  public Person() {
  }
  public Person(
        String username,
        int age) {
    this.username=username;
    this.age=age;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String m_) {
    username=m_;
  }
  public int getAge() {
    return age;
  }
  public void setAge(int m_) {
    age=m_;
  }
  public void serialize(OutputArchive a_, String tag) throws java.io.IOException {
    a_.startRecord(this,tag);
    a_.writeString(username,"username");
    a_.writeInt(age,"age");
    a_.endRecord(this,tag);
  }
  public void deserialize(InputArchive a_, String tag) throws java.io.IOException {
    a_.startRecord(tag);
    username=a_.readString("username");
    age=a_.readInt("age");
    a_.endRecord(tag);
}
  public String toString() {
    try {
      java.io.ByteArrayOutputStream s =
        new java.io.ByteArrayOutputStream();
      CsvOutputArchive a_ = 
        new CsvOutputArchive(s);
      a_.startRecord(this,"");
    a_.writeString(username,"username");
    a_.writeInt(age,"age");
      a_.endRecord(this,"");
      return new String(s.toByteArray(), "UTF-8");
    } catch (Throwable ex) {
      ex.printStackTrace();
    }
    return "ERROR";
  }
  public void write(java.io.DataOutput out) throws java.io.IOException {
    BinaryOutputArchive archive = new BinaryOutputArchive(out);
    serialize(archive, "");
  }
  public void readFields(java.io.DataInput in) throws java.io.IOException {
    BinaryInputArchive archive = new BinaryInputArchive(in);
    deserialize(archive, "");
  }
  public int compareTo (Object peer_) throws ClassCastException {
    if (!(peer_ instanceof Person)) {
      throw new ClassCastException("Comparing different types of records.");
    }
    Person peer = (Person) peer_;
    int ret = 0;
    ret = username.compareTo(peer.username);
    if (ret != 0) return ret;
    ret = (age == peer.age)? 0 :((age<peer.age)?-1:1);
    if (ret != 0) return ret;
     return ret;
  }
  public boolean equals(Object peer_) {
    if (!(peer_ instanceof Person)) {
      return false;
    }
    if (peer_ == this) {
      return true;
    }
    Person peer = (Person) peer_;
    boolean ret = false;
    ret = username.equals(peer.username);
    if (!ret) return ret;
    ret = (age==peer.age);
    if (!ret) return ret;
     return ret;
  }
  public int hashCode() {
    int result = 17;
    int ret;
    ret = username.hashCode();
    result = 37*result + ret;
    ret = (int)age;
    result = 37*result + ret;
    return result;
  }
  public static String signature() {
    return "LPerson(si)";
  }
}