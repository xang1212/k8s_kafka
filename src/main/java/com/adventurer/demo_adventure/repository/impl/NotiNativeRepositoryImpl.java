package com.adventurer.demo_adventure.repository.impl;

import com.adventurer.demo_adventure.model.ItemModel;
import com.adventurer.demo_adventure.model.NotiModel;
import com.adventurer.demo_adventure.repository.NotiNativeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NotiNativeRepositoryImpl implements NotiNativeRepository {
    private final JdbcTemplate jdbcTemplate;
    public NotiNativeRepositoryImpl(JdbcTemplate jdbcTemplate) { this.jdbcTemplate =jdbcTemplate;}

    @Override
    public int insertNoti(NotiModel notiModel) {
        String sql = "INSERT INTO notification (message) VALUES (?)";

        int insertedRow = this.jdbcTemplate.update(sql, notiModel.getMessage());
        return insertedRow;
    }



    @Override
    public List<NotiModel> getAllNoti() {
        String sql = "SELECT * FROM notification";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            NotiModel notiModel = new NotiModel();
            notiModel.setNotiId(rs.getInt("noti_id"));
            notiModel.setMessage(rs.getString("message"));
            notiModel.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            notiModel.setUpdateAt(rs.getTimestamp("update_at").toLocalDateTime());
            return notiModel;
        });
    }


    @Override
    public int deleteNotiById(int notiId) {
        String sql = "DELETE FROM notification WHERE noti_id = ?";
        return jdbcTemplate.update(sql, notiId);
    }

    @Override
    public int updateNoti(NotiModel notiModel) {
        String sql = "UPDATE notification SET message = ?, update_at = CURRENT_TIMESTAMP WHERE noti_id = ?";
        int updatedRows = jdbcTemplate.update(sql, notiModel.getMessage(), notiModel.getNotiId());
        return updatedRows;
    }

}
