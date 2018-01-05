package org.gso.committee.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.gso.committee.model.Committee;

/**
 * 
 * @author Houssem
 *
 */
@Mapper
public interface CommitteeMapper {

	/**
	 * Method to query the database with the annotations approach with MyIbatis
	 * 
	 * @return: List of all committees in database
	 */
	@Select("SELECT * FROM COMMITTEE")
	Set<Committee> getAllCommittees();

	/**
	 * Find set of children of specified parent. JUST FOR DEMO PURPOE, WE DID NOT
	 * USE IT IN OUR APP.
	 * 
	 * @param code:
	 *            Parent code
	 * @return: List of children
	 */
	@Select("SELECT * FROM COMMITTEE WHERE P_CODE = #{parentId}")
	Set<Committee> getChildrenByParentId(@Param("parentId") Long code);

	/**
	 * Get parents with their children using the xml approach of MyIbatis
	 * 
	 * @return: Set of parents
	 */
	Set<Committee> getParentsWithChildren();
}
