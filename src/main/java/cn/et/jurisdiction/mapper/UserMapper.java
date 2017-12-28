package cn.et.jurisdiction.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Select;

import cn.et.jurisdiction.entity.Menu;
import cn.et.jurisdiction.entity.UserInfo;

public interface UserMapper {

	@Select("SELECT user_name AS userName,pass_word AS PASSWORD FROM user_info WHERE user_name = #{0}")
	public UserInfo queryUser(String userName);

	// 查看角色
	@Select("SELECT r.role_name FROM user_info u " + "INNER JOIN relationship_1 urr ON u.user_id = urr.user_id "
			+ "INNER JOIN role r ON urr.role_id = r.role_id " + "WHERE user_name = #{0}")
	public Set<String> queryRoleByName(String userName);

	// 查询当前用户所有的权限
	@Select("SELECT p.pem_tag FROM user_info u " + "INNER JOIN relationship_1 urr ON u.user_id = urr.user_id "
			+ "INNER JOIN role r ON urr.role_id = r.role_id "
			+ "INNER JOIN role_pems_relation rpr ON r.role_id = rpr.role_id "
			+ "INNER JOIN pems p ON rpr.pem_id = p.pem_id " + "WHERE user_name = #{0}")
	public Set<String> queryPemsByName(String userName);

	// 查询权限的路径和权限
	@Select("select menu_id as menuid,menu_name as menuname, menu_url as menuurl, menu_filter as menufilter, is_menu as ismenu from menu")
	public List<Menu> queryMenu();

	// 查询url需要哪个权限
	@Select("select menu_id as menuid,menu_name as menuname, menu_url as menurl, menu_filter as menufilter, is_menu as ismenu from menu where menu_url = #{0}")
	public List<Menu> queryMenuByUrl(String url);

	@Select("SELECT menu_name as menuname, menu_url as menuurl FROM menu m INNER JOIN user_menu_relation umr ON m.menu_id = umr.menu_id INNER JOIN user_info u ON umr.user_id = u.user_id WHERE u.user_name = #{0}")
	public List<Menu> queryMenuByUser(String userName);
}
