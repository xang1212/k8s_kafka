package com.adventurer.demo_adventure.repository.impl;

import com.adventurer.demo_adventure.model.AdventureModel;
import com.adventurer.demo_adventure.model.ItemModel;
import com.adventurer.demo_adventure.repository.ItemNativeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemNativeRepositoryImpl implements ItemNativeRepository {

    private final JdbcTemplate jdbcTemplate;
    public ItemNativeRepositoryImpl(JdbcTemplate jdbcTemplate) { this.jdbcTemplate =jdbcTemplate;}

    @Override
    public int insertItem(ItemModel itemModel) {
        String sql = "INSERT INTO items (name, price, adventure_id) VALUES (?, ?, ?)";
        List<Object> paraList = new ArrayList<>();
        paraList.add(itemModel.getName());
        paraList.add(itemModel.getPrice());
        paraList.add(itemModel.getAdventureId());
        return this.jdbcTemplate.update(sql, paraList.toArray());
    }

    @Override
    public int updateItem(ItemModel itemModel) {
        String sql = "UPDATE items SET name = ?, price = ?, adventure_id = ?, update_at = CURRENT_TIMESTAMP WHERE item_id = ?";
        List<Object> paraList = new ArrayList<>();
        paraList.add(itemModel.getName());
        paraList.add(itemModel.getPrice());
        paraList.add(itemModel.getAdventureId());
        paraList.add(itemModel.getItemId());

        return this.jdbcTemplate.update(sql, paraList.toArray());
    }

    @Override
    public List<ItemModel> getAllItems() {
        String sql = "SELECT * FROM items";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ItemModel itemModel = new ItemModel();
            itemModel.setItemId(rs.getInt("item_id"));
            itemModel.setName(rs.getString("name"));
            itemModel.setPrice(rs.getBigDecimal("price"));
            itemModel.setAdventureId(rs.getInt("adventure_id"));
            itemModel.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            itemModel.setUpdateAt(rs.getTimestamp("update_at").toLocalDateTime());
            return itemModel;
        });
    }

    @Override
    public List<ItemModel> getItemsByAdventureId(int adventureId) {
        String sql = "SELECT * FROM items WHERE adventure_id = ?";
        return jdbcTemplate.query(sql, new Object[]{adventureId}, (rs, rowNum) -> {
            ItemModel itemModel = new ItemModel();
            itemModel.setItemId(rs.getInt("item_id"));
            itemModel.setName(rs.getString("name"));
            itemModel.setPrice(rs.getBigDecimal("price"));
            itemModel.setAdventureId(rs.getInt("adventure_id"));
            itemModel.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            itemModel.setUpdateAt(rs.getTimestamp("update_at").toLocalDateTime());
            return itemModel;
        });
    }


    @Override
    public int deleteItemById(int itemId) {
        String sql = "DELETE FROM items WHERE item_id = ?";
        return jdbcTemplate.update(sql, itemId);
    }


    @Override
    public BigDecimal getItemPrice(int itemId) {

            String selectPriceSql = "SELECT price FROM items WHERE item_id = ?";
            BigDecimal price = jdbcTemplate.queryForObject(selectPriceSql, new Object[]{itemId}, BigDecimal.class);

            if (price == null) {
                throw new RuntimeException("Item price not found for itemId: " + itemId);
            }
            return price;
    }


    @Override
    public int updateItems(int itemId, int adventureBuyerId) {
        String updateItemsSql = "UPDATE items SET adventure_id = ? WHERE item_id = ?";
        List<Object> itemsParams = new ArrayList<>();
        itemsParams.add(adventureBuyerId);
        itemsParams.add(itemId);

        return jdbcTemplate.update(updateItemsSql, itemsParams.toArray());
    }

}
