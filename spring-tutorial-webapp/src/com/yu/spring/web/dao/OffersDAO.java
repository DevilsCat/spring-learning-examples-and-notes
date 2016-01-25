package com.yu.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("offersDao")
public class OffersDAO {

    private NamedParameterJdbcTemplate jdbc;
    
    /**
     * JDBC template wrap the data source. data source is coming from bean
     * dataSource
     * 
     * @param jdbc
     */
    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    /**
     * This method create a anonymous {@link RowMapper} specify the routine of
     * generating Offer Object given retrieved database query result.
     * 
     * @return
     */
    public List<Offer> getOffers() {
        // Prefix placehold by column and name.
        return jdbc.query("select * from offers, users where offers.username=users.username and users.enabled=true",
                new OfferRowMapper());
    }
    
    private static String GET_OFFERS_BY_USERNAME = 
              "select * from offers, users " + 
              "where offers.username=users.username and users.enabled=true and offers.username = :username";
    
    public List<Offer> getOffers(String username) {
        // Prefix placehold by column and name.
        return jdbc.query(GET_OFFERS_BY_USERNAME,
                new MapSqlParameterSource("username", username),
                new OfferRowMapper()
        );
    }

    /**
     * Create an {@link Offer} entry to database.
     * 
     * @param offer
     * @return
     */
    public boolean create(Offer offer) {
        // It will look into the bean for properties.
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);

        return jdbc.update("insert into offers (username, text) values (:username, :text)", params) == 1;
    }

    /**
     * Create multiple {@link Offer} entries to database. Transaction annotation
     * makes sure only all database operations success, then the changes commit,
     * otherwise roll back.
     * 
     * @param offers
     * @return
     */
    @Transactional
    public int[] create(List<Offer> offers) {
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offers.toArray());

        return jdbc.batchUpdate("insert into offers (username, text) values (:username, :text)", params);
    }

    /**
     * Delete a given id from database.
     * 
     * @param id
     * @return Number of row affected
     */
    public int delete(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);

        return jdbc.update("delete from offers where id=:id", params);
    }

    /**
     * Update an entry {@link Offer} to database.
     * 
     * @param offer
     * @return
     */
    public boolean update(Offer offer) {
        // It will look into the bean for properties.
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                offer);

        return jdbc.update("update offers set text=:text where id=:id", params) == 1;

    }

    public Offer getOffer(int id) {
        // "id" <--> :id, Sue is the replace string.
        // Use params.addValue to add many placeholders.
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        // Prefix placeholder by column and name.
        return jdbc.queryForObject("select * from offers, users where id = :id", params, 
                new OfferRowMapper());
    }
}
