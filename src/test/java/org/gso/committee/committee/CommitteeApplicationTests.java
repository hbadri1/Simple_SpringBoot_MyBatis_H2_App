package org.gso.committee.committee;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.gso.committee.mapper.CommitteeMapper;
import org.gso.committee.model.Committee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommitteeApplicationTests {

	@Autowired
	private CommitteeMapper committeeMapper;

	@Test
	public void findAllCommittees() {
		Set<Committee> comms = this.committeeMapper.getAllCommittees();
		assertNotNull(comms);
		assertTrue(!comms.isEmpty());
	}

	@Test
	public void findChildrenByParentId() {
		Set<Committee> children = this.committeeMapper.getChildrenByParentId(1L);
		assertNotNull(children);
		assertTrue(!children.isEmpty());
	}

	@Test
	public void contextLoads() {
	}

}
