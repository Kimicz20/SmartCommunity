package edu.hdu.lab.services.impl;

import edu.hdu.lab.datasource.DataSource;
import edu.hdu.lab.mapper.CommunityCatMapper;
import edu.hdu.lab.pojo.Community;
import edu.hdu.lab.services.CommunityCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by geek on 2017/7/4.
 */
@Service
public class CommunityCatServiceImpl implements CommunityCatService {
    @Autowired
    private CommunityCatMapper communityCatMapper;

    @DataSource("smart_community_config")
    public List<Community> getAllCommunities() {
        return communityCatMapper.getAllCommunities();
    }

    public List<String> getAllDatabases() {
        return communityCatMapper.getAllDatabases();
    }
}
