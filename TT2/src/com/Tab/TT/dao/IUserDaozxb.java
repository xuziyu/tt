package com.Tab.TT.dao;

import javax.security.auth.login.LoginException;

import com.Tab.TT.Entity.User;

/**
 * @author : 周小兵
 * @date 创建时间：2017年9月4日 下午3:53:25
 * @version 1.0
 * @parameter
 * @since
 * @return
 */

// 编写UserJdbcTest类。包含下列方法，在main方法中分别调用各方法执行。
// 1、insert()：
// 张三、qwerty、1、1996-06-02、0592-955221、
// 2、update()：修改上述记录的电话号码为0592-955227、性别为0。注：通过Id修改
// 3、delete()：删除上述插入的记录，注：通过Id删除
// 4、load()：通过id查询用户的信息
// 5、select()：查询所有用户的信息
// 6、findMale()：查询所有男用户的信息
// 7、findFirstNameZhang()：查询所有姓张的用户的信息
// 8、findFirstNameZhangAndMale()：查询所有姓张的男用户的信息
// 9、deleteMale()：删除所有男用户

public interface IUserDaozxb {



	public void insert(User user) throws Exception ;


	public void update(User user) ;


	public void delete(User user) ;


	public void load(User user) ;


	public void select(User user) ;


	public void findMale(User user) ;


	public void findFirstNameZhang(User user) ;


	public void findFirstNameZhangAndMale(User user) ;


	public void deleteMale(User user) ;


	public void login(User user) throws LoginException ;

}
