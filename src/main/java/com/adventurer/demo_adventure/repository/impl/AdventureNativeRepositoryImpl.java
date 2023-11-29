package com.adventurer.demo_adventure.repository.impl;

import com.adventurer.demo_adventure.model.AdventureModel;
import com.adventurer.demo_adventure.repository.AdventureNativeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdventureNativeRepositoryImpl implements AdventureNativeRepository {
    private JdbcTemplate jdbcTemplate;
    public AdventureNativeRepositoryImpl(JdbcTemplate jdbcTemplate) { this.jdbcTemplate =jdbcTemplate;}

    @Override
    public int insertAdventure(AdventureModel adventureModel) {
        List<Object> paraList = new ArrayList<>();

        String sql = "INSERT INTO adventures ( name, balance) VALUES";
        String value = "( ?, ?)";

        paraList.add(adventureModel.getName());
        paraList.add(adventureModel.getBalance());

        sql += value;

        int insertedRow = this.jdbcTemplate.update(sql, paraList.toArray());
        return insertedRow;
    }

    @Override
    public int updateAdventure(AdventureModel adventureModel) {
        String sql = "UPDATE adventures SET name = ?, balance = ?, update_at = CURRENT_TIMESTAMP WHERE adventure_id = ?";
        int updatedRows = jdbcTemplate.update(sql,
                adventureModel.getName(),
                adventureModel.getBalance(),
                adventureModel.getAdventureId());
        return updatedRows;
    }


    @Override
    public List<AdventureModel> getAllAdventures() {
        String sql = "SELECT * FROM adventures";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            AdventureModel adventureModel = new AdventureModel();
            adventureModel.setAdventureId(rs.getInt("adventure_id"));
            adventureModel.setName(rs.getString("name"));
            adventureModel.setBalance(rs.getBigDecimal("balance"));
            adventureModel.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            adventureModel.setUpdateAt(rs.getTimestamp("update_at").toLocalDateTime());
            return adventureModel;
        });
    }

    @Override
    public int deleteAdventureById(int adventureId) {
        String sql = "DELETE FROM adventures WHERE adventure_id = ?";
        return jdbcTemplate.update(sql, adventureId);
    }

    @Override
    public BigDecimal getAdventureBalance(int adventureId) {
        String selectBalanceSql = "SELECT balance FROM adventures WHERE adventure_id = ?";
        return jdbcTemplate.queryForObject(selectBalanceSql, new Object[]{adventureId}, BigDecimal.class);
    }


    @Override
    public void updateAdventuresBalance(BigDecimal price, int adventureSellerId, int adventureBuyerId) {
        String updateAdventuresSql = "UPDATE adventures SET balance = balance + ? WHERE adventure_id = ?";
        List<Object> adventureSellerParams = new ArrayList<>();
        adventureSellerParams.add(price);
        adventureSellerParams.add(adventureSellerId);

        List<Object> adventureBuyerParams = new ArrayList<>();
        adventureBuyerParams.add(price.negate());
        adventureBuyerParams.add(adventureBuyerId);

        jdbcTemplate.update(updateAdventuresSql, adventureSellerParams.toArray());
        jdbcTemplate.update(updateAdventuresSql, adventureBuyerParams.toArray());
    }
}
