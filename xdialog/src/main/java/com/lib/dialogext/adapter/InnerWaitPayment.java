package com.lib.dialogext.adapter;

public class InnerWaitPayment implements Cloneable {
    public String Paykey;
    public String payValue;


    @Override
    public InnerWaitPayment clone() throws CloneNotSupportedException {
        InnerWaitPayment innerWaitPayment = (InnerWaitPayment) super.clone();
        return innerWaitPayment;
    }
}
