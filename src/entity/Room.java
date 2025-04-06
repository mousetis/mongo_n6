package entity;

import java.util.Objects;

public class Room {
	private String RoomCode;
	private String RoomName;
	private String RoomType;
	private int RoomPrice;
	private String RoomStatus;
	private int MaxPeople;
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(RoomCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return Objects.equals(RoomCode, other.RoomCode);
	}

	public Room(String RoomCode) {
		super();
		this.RoomCode = RoomCode;
	}

	public Room(String RoomCode, String RoomName, String RoomType, int RoomPrice, String RoomStatus, int MaxPeople) {
		super();
		this.RoomCode = RoomCode;
		this.RoomName = RoomName;
		this.RoomType = RoomType;
		this.RoomPrice = RoomPrice;
		this.RoomStatus = RoomStatus;
		this.MaxPeople = MaxPeople;
	}
	
	public String getRoomCode() {
		return RoomCode;
	}
	public void setRoomCode(String RoomCode) {
		this.RoomCode = RoomCode;
	}
	public String getRoomName() {
		return RoomName;
	}
	public void setRoomName(String RoomName) {
		this.RoomName = RoomName;
	}
	public String getRoomType() {
		return RoomType;
	}
	public void setRoomType(String RoomType) {
		this.RoomType = RoomType;
	}
	public int getRoomPrice() {
		return RoomPrice;
	}
	public void setRoomPrice(int RoomPrice) {
		this.RoomPrice = RoomPrice;
	}
	public String getRoomStatus() {
		return RoomStatus;
	}
	public void setRoomStatus(String RoomStatus) {
		this.RoomStatus = RoomStatus;
	}
	public int getMaxPeople() {
		return MaxPeople;
	}
	public void setMaxPeople(int MaxPeople) {
		this.MaxPeople = MaxPeople;
	}
	
	
}
