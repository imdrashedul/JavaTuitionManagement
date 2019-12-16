package entity;

import java.time.LocalDateTime;


public class Session {
	
	private int id;
	private int start;
	private int end;
	private LocalDateTime created;

	public Session() {}

	public Session(int id,int start, int end, LocalDateTime created ){
		this.id = id;
		this.email = email;
		this.start = start;
		this.end = end;
		this.created = created;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public int getId() {
		return this.id;
	}

	public int getStart() {
		return this.start;
	}

	public int getEnd() {
		return this.end;
	}

	public LocalDateTime getCreated() {
		return this.created;
	}
}