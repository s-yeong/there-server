package com.there.src.s3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Repository
public class S3Dao {
    private JdbcTemplate jdbcTemplate;

    @Resource
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /**
     * 히스토리 사진 업로드
     */
    public void uploadHistoryPicture(String imgPath, int historyIdx) {
        String uploadHistoryPictureQuery = "insert into historyPicture(imgUrl, historyIdx) VALUES (?,?);";
        Object[] uploadHistoryPictureParams = new Object[]{imgPath, historyIdx};
        this.jdbcTemplate.update(uploadHistoryPictureQuery, uploadHistoryPictureParams);
    }


    /**
     * 히스토리 사진 삭제 (사진 일괄 삭제)
     */
    public void delHistoryAllPicture(int historyIdx) {
        String delHistoryPictureQuery = "delete from historyPicture where historyIdx=?;";
        this.jdbcTemplate.update(delHistoryPictureQuery, historyIdx);
    }

}
