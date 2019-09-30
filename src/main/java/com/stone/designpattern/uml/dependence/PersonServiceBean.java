package com.stone.designpattern.uml.dependence;

/**
 * @author stone
 * @date 2019/9/20 15:11
 * description
 */
public class PersonServiceBean {
    private PersonDao personDao;

    public void save(Person person) {
    }

    public IDCard getIDCard(Integer personid) {
        return null;
    }

    public void modify() {
        Department department = new Department();
    }
}
