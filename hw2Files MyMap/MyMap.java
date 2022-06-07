package map;

import java.io.Serializable;
import java.util.ArrayList;

// This class associates KeyObject with DataObject.
//
// For example, if KeyObject is an Integer representing a ID number,
// DataObject might be a String containing the name of the person
// with that ID.
//
// To work properly, KeyObject should implement the equals() method.
// Both KeyObject and DataObject should ideally also implement clone().
//
// The MyMap class should implement clone(), which is your job to do.

public class MyMap<KeyObject,DataObject> implements Cloneable, Serializable
{
	// Use two arraylists to store the mapping, with the position in the
	// arraylist as the way to associate keys to data
	private ArrayList<KeyObject> keys;
	private ArrayList<DataObject> data;

	// Constructors
	public MyMap()
	{
		keys = new ArrayList<KeyObject>();
		data = new ArrayList<DataObject>();
	}

	// Add pair to the list; goes through and checks if the
	// key already exists. If so, it is updated to the new value.
	// This is not very efficient since it traverses the entire list.
	public void addOrUpdateMap(KeyObject k, DataObject d)
	{
		for (int i = 0; i < keys.size(); i++)
		{
			if (keys.get(i).equals(k))
			{
				data.set(i, d);
				return;
			}
		}
		// Existing key not found, add new data to to the end
		keys.add(k);
		data.add(d);
	}

	// Retrieve the data values as an ArrayList
	public ArrayList<DataObject> getValues()
	{
		return data;
	}

	// Retrieve the key values as an ArrayList
	public ArrayList<KeyObject> getKeys()
	{
		return keys;
	}

	// Returns the data associated with a key, or null if no
	// such data is associated with the key
	public DataObject getData(KeyObject k)
	{
		for (int i = 0; i < keys.size(); i++)
		{
			if (keys.get(i).equals(k))
				return data.get(i);
		}
		return null;
	}

	// If we were to flesh this out further we would have methods to
	// delete items, but they are left off for simplicity

	// You have to flesh out clone so it works properly
        
	public MyMap<KeyObject,DataObject> clone()
	{
		try
		{
			//return (MyMap<KeyObject,DataObject>) super.clone();
                    MyMap<KeyObject,DataObject> copy = (MyMap<KeyObject,DataObject>) super.clone();
                    copy.data = (ArrayList<DataObject>)this.data.clone();
                    copy.keys = (ArrayList<KeyObject>)this.keys.clone();
                    return copy;
		}
		catch (CloneNotSupportedException e)
		{
			return null;
		}
	}
}