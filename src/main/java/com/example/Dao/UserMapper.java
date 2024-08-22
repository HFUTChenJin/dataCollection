package com.example.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.Entity.User;
import org.apache.ibatis.annotations.Mapper;



/**
 * @author MSI
 */
@Mapper
//@Repository
public interface UserMapper extends BaseMapper<User> {

}
