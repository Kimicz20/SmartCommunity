package edu.hdu.lab.mapper;

import edu.hdu.lab.pojo.Community;

import java.util.List;

/**
 * Created by geek on 2017/7/4.
 */
public interface CommunityCatMapper {

    List<Community> getAllCommunities();

    List<String> getAllDatabases();
}
