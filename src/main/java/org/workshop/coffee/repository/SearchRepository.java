package org.workshop.coffee.repository;

import org.workshop.coffee.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

@Repository
public class SearchRepository {

    @Autowired
    EntityManager em;

    @Autowired
    DataSource dataSource;

    public List<Product> searchProduct (String input) {
        //lowercase the input
        input = input.toLowerCase(Locale.ROOT);
        //create a query to search for the input in the product description and product name
        var query = em.createNativeQuery("Select * from Product where lower(description) like '%" + input + "%' OR lower(product_name) like '%" + input + "%'", Product.class);
        //get the result list
        var resultList = (List<Product>) query.getResultList();
        return resultList;
    }

}
