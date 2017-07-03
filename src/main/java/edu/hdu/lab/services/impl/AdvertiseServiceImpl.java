package edu.hdu.lab.services.impl;

import edu.hdu.lab.mapper.AdvertiseClickMapper;
import edu.hdu.lab.mapper.AdvertiseMapper;
import edu.hdu.lab.model.Advertise;
import edu.hdu.lab.model.AdvertiseClick;
import edu.hdu.lab.model.AdvertiseExample;
import edu.hdu.lab.services.AdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justin on 15-7-1.
 */
@Service("AdvertiseService")
public class AdvertiseServiceImpl
    implements AdvertiseService{

    @Autowired
    private AdvertiseMapper advertiseMapper;

    @Autowired
    private AdvertiseClickMapper clickMapper;

    public List<Advertise> getAdvertises(Advertise ad) {
        if (ad == null)
            return new ArrayList<Advertise>();

        return advertiseMapper.getAdvertises(ad);
    }

    public Advertise getAdvertiseById(int id) {
        return advertiseMapper.selectByPrimaryKey(id);
    }

    public int addNewAdvertise(Advertise ad) {
        return
                advertiseMapper.insertSelective(ad);
    }

    public int updateAdvertise(Advertise ad) {
        return
                advertiseMapper.updateByPrimaryKeySelective(ad);
    }

    public List<AdvertiseClick> getAdvertiseClicks(AdvertiseClick click) {
        return
                clickMapper.getAdvertiseClicks(click);
    }

    public int addNewAdvertiseClick(AdvertiseClick click) {
        return
                clickMapper.insertSelective(click);
    }
}
