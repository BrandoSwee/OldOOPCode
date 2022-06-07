package map;

// This is just a wrapper class for a Person.
// It currently only stores a person's name, but could store
// many other things about a person.

public class Person
{
	private String name;

	public Person()
	{
	}
	public Person(String newName)
	{
		name = newName;
	}
	public String toString()
	{
		return name;
	}
 /***************************************************************** 
 * 
 * Name:   equals
 * Description:  This method overrides the Object classes equals method. 
 * Inputs:   Person objects to be compared.
 * Returned value: true or false 
 * Preconditions:  Both objects must be of type Person. 
 * 
 *****************************************************************/
        @Override
        public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        else if(!(obj instanceof Person)){
            return false;
        }
        Person otherObj = (Person) obj;
        return this.name.equals(otherObj.name);
    }
}