// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R2)
// Generated source version: 1.1.3

package com.sun.j2ee.blueprints.consumerwebsite.actions;


public class ContactInfo {
    protected com.sun.j2ee.blueprints.consumerwebsite.actions.Address address;
    protected java.lang.String email;
    protected java.lang.String familyName;
    protected java.lang.String givenName;
    protected java.lang.String phone;
    
    public ContactInfo() {
    }
    
    public ContactInfo(com.sun.j2ee.blueprints.consumerwebsite.actions.Address address, java.lang.String email, java.lang.String familyName, java.lang.String givenName, java.lang.String phone) {
        this.address = address;
        this.email = email;
        this.familyName = familyName;
        this.givenName = givenName;
        this.phone = phone;
    }
    
    public com.sun.j2ee.blueprints.consumerwebsite.actions.Address getAddress() {
        return address;
    }
    
    public void setAddress(com.sun.j2ee.blueprints.consumerwebsite.actions.Address address) {
        this.address = address;
    }
    
    public java.lang.String getEmail() {
        return email;
    }
    
    public void setEmail(java.lang.String email) {
        this.email = email;
    }
    
    public java.lang.String getFamilyName() {
        return familyName;
    }
    
    public void setFamilyName(java.lang.String familyName) {
        this.familyName = familyName;
    }
    
    public java.lang.String getGivenName() {
        return givenName;
    }
    
    public void setGivenName(java.lang.String givenName) {
        this.givenName = givenName;
    }
    
    public java.lang.String getPhone() {
        return phone;
    }
    
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }
}
