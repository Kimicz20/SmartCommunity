package edu.hdu.lab.services;

import edu.hdu.lab.model.Advertise;
import edu.hdu.lab.model.AdvertiseClick;

import java.util.List;

/**
 * Created by justin on 15-7-1.
 */
public interface AdvertiseService {

    List<Advertise> getAdvertises(Advertise ad);

    Advertise getAdvertiseById(int id);

    int addNewAdvertise(Advertise ad);

    int updateAdvertise(Advertise ad);

    List<AdvertiseClick> getAdvertiseClicks(AdvertiseClick click);

    int addNewAdvertiseClick(AdvertiseClick click);
}
