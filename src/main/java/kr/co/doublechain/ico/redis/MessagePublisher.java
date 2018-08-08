package kr.co.doublechain.addr.redis;

/**
 * 
 * Redis Message Publisher Interface
 * 
 * created by basquiat
 *
 */
public interface MessagePublisher {

	void publish(final String message);
	
}
