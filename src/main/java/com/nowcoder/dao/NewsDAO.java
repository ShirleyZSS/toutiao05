package com.nowcoder.dao;

import com.nowcoder.model.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Shirley on 2017/7/13.
 */
@Mapper
public interface NewsDAO {
    String TABLE_NAME ="news";
    String INSERT_FIELDS="title,link,image,like_count,comment_count,created_date,user_id";
    String SELECT_FIELDS="id,"+INSERT_FIELDS;

    @Insert({"insert into ",TABLE_NAME," (",INSERT_FIELDS,
            ") values (#{title},#{link},#{image},#{likeCount},#{commentCount},#{createdDate},#{userId})"})
    int addNews(News news);//添加资讯 通过注解配置

    List<News> selectByUserIdAndOffset(@Param("userId") int userId,@Param("offset") int offset,
                                       @Param("limit") int limit);
    //列出用户部分资讯 通过XML配置




}
