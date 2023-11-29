package com.adventurer.demo_adventure.repository.impl;

import com.adventurer.demo_adventure.model.MarketPlaceModel;
import com.adventurer.demo_adventure.model.MarketPlaceQueryModel;
import com.adventurer.demo_adventure.repository.AdventureNativeRepository;
import com.adventurer.demo_adventure.repository.ItemNativeRepository;
import com.adventurer.demo_adventure.repository.MarketPlaceNativeRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MarketplaceNativeRepositoryImpl implements MarketPlaceNativeRepository {

    private final JdbcTemplate jdbcTemplate;
    private AdventureNativeRepository adventureNativeRepository;

    private ItemNativeRepository itemNativeRepository;
    public MarketplaceNativeRepositoryImpl(JdbcTemplate jdbcTemplate,
                                           AdventureNativeRepository adventureNativeRepository,
                                           ItemNativeRepository itemNativeRepository
                                           ) {
        this.jdbcTemplate =jdbcTemplate;
        this.adventureNativeRepository = adventureNativeRepository;
        this.itemNativeRepository = itemNativeRepository;

    }
//    @Override
//    public int insertMarketPlace(MarketPlaceModel marketPlaceModel) {
//        String sql = "INSERT INTO market_places (adventure_seller_id, item_id) VALUES (?, ?)";
//        List<Object> paraList = new ArrayList<>();
//        paraList.add(marketPlaceModel.getAdventureSellerId());
//        paraList.add(marketPlaceModel.getItemId());
//        return this.jdbcTemplate.update(sql, paraList.toArray());
//    }

    @Override
    public List<MarketPlaceModel> getAllMarketPlaces() {
        String sql = "SELECT * FROM market_places";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            MarketPlaceModel marketPlaceModel = new MarketPlaceModel();
            marketPlaceModel.setAdventureSellerId(rs.getInt("adventure_seller_id"));
            marketPlaceModel.setItemId(rs.getInt("item_id"));
            marketPlaceModel.setStatus(rs.getString("status"));
            marketPlaceModel.setAdventureBuyerId(rs.getInt("adventure_buyer_id"));
            marketPlaceModel.setNotiId(rs.getInt("noti_id"));
            return marketPlaceModel;
        });
    }

//getMarketPlacesByAdventure
@Override
public List<MarketPlaceQueryModel> getMarketPlacesByAdventure(int adventureSellerId) {
    String sql = "SELECT mp.mp_id, mp.status, mp.adventure_seller_id, a_seller.name AS adventure_seller_name, mp.item_id, i.name AS item_name, mp.adventure_buyer_id, a_buyer.name AS adventure_buyer_name, mp.noti_id, n.message, mp.created_at, mp.update_at " +
            "FROM market_places mp " +
            "INNER JOIN adventures a_seller ON mp.adventure_seller_id = a_seller.adventure_id " +
            "INNER JOIN items i ON mp.item_id = i.item_id " +
            "LEFT JOIN adventures a_buyer ON mp.adventure_buyer_id = a_buyer.adventure_id " +
            "INNER JOIN notification n ON mp.noti_id = n.noti_id " +
            "where adventure_seller_id = ? " +
            "ORDER BY noti_id DESC";

    return jdbcTemplate.query(sql, new Object[]{adventureSellerId}, (rs, rowNum) -> {
        MarketPlaceQueryModel marketPlaceModel = new MarketPlaceQueryModel();
        marketPlaceModel.setMpId(rs.getInt("mp_id"));
        marketPlaceModel.setStatus(rs.getString("status"));
        marketPlaceModel.setAdventureSellerId(rs.getInt("adventure_seller_id"));
        marketPlaceModel.setAdventureSellerName(rs.getString("adventure_seller_name"));
        marketPlaceModel.setItemId(rs.getInt("item_id"));
        marketPlaceModel.setItemName(rs.getString("item_name"));
        marketPlaceModel.setAdventureBuyerId(rs.getInt("adventure_buyer_id"));
        marketPlaceModel.setAdventureBuyerName(rs.getString("adventure_buyer_name"));
        marketPlaceModel.setNotiId(rs.getInt("noti_id"));
        marketPlaceModel.setMessage(rs.getString("message"));
        marketPlaceModel.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        marketPlaceModel.setUpdateAt(rs.getTimestamp("update_at").toLocalDateTime());
        return marketPlaceModel;
    });
}


    @Override
    public List<MarketPlaceQueryModel> getAllNativeMarketPlaces() {
        String sql = "SELECT mp.mp_id, mp.status, mp.adventure_seller_id, a_seller.name AS adventure_seller_name, mp.item_id, i.name AS item_name, mp.adventure_buyer_id, a_buyer.name AS adventure_buyer_name, mp.noti_id, n.message, mp.created_at, mp.update_at " +
                "FROM market_places mp " +
                "INNER JOIN adventures a_seller ON mp.adventure_seller_id = a_seller.adventure_id " +
                "INNER JOIN items i ON mp.item_id = i.item_id " +
                "LEFT JOIN adventures a_buyer ON mp.adventure_buyer_id = a_buyer.adventure_id " +
                "INNER JOIN notification n ON mp.noti_id = n.noti_id " +
                "ORDER BY noti_id DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            MarketPlaceQueryModel marketPlaceModel = new MarketPlaceQueryModel();
            marketPlaceModel.setMpId(rs.getInt("mp_id"));
            marketPlaceModel.setStatus(rs.getString("status"));
            marketPlaceModel.setAdventureSellerId(rs.getInt("adventure_seller_id"));
            marketPlaceModel.setAdventureSellerName(rs.getString("adventure_seller_name"));
            marketPlaceModel.setItemId(rs.getInt("item_id"));
            marketPlaceModel.setItemName(rs.getString("item_name"));
            marketPlaceModel.setAdventureBuyerId(rs.getInt("adventure_buyer_id"));
            marketPlaceModel.setAdventureBuyerName(rs.getString("adventure_buyer_name"));
            marketPlaceModel.setNotiId(rs.getInt("noti_id"));
            marketPlaceModel.setMessage(rs.getString("message"));
            marketPlaceModel.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            marketPlaceModel.setUpdateAt(rs.getTimestamp("update_at").toLocalDateTime());
            return marketPlaceModel;
        });
    }

    @Override
    public List<MarketPlaceQueryModel> getAllMarketPlacesNotSell() {
        String sql = "SELECT mp.mp_id, mp.status, mp.adventure_seller_id, a_seller.name AS adventure_seller_name, mp.item_id, i.name AS item_name, mp.adventure_buyer_id, a_buyer.name AS adventure_buyer_name, mp.noti_id, n.message, mp.created_at, mp.update_at " +
                "FROM market_places mp " +
                "INNER JOIN adventures a_seller ON mp.adventure_seller_id = a_seller.adventure_id " +
                "INNER JOIN items i ON mp.item_id = i.item_id " +
                "LEFT JOIN adventures a_buyer ON mp.adventure_buyer_id = a_buyer.adventure_id " +
                "INNER JOIN notification n ON mp.noti_id = n.noti_id " +
                "where n.noti_id = 1 " +
                "ORDER BY noti_id DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            MarketPlaceQueryModel marketPlaceModel = new MarketPlaceQueryModel();
            marketPlaceModel.setMpId(rs.getInt("mp_id"));
            marketPlaceModel.setStatus(rs.getString("status"));
            marketPlaceModel.setAdventureSellerId(rs.getInt("adventure_seller_id"));
            marketPlaceModel.setAdventureSellerName(rs.getString("adventure_seller_name"));
            marketPlaceModel.setItemId(rs.getInt("item_id"));
            marketPlaceModel.setItemName(rs.getString("item_name"));
            marketPlaceModel.setAdventureBuyerId(rs.getInt("adventure_buyer_id"));
            marketPlaceModel.setAdventureBuyerName(rs.getString("adventure_buyer_name"));
            marketPlaceModel.setNotiId(rs.getInt("noti_id"));
            marketPlaceModel.setMessage(rs.getString("message"));
            marketPlaceModel.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            marketPlaceModel.setUpdateAt(rs.getTimestamp("update_at").toLocalDateTime());
            return marketPlaceModel;
        });
    }


    @Override
    public int deleteMarketPlaceById(int mpId) {
        return 0;
    }

    private int updateMarketPlace(int mpId, MarketPlaceModel marketPlaceModel) {
        String updateMarketPlaceSql = "UPDATE market_places SET adventure_seller_id = ?, adventure_buyer_id = ?, noti_id = ?, status = ? WHERE mp_id = ?";
        List<Object> marketPlaceParams = new ArrayList<>();
        marketPlaceParams.add(marketPlaceModel.getAdventureSellerId());
        marketPlaceParams.add(marketPlaceModel.getAdventureBuyerId());
        marketPlaceParams.add(2);
        marketPlaceParams.add("sold out");
        marketPlaceParams.add(mpId);

        return jdbcTemplate.update(updateMarketPlaceSql, marketPlaceParams.toArray());
    }

    @Override
    @Transactional
    public int updateMarketPlaceByMpId(int mpId, MarketPlaceModel marketPlaceModel) {
        try {
            BigDecimal price = this.itemNativeRepository.getItemPrice(marketPlaceModel.getItemId());

            if (price == null) {
                throw new RuntimeException("Item not found.");
            }

            int updatedMarketPlaceRows = updateMarketPlace(mpId, marketPlaceModel);
            int updatedItemsRows = this.itemNativeRepository.updateItems(marketPlaceModel.getItemId(), marketPlaceModel.getAdventureBuyerId());
            this.adventureNativeRepository.updateAdventuresBalance(price, marketPlaceModel.getAdventureSellerId(), marketPlaceModel.getAdventureBuyerId());

            if (updatedMarketPlaceRows > 0 && updatedItemsRows > 0) {
                return updatedMarketPlaceRows;
            } else {
                throw new RuntimeException("Update operation failed.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public MarketPlaceModel getMarketPlaceByMpId(int mpId) {
        try {
            String selectMarketPlaceSql = "SELECT * FROM market_places WHERE mp_id = ?";
            return jdbcTemplate.queryForObject(selectMarketPlaceSql, new Object[]{mpId}, new BeanPropertyRowMapper<>(MarketPlaceModel.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
