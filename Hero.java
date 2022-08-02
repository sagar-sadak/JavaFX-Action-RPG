public class Hero {
	
	// private variables
	private String name;
	private String type;
	private int strength;
	private int charisma;
	private int damage;
	
	/**
	 * 
	 * @param _name Name
	 * @param _type	Type (Mage or Fighter)
	 * @param _strength Strength
	 * @param _charisma Charisma
	 * @param _damage Damage
	 */
	// constructor 1
	public Hero(String _name, String _type, int _strength, int _charisma, int _damage) {
		this.name = _name;
		this.type = _type;
		this.strength = _strength;
		this.charisma = _charisma;
		this.damage = _damage;
	}
	
	// constructor 2
	public Hero() {
		this("?", "?", 0, 0, 0);
	}
	
	// method to access name
	public String getName() {
		return name;
	}

	// method to change name
	public void setName(String name) {
		this.name = name;
	}

	// method to access type
	public String getType() {
		return type;
	}

	// method to change type
	public void setType(String type) {
		this.type = type;
	}

	// method to access strength
	public int getStrength() {
		return strength;
	}

	// method to change strength
	public void setStrength(int strength) {
		this.strength = strength;
	}

	// method to access charisma
	public int getCharisma() {
		return charisma;
	}

	// method to change charisma
	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	// method to access damage
	public double getDamage() {
		return damage;
	}

	// method to change damage
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	// toString method
	public String toString() {
		return String.format("Hero name: %s\t\t\t(%s)\nStrength: %d\tCharisma: %d\tDamage: %d\n", this.name, this.type, this.strength, this.charisma, this.damage);		
	}
}
