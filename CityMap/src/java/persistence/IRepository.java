/*
 * project    company
 * subproject persistence
*/

package persistence;

import java.util.List;

public interface IRepository<T>
{

	T find(int id);

    List<T> findAll();

}

