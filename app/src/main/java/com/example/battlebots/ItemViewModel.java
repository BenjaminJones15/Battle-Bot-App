package com.example.battlebots;
import androidx.lifecycle.ViewModel;

public class ItemViewModel extends ViewModel {

    public Integer Armor;
    public Integer Power;
    public Integer Scan;

    public String Name;

    public Integer getArmor() {
        return Armor;
    }

    public void setArmor(Integer armor) {
        Armor = armor;
    }

    public Integer getPower() {
        return Power;
    }

    public void setPower(Integer power) {
        Power = power;
    }

    public Integer getScan() {
        return Scan;
    }

    public void setScan(Integer scan) {
        Scan = scan;
    }

    public String getName() {return Name;}

    public void setName(String name) {Name = name;}

}
