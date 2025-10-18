package com.wecp.progressive.service.impl;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;

import com.wecp.progressive.entity.Cricketer;
import com.wecp.progressive.service.CricketerService;

public class CricketerServiceImplArraylist implements CricketerService {

    List<Cricketer> cricketers = new ArrayList<>();
    public CricketerServiceImplArraylist(){
        cricketers.add(new Cricketer(11, 1, "Kohli", 37, "Indian", 10, "Batsman", 35000, 10));
        cricketers.add(new Cricketer(10, 2, "Abhishek", 25, "Indian", 5, "Batsman", 5000, 5));
    }
    @Override
    public Integer addCricketer(Cricketer cricketer) {

        this.cricketers.add(cricketer);
        return cricketers.size();
    }

    @Override
    public void emptyArrayList() {

        //CricketerService.super.emptyArrayList();
        cricketers = new ArrayList<Cricketer>();
    }

    @Override
    public List<Cricketer> getAllCricketers() {

        return cricketers;
    }

    @Override
    public List<Cricketer> getAllCricketersSortedByExperience() {

        List<Cricketer> sortedCricketers = new ArrayList<>(this.cricketers);
        sortedCricketers.sort(Comparator.comparing(Cricketer :: getExperience));
        return sortedCricketers;
    }

    

}