package edu.hdu.lab.services;

import edu.hdu.lab.pojo.Community;

import java.util.List;

/**
 * Created by geek on 2017/7/4.
 */
public interface CommunityCatService {

    List<Community> getAllCommunities();

    List<String> getAllDatabases();
}
