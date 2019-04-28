
abstract public class Event {
	private long evtTime;
	public Event(long eventTime) {
		evtTime = eventTime;
	}
	public boolean ready() {
		
	}
	abstract public void action();
	abstract public String description();
} ///