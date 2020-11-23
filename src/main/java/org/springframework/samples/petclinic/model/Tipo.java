package org.springframework.samples.petclinic.model;

public enum Tipo {
	
	CANINO(1,"canino"), 
	FELINO(2,"felino"), 
	REPTILES(3,"reptiles"), 
	AVES(4,"aves");
	
	private int value;
    private String key;
 
    Tipo(int value, String key) {
        this.value = value;
        this.key = key;
    }
 
    public int getValue() {
        return value;
    }
 
    public void setValue(int value) {
        this.value = value;
    }
 
    public String getKey() {
        return key;
    }
 
    public void setKey(String key) {
        this.key = key;
    }
}
