package a1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PhdTest {

	@Test
	public void testConstructor1() {
		Phd person1 = new Phd("George D", 2001, 3);

		assertEquals("George D", person1.name());
		assertEquals("3/2001", person1.date());
		assertEquals(null, person1.advisor1());
		assertEquals(null, person1.advisor2());
		assertEquals(0, person1.nAdvisees());

		assertThrows(AssertionError.class, () -> {
			new Phd("", 2001, 3);
		});
	}

	@Test
	public void testAdvisor() {
		Phd person3 = new Phd("Jake R", 2003, 4);
		Phd person4 = new Phd("Paul L", 2002, 4);
		Phd person5 = new Phd("Chris H", 2002, 4);

		person3.setAdvisor1(person4);
		assertEquals(person4, person3.advisor1());

		person3.setAdvisor2(person5);
		assertEquals(person5, person3.advisor2());

		assertEquals(1, person4.nAdvisees());
		assertEquals(1, person5.nAdvisees());
	}

	@Test
	public void testConstructor2() {
		Phd person7 = new Phd("Zack S", 2002, 5);
		Phd person8 = new Phd("Yuan E", 2002, 5);
		Phd person6 = new Phd("Henry X", 2002, 5, person7, person8);

		assertEquals("Henry X", person6.name());
		assertEquals("5/2002", person6.date());
		assertEquals(person7, person6.advisor1());
		assertEquals(person8, person6.advisor2());
		assertEquals(0, person6.nAdvisees());

		assertEquals(1, person7.nAdvisees());
		assertEquals(1, person8.nAdvisees());
	}

	@Test
	public void testhasNoAdvisees() {
		Phd person9 = new Phd("Jackson C", 2002, 5);

		assertEquals(true, person9.hasNoAdvisees());
	}

	@Test
	public void testgotBefore() {
		Phd feb77 = new Phd("feb77", 1977, 2);
		Phd jun77 = new Phd("jun77", 1977, 6);
		Phd feb78 = new Phd("feb78", 1978, 2);
		Phd jun78 = new Phd("jun77", 1978, 6);

		assertEquals(false, feb77.gotBefore(feb77));
		assertEquals(false, jun77.gotBefore(feb77));
		assertEquals(true, feb77.gotBefore(jun77));

		assertEquals(false, feb78.gotBefore(feb77));
		assertEquals(false, feb78.gotBefore(jun77));
		assertEquals(false, jun78.gotBefore(feb77));

		assertEquals(true, feb77.gotBefore(feb78));
		assertEquals(true, feb77.gotBefore(jun78));
		assertEquals(true, jun77.gotBefore(feb78));
	}

	@Test
	public void testareSib() {
		Phd p1 = new Phd("no advisor", 1977, 2);
		Phd p2 = new Phd("no advisor", 1977, 6);
		assertEquals(false, p1.areSibs(p2));

		Phd p3 = new Phd("p3", 1977, 2, p1, p2);
		assertEquals(false, p3.areSibs(p3));

		Phd p4 = new Phd("p4", 1977, 2, p1, p3);
		Phd p5 = new Phd("p5", 1977, 2, p1, p2);
		assertEquals(true, p4.areSibs(p5));

		Phd p6 = new Phd("p6", 1977, 2, p3, p5);
		Phd p7 = new Phd("p7", 1977, 2, p4, p5);
		assertEquals(true, p6.areSibs(p7));

		Phd p8 = new Phd("p8", 1977, 2, p3, p5);
		Phd p9 = new Phd("p9", 1977, 2, p4, p3);
		assertEquals(true, p8.areSibs(p9));

		Phd p10 = new Phd("p10", 1977, 2, p3, p5);
		Phd p11 = new Phd("p11", 1977, 2, p4, p3);
		assertEquals(true, p10.areSibs(p11));

		Phd p12 = new Phd("p12", 1977, 2);
		p12.setAdvisor1(p1);
		Phd p13 = new Phd("p13", 1977, 2);
		p13.setAdvisor1(p1);
		assertEquals(true, p12.areSibs(p13));

		Phd p14 = new Phd("p14", 1977, 2);
		p14.setAdvisor1(p2);
		p14.setAdvisor2(p1);
		Phd p15 = new Phd("p15", 1977, 2);
		p15.setAdvisor1(p3);
		p15.setAdvisor2(p1);
		assertEquals(true, p14.areSibs(p15));
	}
}
