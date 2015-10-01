package model;

public class byDay {

	private Long date;
	private int count;
	
	byDay(){}
	
	public byDay( Long date, int count )
	{
		this.date = date;
		this.count = count;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String toString()
	{
		return date.toString()+count;
	}
}
