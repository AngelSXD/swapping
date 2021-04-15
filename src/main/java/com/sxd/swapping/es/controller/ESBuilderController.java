package com.sxd.swapping.es.controller;

import com.sxd.swapping.mybatis.vo.UniVerResponse;
import com.sxd.swapping.es.domain.Builder;
import com.sxd.swapping.es.dao.BuilderDao;
import org.elasticsearch.index.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 使用方式有两种：
 * 1.一种是经过 SpringData 封装过的，直接在 dao 接口继承 ElasticsearchRepository 即可
 * 2.一种是经过 Spring 封装过的，直接在 Service/Controller 中引入该 bean 即可 ElasticsearchTemplate
 */
@RestController
@RequestMapping("/es")
public class ESBuilderController {

    @Autowired
    BuilderDao builderDao;

    /**
     * 方式1
     *
     * 单个保存索引
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UniVerResponse<Builder> save(@RequestBody Builder builder){
        builder = builder == null ? new Builder() : builder;
        UniVerResponse<Builder> res = new UniVerResponse<>();
        Builder builder2 = builderDao.save(builder);
        res.beTrue(builder2);
        return res;
    }


    /**
     * 方式1
     *
     * 根据ID获取单个索引
     * @param id
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public UniVerResponse<Builder> get(Long id){
        UniVerResponse<Builder> res = new UniVerResponse<>();
        Optional<Builder> get = builderDao.findById(id);
        res.beTrue(get.isPresent() == false ? null : get.get());
        return res;
    }


    /**
     * ============================单条件查询==================================
     */

    /**
     * 方式1
     *
     * 通过match进行模糊查询
     * 根据传入属性值进行分词，检索指定属性下是否有匹配
     *
     * 例如：
     * name:中国人
     * 那么查询会将 中国人 进行分词， 中国  人  国人 等。之后再进行查询匹配
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/searchNameByMatch", method = RequestMethod.GET)
    public UniVerResponse<List<Builder>> searchNameByMatch(String name){
        UniVerResponse<List<Builder>> res = new UniVerResponse<>();
        MatchQueryBuilder matchBuilder = QueryBuilders.matchQuery("buildName",name);
        Iterable<Builder> search = builderDao.search(matchBuilder);
        Iterator<Builder> iterator = search.iterator();
        List<Builder> list = new ArrayList<>();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }

        res.beTrue(list);
        return res;
    }


    /**
     *  方式1
     *
     *  通过term进行全量完全匹配查询
     *  根据传入属性值，检索指定属性下是否有属性值完全匹配的
     *
     *  例如：
     *  name:中国人
     *  那么查询不会进行分词，就是按照  完整的  中国人  进行查询匹配
     *
     * 此时ik中文分词 并没有起作用【此时是在@Field注解 指定的ik分词器】
     * 例如存入  张卫健  三个字，以ik_max_word 分词存入，查询也指定以ik查询，但是  以张卫健  查询 没有结果
     * 以   【张】   或   【卫】   或   【健】  查询 才有结果，说明分词是以默认分词器 进行分词 ，也就是一个中文汉字 进行一个分词的效果。
     *
     *
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/searchNameByTerm", method = RequestMethod.GET)
    public UniVerResponse<List<Builder>> searchNameByTerm(String name){
        UniVerResponse<List<Builder>> res = new UniVerResponse<>();
        TermQueryBuilder termBuilder = QueryBuilders.termQuery("buildName",name);
        Iterable<Builder> search = builderDao.search(termBuilder);
        Iterator<Builder> iterator = search.iterator();
        List<Builder> list = new ArrayList<>();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }

        res.beTrue(list);
        return res;
    }


    /**
     * 方式1
     *
     * 根据range进行范围查询
     *
     * 时间也可以进行范围查询，但时间传入值应该为yyyy-MM-dd HH:mm:ss 格式的时间字符串或时间戳 或其他定义的时间格式
     * 只有在mapping中定义的时间格式，才能被ES查询解析成功
     *
     * @param num
     * @return
     */
    @RequestMapping(value = "/searchNumByRange", method = RequestMethod.GET)
    public UniVerResponse<List<Builder>> searchNumByRange(Integer num){
        UniVerResponse<List<Builder>> res = new UniVerResponse<>();
        RangeQueryBuilder rangeBuilder = QueryBuilders.rangeQuery("buildNum").gt(0).lt(num);
        Iterable<Builder> search = builderDao.search(rangeBuilder);
        Iterator<Builder> iterator = search.iterator();
        List<Builder> list = new ArrayList<>();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }

        res.beTrue(list);
        return res;
    }


    //处理GET请求的时间转化
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    /**
     * ============================复合条件查询==================================
     * =========================filter和query的区别==============================
     */

    /**
     * 方式1
     *
     * 使用bool进行复合查询，使用filter比must query性能好
     *
     * filter是过滤，1.文档是否匹配 2.不涉及评分 3.更快  4.会自动缓存，下次查询速度会更快
     * query是查询，1.文档是否匹配查詢，相关度高不高 2.计算文档匹配评分 3.速度慢 4.查询的结果要比filter可能更多一些，因为涉及评分，所以更精确
     *
     *  建议配合使用，先filter过滤大范围，再query精确查询
     *
     * @param builder
     * @return
     */
    @RequestMapping(value = "/searchByBool", method = RequestMethod.GET)
    public UniVerResponse<Page<Builder>>  searchByBool(Builder builder){
        UniVerResponse<Page<Builder>> res = new UniVerResponse<>();
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();

        //多个字段匹配 属性值 must query
        MultiMatchQueryBuilder matchQueryBuilder =
                QueryBuilders.multiMatchQuery(builder.getBuildName(),"buildName","buildName2");
        boolBuilder.must(matchQueryBuilder);

        //filter 分别过滤不同字段，缩小筛选范围
        TermQueryBuilder numQuery = QueryBuilders.termQuery("buildNum",builder.getBuildNum());
        boolBuilder.filter(numQuery);

        RangeQueryBuilder dateQuery = QueryBuilders.rangeQuery("buildDate").lt(builder.getBuildDate().getTime());
        boolBuilder.filter(dateQuery);

        //排序 + 分页
        Sort sort = Sort.by(Sort.Direction.DESC,"buildNum");
        PageRequest pageRequest = PageRequest.of(builder.getPageNum()-1,builder.getPageSize(),sort);

        Page<Builder> search = builderDao.search(boolBuilder, pageRequest);
        res.beTrue(search);
        return res;
    }

    /**
     * 方式1
     * 时间范围查询
     * ES中时间字段需要设置为 date类型，才能查询时间范围
     * 时间范围要想准确查询，需要将时间转化为时间戳进行查询
     *
     * ES中date字段存储是 时间戳存储
     *
     *
     * from[包含]  -   to[包含]
     * gt - lt
     * gte - lte
     *
     *
     *
     * @return
     */
    @RequestMapping(value = "/searchByTimeRange", method = RequestMethod.GET)
    public UniVerResponse<List<Builder>> searchByTimeRange(Builder builder){
        UniVerResponse<List<Builder>> res = new UniVerResponse<>();

        QueryBuilder queryBuilder = QueryBuilders.rangeQuery("buildDate").from(builder.getBuildDate().getTime());
        Iterable<Builder> search = builderDao.search(queryBuilder);
        Iterator<Builder> iterator = search.iterator();
        List<Builder> list = new ArrayList<>();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }

        res.beTrue(list);
        return res;
    }


    /**
     *  方式1
     *
     *  检索所有索引
     * @return
     */
    @RequestMapping(value = "/searchAll", method = RequestMethod.GET)
    public UniVerResponse<List<Builder>> searchAll(){
        UniVerResponse<List<Builder>> res = new UniVerResponse<>();
        QueryBuilder queryBuilder = QueryBuilders.boolQuery();
        Iterable<Builder> search = builderDao.search(queryBuilder);
        Iterator<Builder> iterator = search.iterator();
        List<Builder> list = new ArrayList<>();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }

        res.beTrue(list);
        return res;
    }

    /**
     * 方式1
     *
     * 根据传入属性值  全文检索所有属性
     * 关于QueryStringQueryBuilder的使用，如果不指定分词器，那么查询的时候，会使用ES默认的分词器进行查询。
     * 结果就是 会查询出与查询内容丝毫不相干的结果。
     *
     *
     * 关于ES内置分词器：
     * https://blog.csdn.net/u013795975/article/details/81102010
     *
     *
     *
     * @return
     */
    @RequestMapping(value = "/findByStr", method = RequestMethod.GET)
    public UniVerResponse<List<Builder>> findByStr(String paramStr){
        UniVerResponse<List<Builder>> res = new UniVerResponse<>();
        QueryStringQueryBuilder qsqb = new QueryStringQueryBuilder(paramStr).analyzer("standard");
        Iterable<Builder> search = builderDao.search(qsqb);
        Iterator<Builder> iterator = search.iterator();
        List<Builder> list = new ArrayList<>();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }

        res.beTrue(list);
        return res;

    }

    /**
     * 方式1
     *
     * 选择用term或match方式查询
     * 查询字段buildName或者buildName2
     * 指定以分词器 ik_max_word 或  ik_smart 或 standard[es默认分词器] 或 english 或 whitespace 分词器进行分词查询
     *
     *
     *
     * @param analyzer  分词器
     * @param str       查询属性值
     * @param param     指定是参数1[buildName] 还是 参数2[remark]
     * @return
     */
    @RequestMapping(value = "/searchByIK", method = RequestMethod.GET)
    public UniVerResponse<List<Builder>> searchByIK(String analyzer,String str,Integer param){
        UniVerResponse<List<Builder>> res = new UniVerResponse<>();
        QueryBuilder matchBuilder =   QueryBuilders.matchQuery(param ==1 ? "buildName" : "remark",str).analyzer(analyzer);


        Iterable<Builder> search = builderDao.search(matchBuilder);
        Iterator<Builder> iterator = search.iterator();
        List<Builder> list = new ArrayList<>();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }

        res.beTrue(list);
        return res;
    }


    /**
     * 方式1
     *
     * 繁简体转化查询、拼音查询,并且加入评分查询
     * 评分规则详情：https://blog.csdn.net/paditang/article/details/79098830
     * @param pinyinStr
     * @return
     */
    @RequestMapping(value = "/searchByPinYin",method = RequestMethod.GET)
    public UniVerResponse<List<Builder>> searchByPinYin(String pinyinStr){
        UniVerResponse<List<Builder>> res =  new UniVerResponse<>();
        DisMaxQueryBuilder disMaxQuery = QueryBuilders.disMaxQuery();
        QueryBuilder ikSTQuery = QueryBuilders.matchQuery("buildName",pinyinStr).boost(1f);
        QueryBuilder pinyinQuery = QueryBuilders.matchQuery("buildName.pinyin",pinyinStr);

        disMaxQuery.add(ikSTQuery);
        disMaxQuery.add(pinyinQuery);

        Iterable<Builder> search = builderDao.search(disMaxQuery);
        Iterator<Builder> iterator = search.iterator();
        List<Builder> list = new ArrayList<>();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }

        res.beTrue(list);
        return res;
    }







    /**
     *
     * @param builder
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public UniVerResponse<Builder> delete(@RequestBody Builder builder){
        UniVerResponse<Builder> res = new UniVerResponse<>();

        builderDao.deleteById(builder.getId());
        res.beTrue(builder);
        return res;
    }




}
