
public class Statistic {
	
	private long bestCase;
	
	private long worstCase;
	
	private long averageCase;
	
	private int cursor;
	
	public Statistic(long totalTime) {
		this.bestCase=totalTime;
		this.worstCase=totalTime;
		this.averageCase=totalTime;
		this.cursor=1;
	}

	public long getBestCase() {
		return bestCase;
	}

	public void setBestCase(long bestCase) {
		this.bestCase = bestCase;
	}

	public long getWorstCase() {
		return worstCase;
	}

	public void setWorstCase(long worstCase) {
		this.worstCase = worstCase;
	}

	public long getAverageCase() {
		return averageCase;
	}

	public void setAverageCase(long averageCase) {
		this.averageCase = averageCase;
	}

	public int getCursor() {
		return cursor;
	}

	public void setCursor(int cursor) {
		this.cursor = cursor;
	}

}
