package com.zyc.springframework.Pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Repository
public class productRepository {


    // 这个jdbc连接的是mysql的
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static final String INSERT_SQL = "insert into product" +
            "(name,description) values (?,?)";


    public long countProduct() {
        return jdbcTemplate.queryForObject("select count(*) from product", Long.class);
    }

    public product queryProductById(long id) {
        return jdbcTemplate.queryForObject("select * from product where id = ?", rowMapper(), id);
    }

    private RowMapper<product> rowMapper() {
        return  (resultSet, rowNum) -> {
            return product.builder()
                    .id((int) resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .description(resultSet.getString("description"))
                    .build();
        };
    }

    public List<product> queryAllProduct() {
        return jdbcTemplate.query("select * from product", rowMapper());
    }

    public int insertProduct(List<product> products) {
        int[] count = jdbcTemplate.batchUpdate(INSERT_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                product product = products.get(i);
//                ps.setInt(1, product.getId());
                ps.setString(1, product.getName());
                ps.setString(2, product.getDescription());
            }

            @Override
            public int getBatchSize() {
                return products.size();
            }
        });
        return Arrays.stream(count).sum();
    }




}
