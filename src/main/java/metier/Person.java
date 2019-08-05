package metier;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Person {
	
	private String name;
	
	public Person(String name) {
		this.name = name;
	}
	
}
