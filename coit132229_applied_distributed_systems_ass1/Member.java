package coit132229_applied_distributed_systems_ass1;

import java.io.Serializable;

public class Member implements Serializable {
    private String MemberfirstName;
    private String MemberlastName;
    private String Memberaddress;
    private String MemberphoneNumber;
    private static final long serialVersionUID = 1L;
    public Member(String MemberfirstName, String MemberlastName, String Memberaddress, String MemberphoneNumber) {
        this.MemberfirstName = MemberfirstName;
        this.MemberlastName = MemberlastName;
        this.Memberaddress = Memberaddress;
        this.MemberphoneNumber = MemberphoneNumber;
    }

    // Getters and setters for each field
    public String getdataFirstName() {
        return MemberfirstName;
    }

    public void setFirstName(String MemberfirstName) {
        this.MemberfirstName = MemberfirstName;
    }

    public String getdataLastName() {
        return MemberlastName;
    }

    public void setLastName(String MemberlastName) {
        this.MemberlastName = MemberlastName;
    }

    public String getdataAddress() {
        return Memberaddress;
    }

    public void setAddress(String Memberaddress) {
        this.Memberaddress = Memberaddress;
    }

    public String getdataPhoneNumber() {
        return MemberphoneNumber;
    }

    public void setPhoneNumber(String MemberphoneNumber) {
        this.MemberphoneNumber = MemberphoneNumber;
    }

    
   
    // Override toString method for better representation
    @Override
    public String toString() {
        return "Member{" +
                "MemberfirstName='" + MemberfirstName + '\'' +
                ", MemberlastName='" + MemberlastName + '\'' +
                ", Memberaddress='" + Memberaddress + '\'' +
                ", MemberphoneNumber='" + MemberphoneNumber + '\'' +
                '}';
    }
    
    

    public String ToString() {
        return String.format("|%s\t|%s\t|%s\t|%s", MemberfirstName, MemberlastName, Memberaddress, MemberphoneNumber);
    }
}
