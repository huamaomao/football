package com.weixuan.shenxin.entity;

/**
 * 阵型
 * Created by Hua on 2014/8/10.
 */
public class Formation {
    private  int  formation;
    private int reverseFormation;
    /**** 各个位的值 A(个)*****/
    private  int  aValue;
    private  int  bValue;
    private  int  cValue;
    private  int  dValue;
    private  int  eValue;

    private int length;


    public Formation(int formation) {
        this.formation =formation;
        this.reverseFormation=reverse(formation);
        setLength(Integer.bitCount(formation));
        setaValue(reverseFormation % 10);
        setbValue(reverseFormation / 10 % 10);
        setcValue(reverseFormation / 100 % 10);
        setdValue(reverseFormation / 1000 % 10);
        seteValue(reverseFormation / 10000 % 10);
    }
    public Formation(String formation) {
        try {
            this.formation =Integer.valueOf(formation);
        }catch (Exception e){
        }
        this.reverseFormation=reverse(this.formation);
        setLength(Integer.bitCount(this.formation));
        setaValue(reverseFormation % 10);
        setbValue(reverseFormation / 10 % 10);
        setcValue(reverseFormation / 100 % 10);
        setdValue(reverseFormation / 1000 % 10);
        seteValue(reverseFormation / 10000 % 10);
    }

    @Override
    public String toString() {
        return "Formation{" +
                "formation=" + formation +
                ", reverseFormation=" + reverseFormation +
                ", aValue=" + aValue +
                ", bValue=" + bValue +
                ", cValue=" + cValue +
                ", dValue=" + dValue +
                ", fValue=" + eValue +
                ", length=" + length +
                '}';
    }

    int reverse(int n)
    {
        int res=0;
        int temp;
        while(n>0)
        {
            temp=n%10;
            res=res*10+temp;
            n/=10;
        }
        return res;
    }


    public int getReverseFormation() {
        return reverseFormation;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getFormation() {
        return formation;
    }

    public void setFormation(int formation) {
        this.formation = formation;
    }

    public int getaValue() {
        return aValue;
    }

    public void setaValue(int aValue) {
        this.aValue = aValue;
    }

    public int getbValue() {
        return bValue;
    }

    public void setbValue(int bValue) {
        this.bValue = bValue;
    }

    public int getcValue() {
        return cValue;
    }

    public void setcValue(int cValue) {
        this.cValue = cValue;
    }

    public int getdValue() {
        return dValue;
    }

    public void setdValue(int dValue) {
        this.dValue = dValue;
    }

    public int geteValue() {
        return eValue;
    }

    public void seteValue(int eValue) {
        this.eValue = eValue;
    }
}
