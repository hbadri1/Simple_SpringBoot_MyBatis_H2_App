package org.gso.committee;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.gso.committee.mapper.CommitteeMapper;
import org.gso.committee.model.Committee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This is the main controller of our web application.
 * 
 * @author badri.houssem@gmail.com
 *
 */
@Controller
public class MainController {

	@Autowired
	private CommitteeMapper committeMapper;

	/**
	 * This method just to use the annotation in stead of xml configuration (only
	 * for simple queries)
	 * 
	 * @return the view to be rendered which its model contains the data (all the
	 *         committees in db)
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/allCommittees", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getAllCommittees(Map<String, Set<Committee>> model) {
		Set<Committee> allCommittees = committeMapper.getAllCommittees();
		model.put("committees", allCommittees);
		return "home";
	}

	/**
	 * This method query the database to construct the list of parents with their
	 * children using the xml-approach of MyIbatis
	 * 
	 * @param model:
	 *            the model of data to inject in the view to render.
	 * @return: the view to render with all the parents with their children
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getParentsWithChildren(Map<String, Set<Committee>> model) {
		Reader reader;
		try {
			reader = Resources.getResourceAsReader("mybatis.conf.xml");
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory sessionFactory = builder.build(reader);
			SqlSession session = sessionFactory.openSession();
			CommitteeMapper mapper = session.getMapper(CommitteeMapper.class);
			Set<Committee> parents = mapper.getParentsWithChildren();
			model.put("committees", parents);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "home";
	}

	/**
	 * If we need to consult the result of parents with their children in json
	 * format.
	 * 
	 * @return: List of committees (parents with their children)
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, path = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Set<Committee> getParentsWithChildrenAsJson() {
		Reader reader;
		try {
			reader = Resources.getResourceAsReader("mybatis.conf.xml");
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory sessionFactory = builder.build(reader);
			SqlSession session = sessionFactory.openSession();
			CommitteeMapper mapper = session.getMapper(CommitteeMapper.class);
			return mapper.getParentsWithChildren();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
