package com.stone.designpattern.responsibilitychain;

public class SchoolMasterApprover extends Approver {
    public SchoolMasterApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if(purchaseRequest.getPrice() > 30000) {
            System.out.println("SchoolMasterApprover" + purchaseRequest.getId() + "," + this.name);
        }else {
            approver.processRequest(purchaseRequest);
        }
    }
}
