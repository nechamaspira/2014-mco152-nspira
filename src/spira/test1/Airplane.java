package spira.test1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spira.test1.Seat;
import spira.test1.UnknownSeatException;

public class Airplane {
	private ArrayList<Seat> seats;
	private Map<String, Seat> airplane;
	private String configuration;
	private int numSeats;
	private int numRows;
	private int numCol;
	private int numIsles;
	private char[] configur;

	/**
	 * Construct a new Airplane with the specified configuration and number of
	 * rows. The configuration is a String with letters specifying a seat's
	 * position in the row and a "_" for the aisle.
	 * 
	 * For instance, an Airplane with configuration, ABC_DEFGH_JKL would be
	 * three seats, then an aisle, then 5 seats, then an aisle, then 3 seats.
	 * 
	 * @param configuration
	 * @param numRows
	 */
	public Airplane(String configuration, int numRows) {
		this.airplane = new HashMap<String, Seat>();
		this.seats = new ArrayList<Seat>();
		this.configuration = configuration;
		this.numRows = numRows;
		this.configur = configuration.toCharArray();

		int numIsl = 0;
		for (int i = 0; i < configur.length; i++) {
			if (configur[i] == '_') {
				numIsl++;
			}
		}

		this.numIsles = numIsl;
		this.numCol = configur.length - this.numIsles;
		String key;
		Seat value;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < configur.length; j++) {

				if (!(configur[j] == '_')) {
					value = new Seat(i + 1, configur[j]);
					key = value.getCode();

					this.airplane.put(key, value);
					this.seats.add(value);
				}
			}
		}
		this.numSeats = this.numCol * this.numRows;
	}

	/**
	 * @return the total number of EMPTy seats on the plane.
	 */
	public int getNumEmptySeats() {
		int numEmptySeats = 0;
		for (int i = 0; i < seats.size(); i++) {
			if (!seats.get(i).isOccupied()) {
				numEmptySeats++;
			}
		}
		return numEmptySeats;
	}

	/**
	 * @return true if the plane is full, otherwise false.
	 */
	public boolean isFull() {
		for (int i = 0; i < seats.size(); i++) {
			if (!seats.get(i).isOccupied()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param code
	 * @return true if the seat is occupied, otherwise false.
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public boolean isOccupied(String code) throws UnknownSeatException {
		if (!airplane.containsKey(code)) {
			throw new UnknownSeatException();
		}
		if (airplane.get(code).isOccupied()) {
			return true;
		}
		return false;
	}

	/**
	 * Sets the seat as occupied/unoccupied
	 * 
	 * @param code
	 * @param occupied
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public void setOccupied(String code, boolean occupied)
			throws UnknownSeatException {
		if (!airplane.containsKey(code)) {
			throw new UnknownSeatException();
		} else {
			Seat seat = this.airplane.get(code);
			seat.setOccupied(occupied);
		}
	}

	/**
	 * Set all seats by their codes as occupied
	 * 
	 * @param codes
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public void occupy(String... codes) throws UnknownSeatException {
		for (String code : codes) {
			Seat seat = this.airplane.get(code);
			seat.setOccupied(true);

		}
	}

	/**
	 * Sets all seats as occupied
	 * 
	 * @param seats
	 */
	public void occupy(List<Seat> seats) {
		/*
		 * for (int i = 0; i < seats.size(); i++) {
		 * seats.get(i).setOccupied(true); }
		 */

		for (Seat seat : seats) {
			seat.setOccupied(true);
		}

	}

	/**
	 * Returns the seat specified by it's code
	 * 
	 * @param code
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public Seat getSeat(String code) throws UnknownSeatException {
		if (!airplane.containsKey(code)) {
			throw new UnknownSeatException();
		}
		return this.airplane.get(code);
	}

	/**
	 * @return total number of seats on the plane
	 */
	public int getNumSeats() {
		return this.numSeats;
	}

	/**
	 * Returns the Airplane specified in text format.
	 * 
	 * The first line should be the configuration, prepended by 4 spaces Each
	 * row in the plane gets a line which starts with The row number, padded
	 * with leading zeros so that is is always 3 digits. A space Then for each
	 * seat, either a "." for an empty seat, "O" for an occupied seat and "_"
	 * for an aisle.
	 * 
	 * Example. AB_CD_EF\n 001 .._.._..\n 002 .._.._..\n 003 .._.._..\n
	 * 
	 * 
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("000");
		StringBuilder info = new StringBuilder();
		info.append("    ");
		info.append(configuration);
		info.append("\n");

		for (int i = 0; i < numRows; i++) {
			info.append(df.format(i + 1));
			info.append(" ");
			for (int j = 0; j < configur.length; j++) {

				if (this.configur[j] == '_') {
					info.append("_");
				} else {

					String code = String.valueOf(i + 1)
							+ String.valueOf(configur[j]);
					Seat seat = airplane.get(code);

					if (seat.isOccupied()) {
						info.append("O");
					} else {
						info.append(".");
					}
				}

			}
			info.append("\n");
		}
		return info.toString();
	}

	/**
	 * 
	 * @param numSeatsTogeather
	 *            the number of seats to occupy.
	 * @return A list of occupied seats.
	 * @throws FullPlaneException
	 *             if the plane is full
	 * @throws NotEnoughSeatsTogeatherException
	 *             if there are not enough seats next to each other.
	 */
	public List<Seat> occupySeats(int numSeatsTogeather)
			throws FullPlaneException, NotEnoughSeatsTogeatherException {
		List<Seat> numbSeatsTog = new ArrayList<Seat>();
		boolean seatsTog = false;

		if (this.isFull()) {
			throw new FullPlaneException();
		}

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < configur.length; j++) {
				if (configur[j] == '_') {
					j++;
					numbSeatsTog.clear();
				}
				String code = String.valueOf(i + 1)
						+ String.valueOf(configur[j]);

				Seat seat = this.airplane.get(code);

				if (!seat.isOccupied()) {
					numbSeatsTog.add(seat);
				}

				if (numbSeatsTog.size() == numSeatsTogeather) {
					seatsTog = true;
					break;
				}
			}
			if (!seatsTog) {
				numbSeatsTog.clear();
			}
			else{
				break;
			}
		}

		if (!seatsTog) {
			throw new NotEnoughSeatsTogeatherException();
		}
		this.occupy(numbSeatsTog);
		return numbSeatsTog;
	}
}
