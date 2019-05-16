class EventSet {
	private Event[] events = new Event[1000];
	private int index = 0;	
	private int next = 0;
	
	public void add(Event e) {
		events[index++] = e;
	}	

	public Event getNext() {
		Event e = events[next];
		return e;
	}

	public void removeCurrent() {
		events[next] = null;
		next++;
	}
	public void removeNext() {
		events[next+1] = null;
		
	}
}


public class Controller {
	private EventSet es = new EventSet();
	public void addEvent(Event c) { es.add(c); }
	public void run() {
		Event e;
		while((e = es.getNext()) != null) {
			e.action();
			es.removeCurrent();
		}
	}
	public void removeNext() {
		es.removeNext();
	}
}
