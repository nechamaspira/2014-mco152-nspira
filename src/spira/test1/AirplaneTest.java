package spira.test1;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class AirplaneTest {

	@Test
	public void testToStringWithEmptyPlane() {
		Airplane plane = new Airplane("AB_CD_EF", 10);
		Assert.assertEquals("    AB_CD_EF\n" + "001 .._.._..\n"
				+ "002 .._.._..\n" + "003 .._.._..\n" + "004 .._.._..\n" +"005 .._.._..\n" +"006 .._.._..\n" +
				"007 .._.._..\n" +"008 .._.._..\n" +"009 .._.._..\n" +"010 .._.._..\n" , plane.toString());
	}

	@Test
	public void testToStringWithFullPlane() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy("1A", "1B", "1C", "1D", "1E", "1F", "2A", "2B", "2C",
				"2D", "2E", "2F", "3A", "3B", "3C", "3D", "3E", "3F");
		Assert.assertEquals("    AB_CD_EF\n" + "001 OO_OO_OO\n"
				+ "002 OO_OO_OO\n" + "003 OO_OO_OO\n", plane.toString());
	}

	@Test
	public void testGetNumSeats() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		Assert.assertEquals(18, plane.getNumSeats());

	}

	@Test
	public void testGetNumEmptySeats() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy("1A", "1B");

		Assert.assertEquals(16, plane.getNumEmptySeats());

	}

	@Test
	public void testIsFull() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		Assert.assertFalse(plane.isFull());

	}

	@Test
	public void testGetSeatThrowsUnknownSeatException() {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		try {
			plane.getSeat("4A");

			Assert.fail("UnknownSeatExceptionshould be thrown");
		} catch (UnknownSeatException e) {

		}
	}

	@Test
	public void testOccupySeats() throws UnknownSeatException,
			FullPlaneException, NotEnoughSeatsTogeatherException {
		Airplane plane3 = new Airplane("AB_CDE_FG", 3);
		List<Seat> seatsOccupied3 = plane3.occupySeats(3);
		Assert.assertEquals(3, seatsOccupied3.size());
		Assert.assertEquals("    AB_CDE_FG\n" + "001 .._OOO_..\n"
				+ "002 .._..._..\n" + "003 .._..._..\n", plane3.toString());

		Airplane plane = new Airplane("AB", 2);
		plane.occupy("1A");
		List<Seat> seatsOccupied = plane.occupySeats(2);
		Assert.assertEquals("    AB\n" + "001 O.\n" + "002 OO\n",
				plane.toString());
		Assert.assertEquals(2, seatsOccupied.size());

		Airplane plane2 = new Airplane("AB_CDE_FG", 3);
		plane2.occupy("1C", "2D");
		List<Seat> seatsOccupied2 = plane2.occupySeats(3);
		Assert.assertEquals(3, seatsOccupied2.size());
		Assert.assertEquals("    AB_CDE_FG\n" + "001 .._O.._..\n"
				+ "002 .._.O._..\n" + "003 .._OOO_..\n", plane2.toString());
		
		
		
	}

	@Test
	public void testOccupySeatsThrowsNotEnoughSeatsTogeatherException()
			throws FullPlaneException, UnknownSeatException {

		try {
			Airplane plane = new Airplane("AB_CD_EF", 3);
			plane.occupy("1A", "1C", "1E", "2A", "2C", "2E", "3A", "3C", "3E");
			plane.occupySeats(2);

			Assert.fail("NotEnoughSeatsTogeatherException is supposed to been thrown");
		} catch (NotEnoughSeatsTogeatherException e) {

		}
	}

	@Test
	public void testOccupySeatsThrowsFullPlaneException()
			throws NotEnoughSeatsTogeatherException, UnknownSeatException {

		try {
			Airplane plane = new Airplane("AB_CD_EF", 3);
			plane.occupy("1A", "1B", "1C", "1D", "1E", "1F", "2A", "2B", "2C",
					"2D", "2E", "2F", "3A", "3B", "3C", "3D", "3E", "3F");
			plane.occupySeats(2);

			Assert.fail("ThrowsFullPlaneException is supposed to been thrown");
		} catch (FullPlaneException e) {

		}
	}
}
