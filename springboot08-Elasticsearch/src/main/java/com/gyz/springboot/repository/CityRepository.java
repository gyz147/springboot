package com.gyz.springboot.repository;

import com.gyz.springboot.pojo.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface CityRepository extends ElasticsearchRepository<City,Long> {


}
