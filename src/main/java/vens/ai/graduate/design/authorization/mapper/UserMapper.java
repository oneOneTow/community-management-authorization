package vens.ai.graduate.design.authorization.mapper;

import vens.ai.graduate.design.authorization.entity.User;

/**
 * @author
 * @date 2018-05-07 11:26
 **/
public interface UserMapper {
    int save(User user);
    User findByStuId(String stuId);
    User findByName(String name);
}
