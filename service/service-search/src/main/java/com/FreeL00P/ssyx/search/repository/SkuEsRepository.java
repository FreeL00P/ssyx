package com.FreeL00P.ssyx.search.repository;

import com.FreeL00P.ssyx.model.search.SkuEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * SkuRepository
 *
 * @author fj
 * @since 2023/8/3 20:04
 */
/*
泛型解释 操作实体类，主键类型
 */
public interface SkuEsRepository extends ElasticsearchRepository<SkuEs,Long> {

}
