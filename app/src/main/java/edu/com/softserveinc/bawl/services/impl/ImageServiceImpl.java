package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.services.ImageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Illia on 11/23/2015.
 */
@Transactional
@Service
public class ImageServiceImpl implements ImageService {

    public static final Logger LOG = Logger.getLogger(IssueServiceImpl.class);

    @Override
    public void cropImage() {

    }
}
