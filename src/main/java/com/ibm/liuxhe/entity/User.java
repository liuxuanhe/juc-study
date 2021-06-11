package com.ibm.liuxhe.entity;

public class User {

  private int id;
  private String userName;
  private String passWord;

  public String publicName;
  String defaultName;
  protected String protectedName;

  public String getPublicName() {
    return publicName;
  }

  public void setPublicName(String publicName) {
    this.publicName = publicName;
  }

  public String getDefaultName() {
    return defaultName;
  }

  public void setDefaultName(String defaultName) {
    this.defaultName = defaultName;
  }

  public String getProtectedName() {
    return protectedName;
  }

  public void setProtectedName(String protectedName) {
    this.protectedName = protectedName;
  }

  public User() {
  }

  public User(int id, String userName, String passWord) {
    this.id = id;
    this.userName = userName;
    this.passWord = passWord;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", userName='" + userName + '\'' +
        ", passWord='" + passWord + '\'' +
        ", publicName='" + publicName + '\'' +
        ", defaultName='" + defaultName + '\'' +
        ", protectedName='" + protectedName + '\'' +
        '}';
  }
}
