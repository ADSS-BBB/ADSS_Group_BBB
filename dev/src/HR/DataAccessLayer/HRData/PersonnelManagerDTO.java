package HR.DataAccessLayer.HRData;

import HR.DomainLayer.Contract;
import HR.DomainLayer.PersonnelManager;

import java.util.HashMap;
import java.util.LinkedList;

public class PersonnelManagerDTO {
    private String name;

    public PersonnelManagerDTO(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonnelManager DTO2Object() {
        return new PersonnelManager(name);
    }
}
