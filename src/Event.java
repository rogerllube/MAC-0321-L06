
abstract public class Event {
	private long evtPrio;
	public Event(long eventPrio) {
		evtPrio = eventPrio;
	}
	abstract public boolean ready();
	abstract public void action();
	abstract public String description();
} ///