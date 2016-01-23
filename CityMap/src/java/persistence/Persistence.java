/*
 * project    company
 * subproject persistence
*/

package persistence;

import javax.persistence.EntityManager;

public class Persistence extends spize.persistence.Persistence
{

    public static final String persistenceUnit = "CityMap";


    public static EntityManager connect ()
    {
        return connect (persistenceUnit);
    }

    public static EntityManager connect (String user, String password)
    {
        return connect (persistenceUnit, user, password);
    }
}
