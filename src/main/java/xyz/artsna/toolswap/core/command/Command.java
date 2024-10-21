package xyz.artsna.toolswap.core.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

	String name();
	String permission() default "null";
	SenderType senderType() default SenderType.ALL;
	
}
