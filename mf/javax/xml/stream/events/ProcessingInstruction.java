package mf.javax.xml.stream.events;

public interface ProcessingInstruction extends XMLEvent {
    String getData();

    String getTarget();
}
