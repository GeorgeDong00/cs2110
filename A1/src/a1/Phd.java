package a1;

/**
 * NetId: nnnnn, nnnnn. Time spent: hh hours, mm minutes. <br>
 * What I thought about this assignment: <br>
 * <br>
 * An instance maintains info about the Phd of a person.
 */

/*
 * Notes on Variables - Shorter for parameters (arguments) + local; longer for
 * fields + static variables. Method are at most 30-50 lines.
 */

public class Phd {
	/** Name of person with Phd (recipient). Length greater than 0. */
	private String name;

	/** Year Phd was awarded. Must be > 1000 */
	private int year;

	/** Month Phd was awarded. Between 1 and 12 inclusive with 1 meaning January */
	private int month;

	/** First advisor of Phd recipient. Null if unknown. */
	private Phd advisor1;

	/** Second advisor of Phd recipient. Null if unknown or only one advisor. */
	private Phd advisor2;

	/** Recipient's number of Phd advisees. */
	private int advisees;

	/* Group A */

	/**
	 * Constructor: an instance for a person with name n, Phd year y, Phd month m.
	 * Its advisors are unknown, and it has no advisees.
	 * 
	 * Precondition: n has at least 1 char, y > 1000, and m is in 1..12
	 */
	public Phd(String n, int y, int m) {
		assert n.length() >= 1;
		assert y > 1000;
		assert m >= 1 && m <= 12;
		name = n;
		year = y;
		month = m;
		advisor1 = null;
		advisor2 = null;
		advisees = 0;
	}

	/** = name of this person */
	public String name() {
		return this.name;
	}

	/**
	 * = the date on which this person got the Phd. In the form "month/year", with
	 * no blanks, e.g. "6/2007"
	 */
	public String date() {
		return this.month + "/" + this.year;
	}

	/**
	 * = the first advisor of this Phd (null if unknown).
	 */
	public Phd advisor1() {
		return this.advisor1;
	}

	/**
	 * = the second advisor of this Phd (null if unknown or nonexistent).
	 */
	public Phd advisor2() {
		return this.advisor2;
	}

	/** = the number of Phd advisees of this person. */
	public int nAdvisees() {
		return this.advisees;
	}

	/* Group B */
	/** Increment advisor's advisees count by 1. */
	private void plusAdvisees() {
		this.advisees = this.advisees + 1;
	}

	/**
	 * Make p the first advisor of this person.
	 * 
	 * Precondition: the first advisor is unknown and p is not null.
	 */
	public void setAdvisor1(Phd p) {
		assert this.advisor1 == null;
		assert p != null;
		this.advisor1 = p;
		p.plusAdvisees();
	}

	/**
	 * Make p the second advisor of this person.
	 * 
	 * Precondition: The first advisor (of this person) is known, the second advisor
	 * is unknown, p is not null, and p is different from the first advisor.
	 */
	public void setAdvisor2(Phd p) {
		assert this.advisor1 != null;
		assert this.advisor2 == null;
		assert p != null;
		assert p != this.advisor1;
		this.advisor2 = p;
		p.plusAdvisees();
	}

	/* Group C */

	/**
	 * Constructor: a Phd with name n, Phd year y, Phd month m, first advisor a1,
	 * and second advisor a2.
	 * 
	 * Precondition: n has at least 1 char, y > 1000, m is in 1..12, a1 and a2 are
	 * not null, and a1 and a2 are different.
	 */
	public Phd(String n, int y, int m, Phd a1, Phd a2) {
		this(n, y, m);
		assert a1 != a2;
		assert a1 != null;
		assert a2 != null;
		this.advisor1 = a1;
		this.advisor2 = a2;
		a1.plusAdvisees();
		a2.plusAdvisees();
	}

	/* Group D */

	/**
	 * = "this Phd has no advisees" i.e true if Phd has no advisees and false
	 * otherwise
	 */
	public boolean hasNoAdvisees() {
		return this.nAdvisees() == 0;
	}

	/** = "p is not null and this person got the Phd before p.” */
	public boolean gotBefore(Phd p) {
		assert p != null;
		int thisMonth = Integer.valueOf(this.date().split("/")[0]);
		int thisYear = Integer.valueOf(this.date().split("/")[1]);
		int pMonth = Integer.valueOf(p.date().split("/")[0]);
		int pYear = Integer.valueOf(p.date().split("/")[1]);
		return (thisYear < pYear) || (thisYear == pYear && thisMonth < pMonth);
	}

	/**
	 * = "this person and p are intellectual siblings."
	 * 
	 * Precondition: p is not null.
	 */
	public boolean areSibs(Phd p) {
		assert p != null;
		boolean sameObject = (this != p);
		Phd thisA1 = this.advisor1();
		Phd thisA2 = this.advisor2();
		Phd pA1 = p.advisor1();
		Phd pA2 = p.advisor2();
		boolean sameA1 = ((thisA1 == pA1) && (thisA1 != null && pA1 != null))
				|| ((thisA1 == pA2) && (thisA1 != null && pA2 != null));
		boolean sameA2 = ((thisA2 == pA1) && (thisA2 != null && pA1 != null))
				|| ((thisA2 == pA2) && (thisA2 != null && pA2 != null));
		return sameObject && (sameA1 || sameA2);
	}
}