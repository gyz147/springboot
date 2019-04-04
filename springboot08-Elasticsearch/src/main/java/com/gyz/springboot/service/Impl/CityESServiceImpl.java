package com.gyz.springboot.service.Impl;

import com.gyz.springboot.pojo.City;
import com.gyz.springboot.repository.CityRepository;
import com.gyz.springboot.service.CityService;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 城市 ES 业务逻辑实现类
 *
 */
@Service
public class CityESServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityESServiceImpl.class);

    @Autowired
    CityRepository cityRepository;

    @Override
    public Long saveCity(City city) {

        City cityResult = cityRepository.save(city);
        return cityResult.getId();
    }

    @Override
    public List<City> searchCity(Integer pageNumber,
                                 Integer pageSize,
                                 String searchContent) {
        // 分页参数
        Pageable pageable = new PageRequest(pageNumber, pageSize);

        /**
         * spring-boot-starter-data-elasticsearch
         * 老版本权重查询方法  例如2.x
         */
        // Function Score Query
//        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("cityname", searchContent)),
//                    ScoreFunctionBuilders.weightFactorFunction(1000))
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
//                        ScoreFunctionBuilders.weightFactorFunction(100));

        /**
         * spring-boot-starter-data-elasticsearch
         * 较新版本权重查询方法  5.x以上
         */
        // 权重查询
        List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
        filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("cityname", searchContent),
                ScoreFunctionBuilders.weightFactorFunction(1000)));
        filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("description", searchContent),
                ScoreFunctionBuilders.weightFactorFunction(100)));
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
        filterFunctionBuilders.toArray(builders);
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                .setMinScore(2);

        // 创建搜索 DSL 查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();

        LOGGER.info("\n searchCity(): searchContent [" + searchContent + "] \n DSL  = \n " + searchQuery.getQuery().toString());

        Page<City> searchPageResults = cityRepository.search(searchQuery);
        return searchPageResults.getContent();
    }

}
